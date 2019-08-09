package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface ActorService {

	List<ActorRest> getActors() throws NetflixException;
	
	ActorRest getActorById(Long id) throws NetflixException;
	
	ActorRest getActorByName(String name) throws NetflixException;
	
	ActorRest createActor(ActorRest actorRest) throws NetflixException;
	
	String deleteActor(Long id) throws NetflixException;
	
	ActorRest updateActor(ActorRest actorRest) throws NetflixException;
	
	List<ActorRest> getActorsByChapter(Long chapterId) throws NetflixException;

	List<TvShowRest>  getTvShowsAndChaptersByActorId(Long actorId) throws NetflixException;
	

}
