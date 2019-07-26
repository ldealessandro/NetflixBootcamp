package com.everis.d4i.tutorial.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActuacionRest implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	private Long id;
	private String serie;
	private short temporada;
	private short capitulo;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}
	
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public short getTemporada() {
		return temporada;
	}

	public void setTemporada(short temporada) {
		this.temporada = temporada;
	}

	public short getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(short capitulo) {
		this.capitulo = capitulo;
	}

	

}
