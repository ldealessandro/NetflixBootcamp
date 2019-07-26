package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowAwardsRest;

public interface TvShowAwardsService {

	List<TvShowAwardsRest> getTvShowAwardsByTvShow(Long tvShowId) throws NetflixException;
}
