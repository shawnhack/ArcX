package com.bitmind.dao.entity;

import com.googlecode.objectify.annotation.Id;

public abstract class AbstractEntity {

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
