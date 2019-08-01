package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface TvShowRatesService {

	TvShowRest getRateByTvShowId(Long id) throws NetflixException;


}
