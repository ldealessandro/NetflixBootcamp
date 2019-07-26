package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.json.TvShowAwardsRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.TvShowAwardsService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class TvShowAwardsControllerImplUnitTest {

    @Mock
    private TvShowAwardsService tvShowAwardsService;

    @InjectMocks
    private TvShowAwardsControllerImpl tvShowAwardsControllerImpl;

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
        Mockito.when(tvShowAwardsService.getTvShowAwardsByTvShow(id)).thenReturn(tvShowAwardsRestList);

        //when
        NetflixResponse<List<TvShowAwardsRest>> netflixResponse = tvShowAwardsControllerImpl.getTvShowAwardsByTvShow(id);

        //then
        assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(), tvShowAwardsRestList);
    }

}