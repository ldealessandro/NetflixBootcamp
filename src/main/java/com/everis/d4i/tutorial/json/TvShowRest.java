package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.time.Year;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TvShowRest implements Serializable {

	private static final long serialVersionUID = 4916713904971425156L;

	private Long id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private Year year;
	private byte recommendedAge;
	private String advertising;
	
	private List<SeasonRest> seasons;

	@JsonIgnore
	private List<TvShowAwardsRest> tvShowAwards;
	
	@JsonIgnore
	private List<TvShowCategoriesRest> tvShowCategories;
	
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(final String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(final String longDescription) {
		this.longDescription = longDescription;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(final Year year) {
		this.year = year;
	}

	public byte getRecommendedAge() {
		return recommendedAge;
	}

	public void setRecommendedAge(final byte recommendedAge) {
		this.recommendedAge = recommendedAge;
	}

	public String getAdvertising() {
		return advertising;
	}

	public void setAdvertising(final String advertising) {
		this.advertising = advertising;
	}

	public List<SeasonRest> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<SeasonRest> seasons) {
		this.seasons = seasons;
	}

	public List<TvShowAwardsRest> getTvShowAwards() {
		return tvShowAwards;
	}

	public void setTvShowAwards(List<TvShowAwardsRest> tvShowAwards) {
		this.tvShowAwards = tvShowAwards;
	}

	public List<TvShowCategoriesRest> getTvShowCategories() {
		return tvShowCategories;
	}

	public void setTvShowCategories(List<TvShowCategoriesRest> tvShowCategories) {
		this.tvShowCategories = tvShowCategories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TvShowRest other = (TvShowRest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
