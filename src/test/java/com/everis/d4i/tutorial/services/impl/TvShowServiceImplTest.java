package com.everis.d4i.tutorial.services.impl;

 

import static org.assertj.core.api.Assertions.assertThat;

 

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

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;

 

@RunWith(MockitoJUnitRunner.class)
public class TvShowServiceImplTest {

    @Mock
    private TvShowRepository tvShowRepository;

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

        final TvShowRest tvShowRest = new TvShowRest();
        
        Mockito.when(tvShowRepository.findById(id)).thenReturn(Optional.of(tvShow));
        Mockito.when(modelMapper.map(tvShow, TvShowRest.class)).thenReturn(tvShowRest);

        // when
        TvShowRest tvShowRestFinal = tvShowService.getTvShowById(id);

        // then
        assertThat(tvShowRestFinal).isEqualTo(tvShowRest);

    }

	@Test(expected = NotFoundException.class)
	public void getTvShowByIdNotFoundExeption() throws NetflixException {

		// given
		final long id = 1L;
		Mockito.when(tvShowRepository.findById(id)).thenReturn(Optional.empty());

		// when
		tvShowService.getTvShowById(id);

	}
}