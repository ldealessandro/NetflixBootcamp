package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface TvShowRatesController {

	NetflixResponse<TvShowRest> getRateByTvShowId(Long id) throws NetflixException;
	
}
