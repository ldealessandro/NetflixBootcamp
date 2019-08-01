package com.everis.d4i.tutorial.services.impl;

 

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

 

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;

 

@RunWith(MockitoJUnitRunner.class)
public class TvShowRatesServiceImplTest {

    @Mock
    private TvShowRepository tvShowRepository;

    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    TvShowRatesServiceImpl tvShowRatesService;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void getRateByTvShowId() throws NetflixException {
    	
        // given
        final long id = 1L;

        final TvShow tvShow = new TvShow();
        tvShow.setId(id);

        final TvShowRest tvShowRest = new TvShowRest();
        
        Mockito.when(tvShowRepository.findById(id)).thenReturn(Optional.of(tvShow));
        Mockito.when(modelMapper.map(tvShow, TvShowRest.class)).thenReturn(tvShowRest);

        // when
        final TvShowRest tvShowRestFinal = tvShowRatesService.getRateByTvShowId(id);
        
        // then
        assertThat(tvShowRestFinal).isSameAs(tvShowRest);
        assertThat(tvShowRestFinal.getRate()).isNotNull();

       

    }

	@Test(expected = NotFoundException.class)
	public void getTvShowByIdNotFoundExeption() throws NetflixException {

		// given
		final long id = 1L;
		Mockito.when(tvShowRepository.findById(id)).thenReturn(Optional.empty());

		// when
		tvShowRatesService.getRateByTvShowId(id);

	}
}