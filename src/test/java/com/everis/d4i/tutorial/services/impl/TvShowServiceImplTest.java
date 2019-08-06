package com.everis.d4i.tutorial.services.impl;

 

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

 

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.entities.AwardCategory;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.entities.TvShowAwards;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowAwardsRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowAwardsRepository;
import com.everis.d4i.tutorial.repositories.TvShowRatesOfCriticsRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;

 

@RunWith(MockitoJUnitRunner.class)
public class TvShowServiceImplTest {

    @Mock
    private TvShowRepository tvShowRepository;
    
    @Mock
    TvShowAwardsRepository tvShowAwardsRepository;
    
    @Mock
    TvShowRatesOfCriticsRepository tvShowRatesOfCriticsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    TvShowServiceImpl tvShowService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getTvShowsByCategory() throws NetflixException {

        // given
        final Long categoryId = 1L;

        final TvShow tvShow1 = new TvShow();
        final TvShow tvShow2 = new TvShow();

        final List<TvShow> tvShowList = Arrays.asList(tvShow1, tvShow2);

        final TvShowRest tvShowRest1 = new TvShowRest();
        final TvShowRest tvShowRest2 = new TvShowRest();

        Mockito.when(tvShowRepository.findByTvShowCategoriesCategoryId(categoryId)).thenReturn(tvShowList);
        Mockito.when(modelMapper.map(tvShow1, TvShowRest.class)).thenReturn(tvShowRest1);
        Mockito.when(modelMapper.map(tvShow2, TvShowRest.class)).thenReturn(tvShowRest2);

        // when
        final List<TvShowRest> tvShowRestList = tvShowService.getTvShowsByCategory(categoryId);

        // then
        assertThat(tvShowRestList).contains(tvShowRest1, tvShowRest2);

    }

 

    @Test
    public void getTvShowById() throws NetflixException {

        // given
        final long id = 1L;

        final TvShow tvShow = new TvShow();
        tvShow.setId(id);
        tvShow.setName("Juego de tronos");

        final TvShowRest tvShowRest = new TvShowRest();
        tvShowRest.setName(tvShow.getName());
        final TvShowRest tvShowRest2 = new TvShowRest();
        tvShowRest2.setName(tvShow.getName());
        tvShowRest2.setRateOfCritics(2d);
        final TvShowRest tvShowRest3 = new TvShowRest();
        tvShowRest3.setRateOfCritics(2d);
       
        Mockito.when(tvShowRepository.findById(id)).thenReturn(Optional.of(tvShow));
        Mockito.when(modelMapper.map(tvShow, TvShowRest.class)).thenReturn(tvShowRest);
        Mockito.when(tvShowRatesOfCriticsRepository.findRateOfCritics(tvShow.getName())).thenReturn(tvShowRest3);

      // when
        TvShowRest tvShowRestFinal = tvShowService.getTvShowById(id);

        // then
        assertThat(tvShowRestFinal).isEqualTo(tvShowRest2);

    }

	@Test(expected = NotFoundException.class)
	public void getTvShowByIdNotFoundExeption() throws NetflixException {

		// given
		final long id = 1L;
		Mockito.when(tvShowRepository.findById(id)).thenReturn(Optional.empty());

		// when
		tvShowService.getTvShowById(id);

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
	        List<TvShowAwardsRest> tvShowAwardsRestList = tvShowService.getTvShowAwardsByTvShow(tvShowId);

	        //then
	        
	        assertThat(tvShowAwardsRestList.size()).isEqualTo(1);
	        //assertThat(tvShowAwardsRestList.get(0).getYear()).isEqualTo("2010");
	    }

}