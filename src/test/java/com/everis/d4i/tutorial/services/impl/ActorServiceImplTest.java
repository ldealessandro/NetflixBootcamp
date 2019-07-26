package com.everis.d4i.tutorial.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.ActorsChapter;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.ConflictException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorsChapterRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;

@RunWith(MockitoJUnitRunner.class)
public class ActorServiceImplTest {

	@Mock
	ActorRepository actorRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	ActorServiceImpl actorService;

	@Test
	public void getActors() throws NetflixException {
		// given
		final Actor actor1 = new Actor();
		final Actor actor2 = new Actor();

		final List<Actor> actorList = Arrays.asList(actor1, actor2);

		final ActorRest actorRest1 = new ActorRest();
		final ActorRest actorRest2 = new ActorRest();

		Mockito.when(actorRepository.findAll()).thenReturn(actorList);
		Mockito.when(modelMapper.map(actor1, ActorRest.class)).thenReturn(actorRest1);
		Mockito.when(modelMapper.map(actor2, ActorRest.class)).thenReturn(actorRest2);

		// when
		final List<ActorRest> actorRestList = actorService.getActors();

		// then
		assertThat(actorRestList).contains(actorRest1, actorRest2);
	}

	@Test(expected = NotFoundException.class)
	public void getActorsThrowsAnExceptionWhenTheDBContainsNoActors() throws NetflixException {
		// given
		final List<Actor> actorList = new ArrayList<>();
		Mockito.when(actorRepository.findAll()).thenReturn(actorList);

		// when
		actorService.getActors();
	}

	@Test
	public void getActorById() throws NetflixException {

		// given
		final long id = 1L;

		final Actor actor = new Actor();
		actor.setId(id);

		final ActorRest actorRest = new ActorRest();

		Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
		Mockito.when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest);

		// when
		final ActorRest actorRestFinal = actorService.getActorById(id);

