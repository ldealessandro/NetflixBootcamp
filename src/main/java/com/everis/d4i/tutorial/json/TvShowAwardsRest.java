package com.everis.d4i.tutorial.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TvShowAwardsRest implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	private Long id;
	private String year;
	
	@JsonIgnore
	private TvShowRest tvShow;
	
	private AwardRest award;
	private AwardCategoryRest awardCategory;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public TvShowRest getTvShow() {
		return tvShow;
	}
	public void setTvShow(TvShowRest tvShow) {
		this.tvShow = tvShow;
	}
	public AwardRest getAward() {
		return award;
	}
	public void setAward(AwardRest award) {
		this.award = award;
	}
	public AwardCategoryRest getAwardCategory() {
		return awardCategory;
	}
	public void setAwardCategory(AwardCategoryRest awardCategory) {
		this.awardCategory = awardCategory;
	}

}
