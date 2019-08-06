package com.everis.d4i.tutorial.utils.constants;

public class RestConstants {

	public static final String APPLICATION_NAME = "/netflix";
	public static final String API_VERSION_1 = "/v1";
	public static final String SUCCESS = "Success";

	public static final String RESOURCE_CATEGORY = "/categories";
	public static final String RESOURCE_TV_SHOW = "/tv-shows";
	public static final String RESOURCE_SEASON = "/tv-shows/{tvShowId}/seasons";
	public static final String RESOURCE_CHAPTER = "/tv-shows/{tvShowId}/seasons/{seasonNumber}/chapters";
	public static final String RESOURCE_ID = "/{id}";
	public static final String RESOURCE_NUMBER = "/{number}";
	public static final String RESOURCE_NAME = "/{name}";
	
	public static final String PARAMETER_CATEGORY = "categories";
	
	public static final String RESOURCE_ACTOR = "/actors";
	public static final String PARAMETER_ACTOR = "actors";
	public static final String PARAMETER_ACTOR_UPDATE = "actorModify";
		
	public static final String RESOURCE_AWARD = "/awards";
	public static final String PARAMETER_AWARD = "awards";
	
	public static final String RESOURCE_TVSHOW_AWARDS = "/tvShowAwards";
	public static final String PARAMETER_TVSHOW_AWARDS = "tvShowAwards";
	
	
	public static final String RESOURCE_ACTORID = "/getActorById/{actorId}";
	public static final String RESOURCE_CHAPTERID = "/getActorById/{chapterId}";
	public static final String RESOURCE_DELETE_ACTOR = "/deleteActor/{actorId}";
	public static final String RESOURCE_UPDATE_ACTOR = "/updateActor";
	
	public static final String PARAMETER_ACTORBYCHAPTERID = "/getActorsByChapter/{chapterId}";
	public static final String PARAMETER_TVSHOWCHAPTERSBYACTOR = "/getTvShowsChaptersByActor/{actorId}";
	
	public static final String RESOURCE_TV_SHOW_RATES = "/tv-shows-rates";

	private RestConstants() {
		throw new IllegalStateException("Utility Class");
	}

}
