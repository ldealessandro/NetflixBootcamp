package com.everis.d4i.tutorial.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorsChapterRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

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

		when(actorRepository.findAll()).thenReturn(actorList);
		when(modelMapper.map(actor1, ActorRest.class)).thenReturn(actorRest1);
		when(modelMapper.map(actor2, ActorRest.class)).thenReturn(actorRest2);

		// when
		final List<ActorRest> actorRestList = actorService.getActors();

		// then
		assertThat(actorRestList).contains(actorRest1, actorRest2);
	}

	@Test
	public void getActorById() throws NetflixException {

		// given
		final long id = 1L;

		final Actor actor = new Actor();
		actor.setId(id);

		final ActorRest actorRest = new ActorRest();

		when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
		when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest);

		// when
		final ActorRest actorRestFinal = actorService.getActorById(id);

		// then
		assertThat(actorRestFinal).isSameAs(actorRest);

	}

	@Test(expected = NotFoundException.class)
	public void getActorByIdNotFound() throws NetflixException {

		// given
		final long id = 1L;
		when(actorRepository.findById(id)).thenReturn(Optional.empty());

		// when
		actorService.getActorById(id);

	}

	@Test
	public void createActor() throws NetflixException {

		// given
		final Actor actor = new Actor();
		final ActorRest actorRest = new ActorRest();
		when(actorRepository.save(Mockito.any(Actor.class))).thenReturn(actor);
		when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest);

		// when
		ActorRest actorRestOut = actorService.createActor(actorRest);

		// then
		assertThat(actorRestOut).isEqualTo(actorRest);
	}

	@Test
	public void deleteActor() throws NetflixException {
		final Long id = 1L;
		final Actor actor = new Actor();
		actor.setId(id);

		when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
		String message = actorService.deleteActor(id);

		assertThat(message).isEqualTo(ExceptionConstants.MESSAGE_ACTOR_DELETED);
	}

	@Test(expected = NotFoundException.class)
	public void testDeleteWhenActorNotFound() throws NetflixException {
		// given
		final long id = 1L;
		when(actorRepository.findById(id)).thenReturn(Optional.empty());

		// when
		actorService.deleteActor(id);

	}

	@Test
	public void updateActor() throws NetflixException {
		// given
		final long id = 1L;
		final Actor actor = new Actor();
		final ActorRest actorRest1 = new ActorRest();
		actorRest1.setId(id);

		when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
		when(actorRepository.save(actor)).thenReturn(actor);
		when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest1);

		// when
		final ActorRest actorRest2 = actorService.updateActor(actorRest1);

		// then
		assertThat(actorRest2.getId()).isEqualTo(id);

	}
	
	@Test(expected = NotFoundException.class)
	public void updateActorNotFound() throws NetflixException {

		final ActorRest actorRest = new ActorRest();

		when(actorRepository.findById(actorRest.getId())).thenReturn(Optional.empty());

		// when
		actorService.updateActor(actorRest);


	}
	@Test
	public void getActorsByChapter() throws NetflixException {
		
		// given
		final long id = 1L;
		final Chapter chapter = new Chapter();
		chapter.setId(id);
		final Actor actor = new Actor();

		final ActorsChapter actorsChapter = new ActorsChapter();
		chapter.setActorsChapter(Collections.singletonList(actorsChapter));
		actor.setActorsChapter(Collections.singletonList(actorsChapter));
		
		final List<Actor> actorList = Arrays.asList(actor);

		final ActorRest actorRest = new ActorRest();

		when(actorRepository.findByActorsChapterChapterId(id)).thenReturn(actorList);
		when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest);
		
		// when
		final List<ActorRest> actorRestList = actorService.getActorsByChapter(id);

		// then
		assertThat(actorRestList).contains(actorRest);

	}

	@Test(expected = NotFoundException.class)
	public void getTvShowsAndChaptersByActorNotFound() throws NetflixException {

		// given
		final long id = 1L;
		final Actor actor = new Actor();
		actor.setId(id);
		when(actorRepository.findById(id)).thenReturn((Optional.empty()));

	
		// when
		actorService.getTvShowsAndChaptersByActorId(id);


	}
	@Test
	public void getTvShowsAndChaptersByActor() throws NetflixException {

		// given
		final long id = 1L;
		final Actor actor = new Actor();
		when(actorRepository.findById(id)).thenReturn(Optional.of(actor));

		final ActorsChapter actorsChapter = new ActorsChapter();
		final ActorsChapter actorsChapter2 = new ActorsChapter();
		final List<ActorsChapter> actorsChapterList = new ArrayList<>();
		actorsChapterList.add(actorsChapter);
		actorsChapterList.add(actorsChapter2);
		
		actor.setActorsChapter(actorsChapterList);
		
		final ActorsChapterRest actorsChapterRest = new ActorsChapterRest();
		when(modelMapper.map(actorsChapter, ActorsChapterRest.class)).thenReturn(actorsChapterRest);

		final ChapterRest chapterRest = new ChapterRest();
		actorsChapterRest.setChapter(chapterRest);
		when(modelMapper.map(chapterRest, ChapterRest.class)).thenReturn(chapterRest);

		final SeasonRest seasonRest = new SeasonRest();
		seasonRest.setId(2L);
		chapterRest.setSeason(seasonRest);
		when(modelMapper.map(seasonRest, SeasonRest.class)).thenReturn(seasonRest);

		final TvShowRest tvShowRest = new TvShowRest();
		/*when(modelMapper.map(actorsChapterRest.getChapter().getSeason().getTvShow(), TvShowRest.class))
				.thenReturn(tvShowRest);*/
		
		tvShowRest.setId(4L);
		seasonRest.setTvShow(tvShowRest);
		when(modelMapper.map(tvShowRest, TvShowRest.class)).thenReturn(tvShowRest);
		//CREAR CHAPTER, SEASON Y TVSHOW para probar los FILTER
				final ActorsChapterRest actorsChapterRest2 = new ActorsChapterRest();
				when(modelMapper.map(actorsChapter2, ActorsChapterRest.class)).thenReturn(actorsChapterRest2);

				final ChapterRest chapterRest2 = new ChapterRest();
				actorsChapterRest2.setChapter(chapterRest2);
				when(modelMapper.map(chapterRest2, ChapterRest.class)).thenReturn(chapterRest2);
				
				final SeasonRest seasonRest2 = new SeasonRest();
				seasonRest2.setId(22L);
				chapterRest2.setSeason(seasonRest2);
				when(modelMapper.map(seasonRest2, SeasonRest.class)).thenReturn(seasonRest2);

				final TvShowRest tvShowRest2 = new TvShowRest();
				/*when(modelMapper.map(actorsChapterRest2.getChapter().getSeason().getTvShow(), TvShowRest.class))
						.thenReturn(tvShowRest2);*/
				
				tvShowRest2.setId(44L);
				seasonRest2.setTvShow(tvShowRest2);
				when(modelMapper.map(tvShowRest2, TvShowRest.class)).thenReturn(tvShowRest2);
		//-------------------------------------			
		List<TvShowRest> tvShowRestList = new ArrayList<>();
		tvShowRestList.add(tvShowRest);
		// when
		List<TvShowRest> tvShowRestListFinal = actorService.getTvShowsAndChaptersByActorId(id);

		// then
		assertThat(tvShowRestListFinal).containsAll(tvShowRestList);

	}

}