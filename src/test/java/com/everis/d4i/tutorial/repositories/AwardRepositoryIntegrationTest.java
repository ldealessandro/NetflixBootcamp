package com.everis.d4i.tutorial.repositories;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.everis.d4i.tutorial.config.H2JpaConfig;
import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes = H2JpaConfig.class)
//llamar a la clase H2JPACONFIG
@Transactional

@Sql(scripts = "classpath:award.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class AwardRepositoryIntegrationTest {

	
	@Autowired
    private 	AwardRepository awardRepository;

    
    
    @Test
    public void whenfindByTvShowAwardsTvShowId_thenReturnAListOfAwards() throws NetflixException {
    	
    	Long id = 1L;
    	
    	List<Award> foundAwardList = awardRepository.findByTvShowAwardsTvShowId(id);

        assertNotNull(foundAwardList);
        assertThat(foundAwardList.size()).isEqualTo(2);
        assertThat(foundAwardList.get(0).getName()).isEqualTo("award1");

    }

}
