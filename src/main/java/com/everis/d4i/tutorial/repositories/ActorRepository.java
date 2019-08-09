package com.everis.d4i.tutorial.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
	
    List<Actor> findByActorsChapterChapterId(Long chapterId);
    
    Actor findByName(String name);

}
