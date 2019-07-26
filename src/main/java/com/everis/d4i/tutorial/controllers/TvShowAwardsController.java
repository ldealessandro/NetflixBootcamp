package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowAwardsRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface TvShowAwardsController {
	
	NetflixResponse<List<TvShowAwardsRest>> getTvShowAwardsByTvShow(Long TvShowId) throws NetflixException;

}