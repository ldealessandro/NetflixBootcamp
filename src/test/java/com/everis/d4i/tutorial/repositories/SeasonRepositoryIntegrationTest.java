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
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes = H2JpaConfig.class)
//llamar a la clase H2JPACONFIG
@Transactional

@Sql(scripts = "classpath:season.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class SeasonRepositoryIntegrationTest {

	
	@Autowired
    private 	SeasonRepository seasonRepository;

    //findByTvShowId
    @Test
    public void whenFindByTvShowId_thenReturnAListOfSeasons() throws NetflixException {
    	
    	Long id = 1L;
    	
    	List<Season> foundSeasonList = seasonRepository.findByTvShowId(id);

        assertNotNull(foundSeasonList);
        assertThat(foundSeasonList.size()).isEqualTo(2);
        assertThat(foundSeasonList.get(0).getName()).isEqualTo("One");

    }

}
