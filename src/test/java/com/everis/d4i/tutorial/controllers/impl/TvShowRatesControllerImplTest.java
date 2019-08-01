package com.everis.d4i.tutorial.controllers.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.TvShowRatesService;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;

@RunWith(MockitoJUnitRunner.class)
public class TvShowRatesControllerImplTest {
	@Mock
	private TvShowRatesService tvShowRatesService;

	@InjectMocks
	private TvShowRatesControllerImpl tvShowRatesControllerImpl;

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void getRatesByTvShowIdReturnsTvShowRest() throws NetflixException {
		// given
		final long id = 1;
		final TvShowRest tvShowRest = new TvShowRest();
		tvShowRest.setId(id);
		
		Mockito.when(tvShowRatesService.getRateByTvShowId(id)).thenReturn(tvShowRest);

		// when
		final NetflixResponse<TvShowRest> netflixResponse = tvShowRatesControllerImpl.getRateByTvShowId(id);
		
		// then
		assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(), tvShowRest);        
	}
}
