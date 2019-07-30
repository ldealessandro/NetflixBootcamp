package com.everis.d4i.tutorial.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.repositories.AwardRepository;

@RunWith(MockitoJUnitRunner.class)
public class AwardServiceImplTest {

	@Mock
	AwardRepository awardRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	AwardServiceImpl awardService;

	@Test
	public void getAwards() throws NetflixException {
		// given
		final Award award1 = new Award();
		final Award award2 = new Award();

		final List<Award> awardList = Arrays.asList(award1, award2);

		final AwardRest awardRest1 = new AwardRest();
		final AwardRest awardRest2 = new AwardRest();

		Mockito.when(awardRepository.findAll()).thenReturn(awardList);
		Mockito.when(modelMapper.map(award1, AwardRest.class)).thenReturn(awardRest1);
		Mockito.when(modelMapper.map(award2, AwardRest.class)).thenReturn(awardRest2);

		// when
		final List<AwardRest> awardRestList = awardService.getAwards();

		// then
		assertThat(awardRestList).contains(awardRest1, awardRest2);
	}



	@Test
	public void createAward() throws NetflixException {

		// given
		final AwardRest awardRest = new AwardRest();
		final Award awardInput = new Award();
		final Award awardOutput = new Award();
		final AwardRest awardRestOut = new AwardRest();

		Mockito.when(modelMapper.map(awardRest, Award.class)).thenReturn(awardInput);
		Mockito.when(awardRepository.save(awardInput)).thenReturn(awardOutput);
		Mockito.when(modelMapper.map(awardOutput, AwardRest.class)).thenReturn(awardRestOut);

		// when
		final AwardRest actorRestOutput = awardService.createAward(awardRest);

		// then
		assertThat(actorRestOutput).isSameAs(awardRestOut);
	}


}