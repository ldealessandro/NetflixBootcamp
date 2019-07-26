package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorsChapterRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class ActorControllerImplUnitTest {

    @Mock
    private ActorService actorService;

    @InjectMocks
    private ActorControllerImpl actorControllerImpl;

    @Test
    public void getActors() throws NetflixException {

		// given
    	final List<ActorRest> actorRestList = new ArrayList<>();
		actorRestList.add(new ActorRest());
		actorRestList.add(new ActorRest());
		actorRestList.add(new ActorRest());
		Mockito.when(actorService.getActors()).thenReturn(actorRestList);

		// when
		NetflixResponse<List<ActorRest>> netflixResponse = actorControllerImpl.getActors();

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), actorRestList);
    }

    @Test
    public void createActor() throws NetflixException{
    	
		// given
    	final Long id = 1L;
    	final ActorRest actorRest = new ActorRest();
		actorRest.setId(id);
		Mockito.when(actorService.createActor(actorRest)).thenReturn(actorRest);

		// when
		NetflixResponse<ActorRest> netflixResponse = actorControllerImpl.createActor(actorRest);

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.CREATED));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), actorRest);
    }
    
    @Test
    public void deleteActor() throws NetflixException{
        //given
    	final ActorRest actorRest = new ActorRest();
    	final Long id = 22L;
        actorRest.setId(id);   
      
        Mockito.when(actorService.deleteActor(id)).thenReturn("actor delete sucefully");

        //when
        NetflixResponse<String> netflixResponse = actorControllerImpl.deleteActor(id);

        //then
        assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.NO_CONTENT));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(), "actor delete sucefully");
    }
    
    @Test
    public void modifyActor() throws NetflixException{
        //given
    	final ActorRest actorRest = new ActorRest();
    	final Long id = 22L;
        actorRest.setId(id);  
        final ActorRest actorRestModified = new ActorRest();
        actorRestModified.setId(id);
        
		Mockito.when(actorService.modifyActor(actorRest)).thenReturn(actorRestModified);

		// when
		NetflixResponse<ActorRest> netflixResponse = actorControllerImpl.modifyActor(actorRest);

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.NO_CONTENT));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), actorRestModified);
    }
    
    @Test
    public void getActorById() throws NetflixException{
		// given
    	final Long id = 1L;
    	final ActorRest actorRest = new ActorRest();
		actorRest.setId(id);
		Mockito.when(actorService.getActorById(id)).thenReturn(actorRest);

		// when
		NetflixResponse<ActorRest> netflixResponse = actorControllerImpl.getActorById(id);

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), actorRest);
    }
    
    @Test
    public void getActorsByChapter() throws NetflixException {

        //given
    	ActorRest actor1 = new ActorRest();
    	ActorRest actor2 = new ActorRest();
    	ActorRest actor3 = new ActorRest();
    	
        List<ActorRest> actorRestList = new ArrayList<>();
        actorRestList.add(actor1);
        actorRestList.add(actor2);
        actorRestList.add(actor3);
        
        Long id = 1L;
        ChapterRest chapter = new ChapterRest();
        chapter.setId(id);
        
        ActorsChapterRest actorsChapter1 = new ActorsChapterRest();
        actorsChapter1.setActor(actor1);
        actorsChapter1.setChapter(chapter);
        ActorsChapterRest actorsChapter2 = new ActorsChapterRest();
        actorsChapter2.setActor(actor2);
        actorsChapter2.setChapter(chapter);
        ActorsChapterRest actorsChapter3 = new ActorsChapterRest();
        actorsChapter3.setActor(actor3);
        actorsChapter3.setChapter(chapter);
        
        Mockito.when(actorService.getActorsByChapter(id)).thenReturn(actorRestList);

        //when
        NetflixResponse<List<ActorRest>> netflixResponse = actorControllerImpl.getActorsByChapter(id);

        //then
        assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertNotNull(netflixResponse.getData());
        assertEquals(netflixResponse.getData(), actorRestList);
    }
    
    @Test
    public void getTvShowChaptersByActor() throws NetflixException {

        //given
    	final ActorRest actor = new ActorRest();
    	        
    	final Long id = 1L;
        actor.setId(id);
        
        final List<TvShowRest> tvShowRestList = new ArrayList<>();
        tvShowRestList.add(new TvShowRest());
        tvShowRestList.add(new TvShowRest());
        tvShowRestList.add(new TvShowRest());
        

        
        Mockito.when(actorService.getTvShowsAndChaptersByActorId(id)).thenReturn(tvShowRestList);

        //when
        NetflixResponse<List<TvShowRest>> netflixResponse = actorControllerImpl.getTvShowChaptersByActor(id);

        //then
        assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertNotNull(netflixResponse.getData());
        assertEquals(netflixResponse.getData(), tvShowRestList);
    }
}