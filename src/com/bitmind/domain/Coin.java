package com.bitmind.domain;

import java.io.Serializable;

public abstract class Coin implements Serializable {

	private static final long serialVersionUID = 1L;

	private String price;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
