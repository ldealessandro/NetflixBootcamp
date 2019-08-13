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
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes = H2JpaConfig.class)

@Transactional

@Sql(scripts = "classpath:chapter.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class ChapterRepositoryIntegrationTest {

    @Autowired
    private 	ChapterRepository chapterRepository;

    @Test
    public void whenfindBySeasonTvShowIdAndSeasonNumber_thenReturnAListOfChapter() throws NetflixException {
    	
    	Long id = 1L;
    	Short id2 = 1;
    	
    	List<Chapter> foundChapterList = chapterRepository.findBySeasonTvShowIdAndSeasonNumber(id,id2);

        assertNotNull(foundChapterList);
        assertThat(foundChapterList.size()).isEqualTo(2);

    }
 
}
