package com.everis.d4i.tutorial.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AwardCategoryRest implements Serializable {

	private static final long serialVersionUID = 180802329613616000L;

	private Long id;
	private String	description;

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



}
