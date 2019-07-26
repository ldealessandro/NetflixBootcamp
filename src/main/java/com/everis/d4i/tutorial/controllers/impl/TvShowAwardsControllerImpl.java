package com.everis.d4i.tutorial.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.everis.d4i.tutorial.controllers.TvShowAwardsController;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowAwardsRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.TvShowAwardsService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_TVSHOW_AWARDS)
public class TvShowAwardsControllerImpl implements TvShowAwardsController {

	@Autowired
	private TvShowAwardsService tvShowAwardService;

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/getTvShowAwardsByTvShow/{tvShowId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<TvShowAwardsRest>> getTvShowAwardsByTvShow(@PathVariable final Long tvShowId) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowAwardService.getTvShowAwardsByTvShow(tvShowId));
	}

}
