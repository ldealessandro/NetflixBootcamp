package com.everis.d4i.tutorial.repositories;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
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

public class CategoryRepositoryIntegrationTest {

	
    @MockBean
    private 	ActorRepository actorRepository;

    
    /*@Test
    public void whenFindById_thenReturnActor() throws NetflixException {
    	Long id = 1L;
    	String name  = "actor1";
    	Actor actor = new Actor();
    	actor.setId(id);
    	actor.setName(name);
    
    	actorRepository.save(actor);
    	
    	final Actor dbActor = actorRepository.findByName(name);
    	Assert.assertNotNull(dbActor);
    	//Assert.assertEquals(dbActor.getId(), id);
    }*/

}
