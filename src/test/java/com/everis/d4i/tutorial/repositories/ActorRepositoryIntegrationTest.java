package com.everis.d4i.tutorial.repositories;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.everis.d4i.tutorial.config.H2JpaConfig;
import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes = H2JpaConfig.class)
//llamar a la clase H2JPACONFIG
@Transactional

@Sql(scripts = "classpath:actor.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class ActorRepositoryIntegrationTest {

	
    //@MockBean en estos tets no se mockea!
	@Autowired
    private 	ActorRepository actorRepository;

    
    @Test
    public void whenFindByName_thenReturnActorWithSameName() throws NetflixException {
    	
    	Long id = 1L;
    	String name  = "actor1";
    	
    	Actor foundActor = actorRepository.findByName(name);

        assertNotNull(foundActor);
        assertEquals(name, foundActor.getName());
        assertEquals(id, foundActor.getId());

    }
    
    @Test
    public void whenFindByActorsChapterChapterId_thenReturnAListOfActors() throws NetflixException {
    	
    	Long id = 1L;
    	
    	List<Actor> foundActorList = actorRepository.findByActorsChapterChapterId(id);

        assertNotNull(foundActorList);
        assertThat(foundActorList.size()).isEqualTo(2);
        assertThat(foundActorList.get(0).getName()).isEqualTo("actor1");

    }
    
}
