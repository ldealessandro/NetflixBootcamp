package com.everis.d4i.tutorial.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorsChapterRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class ActorServiceImpl implements ActorService {

//	private static final Logger LOGGER = LoggerFactory.getLogger(ActorServiceImpl.class);

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ActorRest> getActors() throws NetflixException {

		return actorRepository.findAll().stream().map(actor -> modelMapper.map(actor, ActorRest.class))
				.collect(Collectors.toList());

	}

	@Override
	public ActorRest getActorById(final Long id) throws NetflixException {

		final Actor actor = actorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));

		return modelMapper.map(actor, ActorRest.class);

	}
	
    @Override
    public ActorRest getActorByName(String name) {
    	
    	final Actor actor = actorRepository.findByName(name);

		return modelMapper.map(actor, ActorRest.class);
    }

	@Override
	public ActorRest createActor(final ActorRest actorRest) throws NetflixException {

		final Actor actor = modelMapper.map(actorRest, Actor.class);
		return modelMapper.map(actorRepository.save(actor), ActorRest.class);

	}

	@Override
	public String deleteActor(final Long id) throws NetflixException {

		final Actor actor = actorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));

		actorRepository.deleteById(actor.getId());
		return ExceptionConstants.MESSAGE_ACTOR_DELETED;

	}

	@Override
	public ActorRest updateActor(final ActorRest actorRest) throws NetflixException {
		final Actor actor = actorRepository.findById(actorRest.getId())
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));

		actor.setId(actorRest.getId());
		actor.setName(actorRest.getName());
		actor.setDateOfBirth(actorRest.getDateOfBirth());
		actor.setCountry(actorRest.getCountry());

		return modelMapper.map(actorRepository.save(actor), ActorRest.class);
	}

	@Override
	public List<ActorRest> getActorsByChapter(final Long chapterId) throws NetflixException {

		return actorRepository.findByActorsChapterChapterId(chapterId).stream()
				.map(actor -> modelMapper.map(actor, ActorRest.class)).collect(Collectors.toList());

	}

	@Override
	public List<TvShowRest> getTvShowsAndChaptersByActorId(Long actorId) throws NetflixException {

		final Actor actor = actorRepository.findById(actorId)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));

		final List<ActorsChapterRest> actorsChapterRestList = actor.getActorsChapter().stream()
				.map(actorsChapter -> modelMapper.map(actorsChapter, ActorsChapterRest.class))
				.collect(Collectors.toList());

		final List<ChapterRest> chapterList = new ArrayList<ChapterRest>();
		final Set<SeasonRest> seasonSet = new HashSet<SeasonRest>();
		final Set<TvShowRest> tvShowSet = new HashSet<TvShowRest>();

		actorsChapterRestList.forEach(actorsChapterRest -> {
			chapterList.add(modelMapper.map(actorsChapterRest.getChapter(), ChapterRest.class));
			seasonSet.add(modelMapper.map(actorsChapterRest.getChapter().getSeason(), SeasonRest.class));
			tvShowSet.add(modelMapper.map(actorsChapterRest.getChapter().getSeason().getTvShow(), TvShowRest.class));
		});

		final List<SeasonRest> seasonRestList = seasonSet.stream().map(season -> {
			season.setChapters(chapterList.stream().filter(chapter -> chapter.getSeason().getId() == season.getId())
					.collect(Collectors.toList()));
			return season;
		}).collect(Collectors.toList());

		final List<TvShowRest> tvShowRestList = tvShowSet.stream().map(tvShow -> {
			tvShow.setSeasons(seasonRestList.stream().filter(season -> season.getTvShow().getId() == tvShow.getId())
					.collect(Collectors.toList()));
			return tvShow;
		}).collect(Collectors.toList());

		return tvShowRestList;
	}


}
