package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeasonRest implements Serializable {

	private static final long serialVersionUID = 180802329613616000L;

	private Long id;
	private short number;
	private String name;
	
	
	private List<ChapterRest> chapters;
	
	@JsonIgnore
	private TvShowRest tvShow;
	
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public short getNumber() {
		return number;
	}

	public void setNumber(final short number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<ChapterRest> getChapters() {
		return chapters;
	}

	public void setChapters(List<ChapterRest> chapters) {
		this.chapters = chapters;
	}

	public TvShowRest getTvShow() {
		return tvShow;
	}

	public void setTvShow(TvShowRest tvShow) {
		this.tvShow = tvShow;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeasonRest other = (SeasonRest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
