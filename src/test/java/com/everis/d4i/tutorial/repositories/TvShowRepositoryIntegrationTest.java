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
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes = H2JpaConfig.class)
//llamar a la clase H2JPACONFIG
@Transactional

@Sql(scripts = "classpath:tvshow.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class TvShowRepositoryIntegrationTest {

	
	@Autowired
    private 	TvShowRepository tvShowRepository;

   @Test
    public void whenFindByTvShowCategoriesCategoryId_thenReturnAListOftVshOW() throws NetflixException {
    	
    	//Long categoryId = 1L;
    	
    	//List<TvShow> foundTvShowList = tvShowRepository.findByTvShowCategoriesCategoryId(categoryId);

        //assertNotNull(foundTvShowList);
        //assertThat(foundTvShowList.size()).isEqualTo(2);
        //assertThat(foundTvShowList.get(0).getName()).isEqualTo("TvShow1");

    }


}
