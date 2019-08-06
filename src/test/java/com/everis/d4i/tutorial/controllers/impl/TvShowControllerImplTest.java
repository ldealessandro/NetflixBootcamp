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
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.TvShowAwardsRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;

@RunWith(MockitoJUnitRunner.class)
public class TvShowControllerImplTest {
	@Mock
	private TvShowService tvShowService;

	@InjectMocks
	private TvShowControllerImpl tvShowControllerImpl;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getTvShowsByCategory() throws NetflixException {
		// given
		final long categoryId = 1L;
		final CategoryRest categoryRest = new CategoryRest();
		categoryRest.setId(categoryId);
		
		final List<TvShowRest> tvShowRestList = new ArrayList<>();
		tvShowRestList.add(new TvShowRest());
		tvShowRestList.add(new TvShowRest());
		tvShowRestList.add(new TvShowRest());		
		
		Mockito.when(tvShowService.getTvShowsByCategory(categoryId)).thenReturn(tvShowRestList);

		// when
		final NetflixResponse<List<TvShowRest>> netflixResponse = tvShowControllerImpl.getTvShowsByCategory(categoryId);
		
		// then
		assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);        
        assertEquals(netflixResponse.getData(), tvShowRestList);
	}
	
	@Test
	public void getTvShowById() throws NetflixException {
		// given
		final long id = 1;
		final TvShowRest tvShowRest = new TvShowRest();
		tvShowRest.setId(id);
		
		Mockito.when(tvShowService.getTvShowById(id)).thenReturn(tvShowRest);

		// when
		final NetflixResponse<TvShowRest> netflixResponse = tvShowControllerImpl.getTvShowById(id);
		
		// then
		assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(), tvShowRest);        
	}
	
	   @Test
	    public void getAwardsByTvShow() throws NetflixException {

	        //given
	    	AwardRest award1 = new AwardRest();
	    	AwardRest award2 = new AwardRest();
	    	AwardRest award3 = new AwardRest();
	    	
	        List<AwardRest> awardRestList = new ArrayList<>();
	        awardRestList.add(award1);
	        awardRestList.add(award2);
	        awardRestList.add(award3);
	        
	        Long id = 1L;
	        TvShowRest tvShow = new TvShowRest();
	        tvShow.setId(id);
	        
	        TvShowAwardsRest tvShowAwards1 = new TvShowAwardsRest();
	        tvShowAwards1.setAward(award1);
	        tvShowAwards1.setTvShow(tvShow);
	        TvShowAwardsRest tvShowAwards2 = new TvShowAwardsRest();
	        tvShowAwards2.setAward(award2);
	        tvShowAwards2.setTvShow(tvShow);
	        TvShowAwardsRest tvShowAwards3 = new TvShowAwardsRest();
	        tvShowAwards3.setAward(award3);
	        tvShowAwards3.setTvShow(tvShow);
	        
	        List<TvShowAwardsRest> tvShowAwardsRestList = new ArrayList<>();
	        Mockito.when(tvShowService.getTvShowAwardsByTvShow(id)).thenReturn(tvShowAwardsRestList);

	        //when
	        NetflixResponse<List<TvShowAwardsRest>> netflixResponse = tvShowControllerImpl.getTvShowAwardsByTvShow(id);

	        //then
	        assertNotNull(netflixResponse);
	        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
	        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
	        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
	        assertEquals(netflixResponse.getData(), tvShowAwardsRestList);
	    }
	   
	   @Test
		public void getRatesByTvShowIdReturnsRate() throws NetflixException {
			// given
			final long id = 1;
			final TvShowRest tvShowRest = new TvShowRest();
			tvShowRest.setId(id);
			Double rate = null;
			
			//Mockito.when(tvShowService.getTvShowById(id)).thenReturn(tvShowRest);
			Mockito.when(tvShowService.getRateByTvShowId(id)).thenReturn(rate);

			// when
			final NetflixResponse<Double> netflixResponse = tvShowControllerImpl.getRateByTvShowId(id);
			
			// then
			assertNotNull(netflixResponse);
	        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
	        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
	        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
	        assertEquals(netflixResponse.getData(), rate);        
		}
	   
	   @Test
		public void getRateOfCriticsByTvShowIdReturnsRateOfCritics() throws NetflixException {
			// given
			final long id = 1;
			final TvShowRest tvShowRest = new TvShowRest();
			tvShowRest.setId(id);
			Double rateOfCritics = null;
			
			//Mockito.when(tvShowService.getTvShowById(id)).thenReturn(tvShowRest);
			Mockito.when(tvShowService.getRateOfCriticsByTvShowId(id)).thenReturn(rateOfCritics);

			// when
			final NetflixResponse<Double> netflixResponse = tvShowControllerImpl.getRateOfCriticsByTvShowId(id);
			
			// then
			assertNotNull(netflixResponse);
	        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
	        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
	        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
	        assertEquals(netflixResponse.getData(), rateOfCritics);        
		}
}
