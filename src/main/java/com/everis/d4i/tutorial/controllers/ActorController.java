package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface ActorController {

	NetflixResponse<List<ActorRest>> getActorsByChapter(Long chapterId) throws NetflixException;
	
	NetflixResponse<List<ActorRest>> getActors() throws NetflixException;
	
	NetflixResponse<ActorRest> getActorById(Long id) throws NetflixException;
	
	NetflixResponse<ActorRest> createActor(ActorRest actorRest) throws NetflixException;
	
	NetflixResponse<List<TvShowRest>> getTvShowChaptersByActor(Long actorId) throws NetflixException;
	
	NetflixResponse<ActorRest> updateActor(ActorRest actorRest) throws NetflixException;
	
	NetflixResponse<String> deleteActor(Long actorId) throws NetflixException;
	
	

}