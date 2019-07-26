package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.repositories.AwardRepository;
import com.everis.d4i.tutorial.services.AwardService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class AwardServiceImpl implements AwardService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AwardServiceImpl.class);

	@Autowired
	private AwardRepository awardRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<AwardRest> getAwards() throws NetflixException {

		return awardRepository.findAll().stream().map(award -> modelMapper.map(award, AwardRest.class))
				.collect(Collectors.toList());

	}
	
	@Override
	public AwardRest createAward(final AwardRest awardRest) throws NetflixException {
		Award award = new Award();
		award.setName(awardRest.getName());
		award.setDescription(awardRest.getDescription());
		try {
			award = awardRepository.save(award);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(award, AwardRest.class);
	}
	


}
