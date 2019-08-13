package com.everis.d4i.tutorial.repositories;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.everis.d4i.tutorial.config.H2JpaConfig;
import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes = H2JpaConfig.class)
//llamar a la clase H2JPACONFIG
@Transactional

@Sql(scripts = "classpath:category.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class CategoryRepositoryIntegrationTest {

	
    @MockBean
    private 	CategoryRepository categoryRepository;

    @Test
    public void whenFindByName_thenReturnCategoryWithSameName() throws NetflixException {
    	
    	String name  = "category1";
    	//Category category = new Category();
    	//category.setId(id);
    	//category.setName(name);
    	
    	Optional<Category> foundCategory = categoryRepository.findByName(name);
    	assertThat(foundCategory.isPresent());
    	assertNotNull(foundCategory);
        if(foundCategory.isPresent()) {
        	System.out.println(foundCategory.get().getName());
        } 
        

    }

}
