package com.everis.d4i.tutorial.repositories;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.everis.d4i.tutorial.config.H2JpaConfig;
import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.entities.TvShowAwards;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes = H2JpaConfig.class)
//llamar a la clase H2JPACONFIG
@Transactional

@Sql(scripts = "classpath:tvShowAwards.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class TvShowAwardsRepositoryIntegrationTest {

	
	@Autowired
    private 	TvShowAwardsRepository tvShowAwardsRepository;

    
   @Test
    public void whenFindById_thenReturnTvShowAwardList() throws NetflixException {
   	Long id = 1L;
	
   	List<TvShowAwards> foundTvShowAwardsList = tvShowAwardsRepository.findByTvShowId(id);

       assertNotNull(foundTvShowAwardsList);
       assertThat(foundTvShowAwardsList.size()).isEqualTo(2);
       assertThat(foundTvShowAwardsList.get(0).getAward().getName()).isEqualTo("Oscar");
   }
}
