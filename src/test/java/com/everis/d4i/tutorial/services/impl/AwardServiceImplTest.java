package com.everis.d4i.tutorial.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.exceptions.ConflictException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
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

	@Test(expected = NotFoundException.class)
	public void getAwardThrowsAnExceptionWhenTheDBContainsNoAward() throws NetflixException {
		// given
		final List<Award> awardList = new ArrayList<>();
		Mockito.when(awardRepository.findAll()).thenReturn(awardList);

		// when
		awardService.getAwards();
	}



	@Test(expected = ConflictException.class)
	public void createAwardThrowsAnExceptionIfTheDBAlreadyContainsTheAward() throws NetflixException {
		// given
		final Award awardInput = new Award();
		awardInput.setName("pepito");
		final Award awardOutput = new Award();
		awardOutput.setId(3L);
		awardOutput.setName(awardInput.getName());
		final AwardRest awardRest = new AwardRest();
		awardRest.setName("pepito");
		Mockito.when(awardRepository.findById(awardInput.getId())).thenReturn(Optional.of(awardOutput));

		// when
		awardService.createAward(awardRest);
	}

	@Test
	public void createAward() throws NetflixException {

		// given
		final Award award = new Award();
		final AwardRest awardRest = new AwardRest();
		Mockito.when(awardRepository.save(Mockito.any(Award.class))).thenReturn(award);
		Mockito.when(modelMapper.map(award, AwardRest.class)).thenReturn(awardRest);

		// when
		AwardRest awardRestOut = awardService.createAward(awardRest);

		// then
		assertThat(awardRestOut).isEqualTo(awardRest);
	}


}