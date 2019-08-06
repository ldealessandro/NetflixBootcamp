package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowAwardsRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowAwardsRepository;
import com.everis.d4i.tutorial.repositories.TvShowRatesOfCriticsRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class TvShowServiceImpl implements TvShowService {

	@Autowired
	private TvShowRepository tvShowRepository;
	
	@Autowired
	private TvShowRatesOfCriticsRepository tvShowRatesOfCriticsRepository;
	
	@Autowired
	private TvShowAwardsRepository tvShowAwardsRepository;
	

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<TvShowRest> getTvShowsByCategory(final Long categoryId) throws NetflixException {

		return tvShowRepository.findByTvShowCategoriesCategoryId(categoryId).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());

	}

	@Override
	public TvShowRest getTvShowById(final Long id) throws NetflixException {

		final TvShow tvShow = tvShowRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_TV_SHOW));

		final TvShowRest tvShowRest = modelMapper.map(tvShow, TvShowRest.class);
		tvShowRest.setRate(this.getRateByTvShowId(tvShow.getId()));
		tvShowRest.setRateOfCritics(this.getRateOfCriticsByTvShowId(tvShow.getId()));
		
	return tvShowRest;
	}
	
	public List<TvShowAwardsRest> getTvShowAwardsByTvShow(final Long id) throws NetflixException {

		return tvShowAwardsRepository.findByTvShowId(id).stream().map(tvShowAward -> modelMapper.map(tvShowAward, TvShowAwardsRest.class))
					.collect(Collectors.toList());

	}
	
	@Override
	public Double getRateByTvShowId(final Long id) throws NetflixException {

		
		 final TvShow tvShow = tvShowRepository.findById(id) 
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_TV_SHOW));
		 
		 TvShowRest tvShowRest = modelMapper.map(tvShow, TvShowRest.class);
		 
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8181/film-info/film/";
		
		Double rate = restTemplate.getForObject(url + tvShowRest.getName(), TvShowRest.class).getRate();
		
		//tvShowRest.setRate(rate);

	return rate;
	}
	
	@Override
	public Double getRateOfCriticsByTvShowId(final Long id) throws NetflixException {
		
		 final TvShow tvShow = tvShowRepository.findById(id) 
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_TV_SHOW));
		 
		final TvShowRest tvShowRest = modelMapper.map(tvShow, TvShowRest.class);	
		
		Double rateOfCritics = tvShowRatesOfCriticsRepository.findRateOfCritics(tvShowRest.getName()).getRate();
		
				
	return rateOfCritics;
	}

}
