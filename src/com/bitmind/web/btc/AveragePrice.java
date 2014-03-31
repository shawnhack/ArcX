package com.bitmind.web.btc;

import com.bitmind.web.Price;

public class AveragePrice implements Price {

	private String last;
	private String timestamp;

	@Override
	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	@Override
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