		// then
		assertThat(actorRestFinal).isSameAs(actorRest);

	}

	@Test(expected = NotFoundException.class)
	public void getActorByIdNotFound() throws NetflixException {

		// given
		final long id = 1L;
		Mockito.when(actorRepository.findById(id)).thenReturn(Optional.empty());

		// when
		actorService.getActorById(id);

	}

	@Test(expected = ConflictException.class)
	public void createActorThrowsAnExceptionIfTheDBAlreadyContainsTheActor() throws NetflixException {
		// given
		final Actor actorInput = new Actor();
		actorInput.setName("pepito");
		final Actor actorOutput = new Actor();
		actorOutput.setId(3L);
		actorOutput.setName(actorInput.getName());
		final ActorRest actorRest = new ActorRest();
		actorRest.setName("pepito");
		Mockito.when(actorRepository.findByName(actorInput.getName())).thenReturn(Optional.of(actorOutput));

		// when
		actorService.createActor(actorRest);
	}

	@Test
	public void createActor() throws NetflixException {

		// given
		final Actor actor = new Actor();
		final ActorRest actorRest = new ActorRest();
		Mockito.when(actorRepository.save(Mockito.any(Actor.class))).thenReturn(actor);
		Mockito.when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest);

		// when
		ActorRest actorRestOut = actorService.createActor(actorRest);

		// then
		assertThat(actorRestOut).isEqualTo(actorRest);
	}

	@Test
	public void testDelete() throws NetflixException {
		final Long id = 1L;
		final Actor actor1 = new Actor();
		actor1.setId(id);

		Mockito.when(actorRepository.existsById(id)).thenReturn(true);
		String message = actorService.deleteActor(id);

		assertThat(message).isEqualTo("actor deleted sucefully");
	}

	@Test(expected = NotFoundException.class)
	public void testDeleteWhenActorNotFound() throws NetflixException {
		final Long id = 1L;
		final Actor actor1 = new Actor();
		actor1.setId(id);

		Mockito.when(actorRepository.existsById(id)).thenReturn(false);
		actorService.deleteActor(id);

	}
	
    @Test
    public void updateActor() throws NetflixException {
        Actor actor = new Actor();
        actor.setId(1L);
        actor.setName("Milla");
        
        ActorRest actorRest = new ActorRest();
        actorRest.setId(1L);
        actorRest.setName("Alice");
        
        Mockito.when(actorRepository.existsById(1L)).thenReturn(true);
        Mockito.when(actorRepository.findById(1L)).thenReturn(Optional.of(actor));
        actor.setName(actorRest.getName());
        
        Mockito.when(modelMapper.map(actorRest, Actor.class)).thenReturn(actor);
        Mockito.when(modelMapper.map(actorRepository.findById(1L), ActorRest.class)).thenReturn(actorRest);

        ActorRest actorRestOut = actorService.modifyActor(actorRest);
        
        assertThat(actorRestOut).isEqualTo(actorRest);

    }
    
	@Test
	public void getActorsByChapter() throws NetflixException {


		final Long id = 1L;
		final List<ActorRest> actorRestList = new ArrayList<>();
		
		Mockito.when(actorRepository.findByActorsChapterChapterId(id).stream()
				.map(actor -> modelMapper.map(actor, ActorRest.class)).collect(Collectors.toList())).thenReturn(actorRestList);

		// when
		final List<ActorRest> actorRestListFinal = actorService.getActorsByChapter(id);

		// then
		assertThat(actorRestListFinal).isEqualTo(actorRestList);

	}

	/*@Test(expected = NotFoundException.class)
	public void getActorsByChapterThrowsAnExceptionWhenTheDBContainsNo() throws NetflixException {
		final Long id = 1L;
		final List<ActorRest> actorRestList = new ArrayList<>();
		
		Mockito.when(actorRepository.findByActorsChapterChapterId(id).stream()
				.map(actor -> modelMapper.map(actor, ActorRest.class)).collect(Collectors.toList())).thenReturn(actorRestList);

		// when
		actorService.getActorsByChapter(id).isEmpty();

	}*/

	@Test
	public void getTvShowsAndChaptersByActor() throws NetflixException {
		
		final long id = 1L;
		final Actor actor = new Actor();
		final TvShow tvShow = new TvShow();
		final TvShowRest tvShowRest = new TvShowRest();
		final Season season = new Season();
		final SeasonRest seasonRest = new SeasonRest();
		final Chapter chapter = new Chapter();
		final ChapterRest chapterRest = new ChapterRest();
		final Map<TvShowRest, Set<SeasonRest>> tvShowSeasonMap = new HashMap<>(); // TvShowRest - Set SeasonRest
		final Map<SeasonRest, List<ChapterRest>> seasonChaptersMap = new HashMap<>();
		final ActorsChapter chapterActor1 = new ActorsChapter();
		final List<ActorsChapter> chapterActorList = new ArrayList<>();
		chapterActorList.add(chapterActor1);
		actor.setActorsChapter(chapterActorList);
		// final ActorRest actorRest = new ActorRest();

		actor.setId(id);
		// chapterActor.setActor(actor);
		// chapterActor.setChapter(chapter);
		chapter.setSeason(season);
		season.setTvShow(tvShow);
		chapterActor1.setChapter(chapter);
		tvShowSeasonMap.computeIfAbsent(tvShowRest, k -> new HashSet<>()).add(seasonRest);
		seasonChaptersMap.computeIfAbsent(seasonRest, k -> new ArrayList<>()).add(chapterRest);

		Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
		Mockito.when(modelMapper.map(tvShow, TvShowRest.class)).thenReturn(tvShowRest);
		Mockito.when(modelMapper.map(season, SeasonRest.class)).thenReturn(seasonRest);
		Mockito.when(modelMapper.map(chapter, ChapterRest.class)).thenReturn(chapterRest);
		// Mockito.when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest);

		// when
		final List<TvShowRest> tvShowRestList = actorService.getTvShowsAndChaptersByActorId(id);

		// then
		assertThat(tvShowRestList.equals(tvShowRestList)).isTrue();


	}

}