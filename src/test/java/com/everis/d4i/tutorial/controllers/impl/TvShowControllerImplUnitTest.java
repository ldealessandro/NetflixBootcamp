package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.TvShowCategoriesRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.TvShowService;
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
public class TvShowControllerImplUnitTest {

    @Mock
    private TvShowService tvShowService;

    @InjectMocks
    private TvShowControllerImpl tvShowControllerImpl;

    @Test
    public void getTvShowById() throws NetflixException{
        //given
        final TvShowRest tvShowRest = new TvShowRest();
        final Long id = 22L;
        
        tvShowRest.setId(id);
        
        Mockito.when(tvShowService.getTvShowById(id)).thenReturn(tvShowRest);

        //when
        NetflixResponse<TvShowRest> netflixResponse = tvShowControllerImpl.getTvShowById(id);

        //then
        assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(), tvShowRest);
    }

    @Test
    public void getTvShowByCategory() throws NetflixException {

        //given
    	final TvShowRest tvShow1 = new TvShowRest();
    	final TvShowRest tvShow2 = new TvShowRest();
    	final TvShowRest tvShow3 = new TvShowRest();
    	
        final List<TvShowRest> tvShowRestList = new ArrayList<>();
        tvShowRestList.add(tvShow1);
        tvShowRestList.add(tvShow2);
        tvShowRestList.add(tvShow3);
        
        final Long id = 1L;
        final CategoryRest category = new CategoryRest();
        category.setId(id);
        
        TvShowCategoriesRest tvShowCategories1 = new TvShowCategoriesRest();
        tvShowCategories1.setTvShow(tvShow1);
        tvShowCategories1.setCategory(category);
        TvShowCategoriesRest tvShowCategories2 = new TvShowCategoriesRest();
        tvShowCategories2.setTvShow(tvShow1);
        tvShowCategories2.setCategory(category);
        TvShowCategoriesRest tvShowCategories3 = new TvShowCategoriesRest();
        tvShowCategories3.setTvShow(tvShow1);
        tvShowCategories3.setCategory(category);
        
        Mockito.when(tvShowService.getTvShowsByCategory(id)).thenReturn(tvShowRestList);

        //when
        NetflixResponse<List<TvShowRest>> netflixResponse = tvShowControllerImpl.getTvShowsByCategory(id);

        //then
        assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(), tvShowRestList);
    }

}