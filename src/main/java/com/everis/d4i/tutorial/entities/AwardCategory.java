package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AWARD_CATEGORIES")
public class AwardCategory implements Serializable {

	private static final long serialVersionUID = 180802329613616000L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DESCRIPTION", unique = true)
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "awardCategory")
	private List<TvShowAwards> tvShowAwards;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TvShowAwards> getTvShowAwards() {
		return tvShowAwards;
	}

	public void setTvShowAwards(List<TvShowAwards> tvShowAwards) {
		this.tvShowAwards = tvShowAwards;
	}



}
