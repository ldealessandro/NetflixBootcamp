package com.everis.d4i.tutorial.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
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

	private ModelMapper modelMapper = new ModelMapper();

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
	public ActorRest createActor(final ActorRest actorRest) throws NetflixException {

		final Actor actorIn = new Actor();
		actorIn.setName(actorRest.getName());
		actorIn.setDateOfBirth(actorRest.getDateOfBirth());
		actorIn.setCountry(actorRest.getCountry());

		// actorRepository.findByName(actor.getName()).orElseThrow(() -> new
		// ConflictException("ALREADY EXISTS AN ACTOR WITH THE SAME NAME"));

		final Actor actorOut = actorRepository.save(actorIn);

		return modelMapper.map(actorOut, ActorRest.class);
		
	}

	@Override
	public String deleteActor(final Long id) throws NetflixException {

		if (!actorRepository.existsById(id)) {
			throw new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR);
		}

		actorRepository.deleteById(id);
		return "actor deleted sucefully";

	}

	@Override
	public ActorRest modifyActor(final ActorRest actorRest) throws NetflixException {
		Actor actor = new Actor();
		actor.setId(actorRest.getId());
		actor.setName(actorRest.getName());
		actor.setDateOfBirth(actorRest.getDateOfBirth());
		actor.setCountry(actorRest.getCountry());
		
			if (!actorRepository.existsById(actor.getId())) {
				throw new InternalServerErrorException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR);
			}
			actorRepository.save(actor);

		return modelMapper.map(actor, ActorRest.class);
	}

	@Override
	public List<ActorRest> getActorsByChapter(final Long chapterId) throws NetflixException {

			return actorRepository.findByActorsChapterChapterId(chapterId).stream()
					.map(actor -> modelMapper.map(actor, ActorRest.class)).collect(Collectors.toList());

	}

	
	/*  @Override public List<TvShowRest> getTvShowsAndChaptersByActorId(Long
	  actorId) throws NetflixException { final Actor actor =
	  actorRepository.findById(actorId).orElseThrow(() -> new
	  NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));
	  
	  final List<ActorsChapterRest> actorsChapterRestList =
	  actor.getActorsChapter().stream() .map(actorsChapter ->
	  modelMapper.map(actorsChapter,
	  ActorsChapterRest.class)).collect(Collectors.toList());
	  
	  final List<ChapterRest> chapterList = new ArrayList<ChapterRest>(); final
	  Set<SeasonRest> seasonSet = new HashSet<SeasonRest>(); final Set<TvShowRest>
	  tvShowSet = new HashSet<TvShowRest>(); actorsChapterRestList.forEach(x -> {
	  chapterList.add(modelMapper.map(x.getChapter(), ChapterRest.class));
	  seasonSet.add(modelMapper.map(x.getChapter().getSeason(), SeasonRest.class));
	  tvShowSet.add( modelMapper.map(x.getChapter().getSeason().getTvShow(),
	  TvShowRest.class)); });
	  
	  List<SeasonRest> seasonRestList = new ArrayList<>(); seasonSet.forEach(season
	  -> { final List<ChapterRest> chapterRestListTemp = new ArrayList<>();
	  chapterList.forEach(chapter -> { if
	  (chapter.getSeason().getId()==season.getId())
	  {chapterRestListTemp.add(chapter);} });
	  season.setChapters(chapterRestListTemp); seasonRestList.add(season); });
	  final List<TvShowRest> tvShowRestList = new ArrayList<TvShowRest>();
	  tvShowSet.forEach(tvShow -> { final List<SeasonRest> seasonRestListTemp = new
	  ArrayList<>(); seasonRestList.forEach(season -> {
	  if(tvShow.getId()==season.getTvShow().getId())
	  {seasonRestListTemp.add(season);} }); tvShow.setSeasons(seasonRestListTemp);
	  if(!tvShowRestList.contains(tvShow))tvShowRestList.add(tvShow);
	  
	  }); return tvShowRestList; }*/
	 

	@Override
	public List<TvShowRest> getTvShowsAndChaptersByActorId(Long id) throws NetflixException {
		final Actor actor = actorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));

		final Map<TvShowRest, Set<SeasonRest>> tvShowSeasonMap = new HashMap<>(); // TvShowRest - Set SeasonRest
		final Map<SeasonRest, List<ChapterRest>> seasonChaptersMap = new HashMap<>(); // SeasonRest - List ChapterRest

		final Set<SeasonRest> seasonSet = new HashSet<SeasonRest>();

		actor.getActorsChapter().forEach(chapterActor -> {
			final TvShowRest tvShowRest = modelMapper.map(chapterActor.getChapter().getSeason().getTvShow(),
					TvShowRest.class);
			final SeasonRest seasonRest = modelMapper.map(chapterActor.getChapter().getSeason(), SeasonRest.class);
			final ChapterRest chapterRest = modelMapper.map(chapterActor.getChapter(), ChapterRest.class);

			seasonSet.add(seasonRest);

			tvShowSeasonMap.computeIfAbsent(tvShowRest, k -> new HashSet<>()).add(seasonRest);
			seasonChaptersMap.computeIfAbsent(seasonRest, k -> new ArrayList<>()).add(chapterRest);
		});

		final List<TvShowRest> tvShowList = new ArrayList<>();

		
		for (final TvShowRest tvShow : tvShowSeasonMap.keySet()) {
			for (final SeasonRest season : tvShow.getSeasons()) {
				season.setChapters(seasonChaptersMap.get(season));
			}
			tvShowList.add(tvShow);
		}
		return tvShowList;
	}
}
