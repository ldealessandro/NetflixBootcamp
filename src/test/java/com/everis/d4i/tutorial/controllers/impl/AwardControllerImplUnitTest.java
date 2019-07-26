package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.AwardService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class AwardControllerImplUnitTest {

    @Mock
    private AwardService awardService;

    @InjectMocks
    private AwardControllerImpl awardControllerImpl;

    @Test
    public void getAwards() throws NetflixException {

		// given
    	final List<AwardRest> awardRestList = new ArrayList<>();
    	awardRestList.add(new AwardRest());
    	awardRestList.add(new AwardRest());
    	awardRestList.add(new AwardRest());
		Mockito.when(awardService.getAwards()).thenReturn(awardRestList);

		// when
		NetflixResponse<List<AwardRest>> netflixResponse = awardControllerImpl.getAwards();

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), awardRestList);
    }
    
    @Test
    public void createAward() throws NetflixException{
    	
		// given
    	final Long id = 1L;
    	final AwardRest awardRest = new AwardRest();
    	awardRest.setId(id);
		Mockito.when(awardService.createAward(awardRest)).thenReturn(awardRest);

		// when
		NetflixResponse<AwardRest> netflixResponse = awardControllerImpl.createAward(awardRest);

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.CREATED));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), awardRest);
    }
    
  

}