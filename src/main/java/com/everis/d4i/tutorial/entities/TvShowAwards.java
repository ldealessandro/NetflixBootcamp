package com.everis.d4i.tutorial.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TV_SHOW_AWARDS")
public class TvShowAwards implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "YEAR")
	private String year;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TV_SHOW_ID", nullable = false)
	private TvShow tvShow;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AWARD_ID", nullable = false)
	private Award award;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AWARD_CATEGORY_ID", nullable = false)
	private AwardCategory awardCategory;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public TvShow getTvShow() {
		return tvShow;
	}

	public void setTvShow(TvShow tvShow) {
		this.tvShow = tvShow;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public AwardCategory getAwardCategory() {
		return awardCategory;
	}

	public void setAwardCategory(AwardCategory awardCategory) {
		this.awardCategory = awardCategory;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
}