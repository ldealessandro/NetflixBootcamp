package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.entities.AwardCategory;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.entities.TvShowAwards;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowAwardsRest;
import com.everis.d4i.tutorial.repositories.TvShowAwardsRepository;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TvShowAwardsServiceImplTest {

    @Mock
    TvShowAwardsRepository tvShowAwardsRepository;

    @InjectMocks
    TvShowAwardsServiceImpl tvShowAwardsService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getTvShowAwardsByTvShowReturnsAListOfTvShowAwardsRest() throws NetflixException {
        //given
    	
    	TvShowAwards tvShowAward = new TvShowAwards();
    	
    	TvShow tvShow = new TvShow();
    	Long tvShowId = (long) 1;
    	tvShow.setId(tvShowId);
    	
    	Award award = new Award();
    	Long awardId = (long) 1;
    	award.setId(awardId);
    	
    	AwardCategory awardCategory = new AwardCategory();
    	Long awardCategoryId = (long) 1;
    	awardCategory.setId(awardCategoryId);
    	
    	String year = "2010"; 	
    	
    	tvShowAward.setAward(award);
    	tvShowAward.setAwardCategory(awardCategory);
    	tvShowAward.setTvShow(tvShow);
    	tvShowAward.setYear(year);

    	 List<TvShowAwards> tvShowAwardsList = new ArrayList<>();
    	 tvShowAwardsList.add(tvShowAward);
         Mockito.when(tvShowAwardsRepository.findByTvShowId(tvShowId)).thenReturn(tvShowAwardsList);
    	
        //when
        List<TvShowAwardsRest> tvShowAwardsRestList = tvShowAwardsService.getTvShowAwardsByTvShow(tvShowId);

        //then
        assertThat(tvShowAwardsRestList.size()).isEqualTo(1);
        assertThat(tvShowAwardsRestList.get(0).getYear()).isEqualTo("2010");
    }


}