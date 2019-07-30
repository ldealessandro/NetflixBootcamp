package com.everis.d4i.tutorial.utils.constants;

public class ExceptionConstants {

	public static final String ERROR = "ERROR";

	public static final String MESSAGE_INEXISTENT_SEASON = "SEASON INEXISTENT - Season does not exist";
	public static final String MESSAGE_INEXISTENT_CHAPTER = "CHAPTER INEXISTENT - Chapter does not exist";
	public static final String MESSAGE_INEXISTENT_ACTOR = "ACTOR INEXISTENT - Actor does not exist";
	public static final String MESSAGE_INEXISTENT_TV_SHOW = "TV_SHOW INEXISTENT - Tv Show does not exist";
	public static final String MESSAGE_ACTOR_DELETED = "ACTOR DELETED - The actor was deleted sucefully";
	
	
	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR - An internal server error has ocurred";

	public static final String MESSAGE_INEXISTENT_CATEGORY = "CATEGORY INEXISTENT - Category does not exist";;

	private ExceptionConstants() {
		throw new IllegalStateException("Utility Class");
	}

}
