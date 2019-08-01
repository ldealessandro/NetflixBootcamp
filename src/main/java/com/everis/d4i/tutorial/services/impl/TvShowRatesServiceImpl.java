package com.everis.d4i.tutorial.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowRatesService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class TvShowRatesServiceImpl implements TvShowRatesService {

	@Autowired
	private TvShowRepository tvShowRepository;
	

	private ModelMapper modelMapper = new ModelMapper();


	@Override
	public TvShowRest getRateByTvShowId(final Long id) throws NetflixException {

		
		 final TvShow tvShow = tvShowRepository.findById(id) 
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_TV_SHOW));
		 
		 TvShowRest tvShowRest = modelMapper.map(tvShow, TvShowRest.class);
		 
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8181/film-info/film/";
		
		Double rate = restTemplate.getForObject(url + tvShowRest.getName(), TvShowRest.class).getRate();
		
		tvShowRest.setRate(rate);

	return tvShowRest;
	}

}
