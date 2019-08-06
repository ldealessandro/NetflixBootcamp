package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowAwardsRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface TvShowController {

	NetflixResponse<List<TvShowRest>> getTvShowsByCategory(Long categoryId) throws NetflixException;

	NetflixResponse<TvShowRest> getTvShowById(Long id) throws NetflixException;
	
	NetflixResponse<List<TvShowAwardsRest>> getTvShowAwardsByTvShow(Long TvShowId) throws NetflixException;
	
	NetflixResponse<Double> getRateByTvShowId(Long id) throws NetflixException;
	
	NetflixResponse<Double> getRateOfCriticsByTvShowId(Long id) throws NetflixException;
}
