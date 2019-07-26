package com.everis.d4i.tutorial.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TvShowCategoriesRest implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	private Long id;
	
	@JsonIgnore
	private TvShowRest tvShow;
	
	private CategoryRest category;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TvShowRest getTvShow() {
		return tvShow;
	}
	public void setTvShow(TvShowRest tvShow) {
		this.tvShow = tvShow;
	}
	public CategoryRest getCategory() {
		return category;
	}
	public void setCategory(CategoryRest category) {
		this.category = category;
	}


}
