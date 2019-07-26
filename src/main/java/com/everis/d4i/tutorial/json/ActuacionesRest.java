package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.everis.d4i.tutorial.entities.TvShow;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActuacionesRest implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	private Long id;
	private Set<TvShow> series;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Set<TvShow> getSeries() {
		return series;
	}

	public void setSeries(Set<TvShow> series) {
		this.series = series;
	}

	
	
		

}
