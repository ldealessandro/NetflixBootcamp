package com.everis.d4i.tutorial.repositories;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.utils.constants.RestConstants;

//import feign.Param;

//@Repository
@FeignClient(value="TvShow", url="http://localhost:8181/film-info/film")

public interface TvShowRatesOfCriticsRepository {

	
	@RequestMapping(method = RequestMethod.GET, value = RestConstants.RESOURCE_NAME, produces = "application/json")
	TvShowRest findRateOfCritics(@PathVariable("name") String tvShowName);
	//TvShow findRateOfCritics(@Param("name") String tvShowName);
	
}
