package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class TvShowServiceImpl implements TvShowService {

	@Autowired
	private TvShowRepository tvShowRepository;
	

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

	return modelMapper.map(tvShow, TvShowRest.class);
	}

}
