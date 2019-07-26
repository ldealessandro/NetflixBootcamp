package com.everis.d4i.tutorial.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorsChapterRest implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	private Long id;
	private ChapterRest chapter;
	private ActorRest actor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ChapterRest getChapter() {
		return chapter;
	}
	public void setChapter(ChapterRest chapter) {
		this.chapter = chapter;
	}
	public ActorRest getActor() {
		return actor;
	}
	public void setActor(ActorRest actor) {
		this.actor = actor;
	}
	
	

}
