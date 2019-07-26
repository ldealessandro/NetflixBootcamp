package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TV_SHOW_CATEGORIES")
public class TvShowCategories implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TV_SHOW_ID", nullable = false)
	private TvShow tvShow;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
}