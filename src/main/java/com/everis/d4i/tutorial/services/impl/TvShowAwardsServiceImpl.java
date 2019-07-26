package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowAwardsRest;
import com.everis.d4i.tutorial.repositories.TvShowAwardsRepository;
import com.everis.d4i.tutorial.services.TvShowAwardsService;

@Service
public class TvShowAwardsServiceImpl implements TvShowAwardsService {

	//private static final Logger LOGGER = LoggerFactory.getLogger(TvShowAwardsServiceImpl.class);

	@Autowired
	private TvShowAwardsRepository tvShowAwardsRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public List<TvShowAwardsRest> getTvShowAwardsByTvShow(final Long tvShowId) throws NetflixException {
		
		try {
			
			final List<TvShowAwardsRest> awardList = tvShowAwardsRepository.findByTvShowId(tvShowId).stream().map(tvShowAward -> modelMapper.map(tvShowAward, TvShowAwardsRest.class))
					.collect(Collectors.toList());
			
			return awardList;
		
		} catch (final EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
	}

}
