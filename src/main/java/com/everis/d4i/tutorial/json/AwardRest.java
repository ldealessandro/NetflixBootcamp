package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AwardRest implements Serializable {

	private static final long serialVersionUID = 180802329613616000L;

	private Long id;
	private String name;
	
	@JsonIgnore
	private String	description;
	
	@JsonIgnore
	private List<TvShowAwardsRest> tvShowAwards;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TvShowAwardsRest> getTvShowAwards() {
		return tvShowAwards;
	}

	public void setTvShowAwards(List<TvShowAwardsRest> tvShowAwards) {
		this.tvShowAwards = tvShowAwards;
	}



}
