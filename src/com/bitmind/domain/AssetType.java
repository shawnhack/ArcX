package com.bitmind.domain;

public enum AssetType {

	BTC("Bitcoin", "BTC"), LTC("Litecoin", "LTC"), PTS("ProtoShares", "PTS");

	private String name;
	private String symbol;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	private AssetType(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

}
