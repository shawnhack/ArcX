package com.bitmind.web.ltc;

import com.bitmind.web.Price;
import com.google.gson.annotations.SerializedName;

public class CryptochartPrice implements Price {

	private String id;
	private String price;
	@SerializedName("price_before_24h")
	private String priceBefore;
	@SerializedName("volume_first")
	private String volumeFirst;
	@SerializedName("volume_second")
	private String volumeSecond;
	@SerializedName("volume_btc")
	private String volumeBtc;
	@SerializedName("best_market")
	private String bestMarket;
	@SerializedName("latest_trade")
	private String latestTrade;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPriceBefore() {
		return priceBefore;
	}

	public void setPriceBefore(String priceBefore) {
		this.priceBefore = priceBefore;
	}

	public String getVolumeFirst() {
		return volumeFirst;
	}

	public void setVolumeFirst(String volumeFirst) {
		this.volumeFirst = volumeFirst;
	}

	public String getVolumeSecond() {
		return volumeSecond;
	}

	public void setVolumeSecond(String volumeSecond) {
		this.volumeSecond = volumeSecond;
	}

	public String getVolumeBtc() {
		return volumeBtc;
	}

	public void setVolumeBtc(String volumeBtc) {
		this.volumeBtc = volumeBtc;
	}

	public String getBestMarket() {
		return bestMarket;
	}

	public void setBestMarket(String bestMarket) {
		this.bestMarket = bestMarket;
	}

	public String getLatestTrade() {
		return latestTrade;
	}

	public void setLatestTrade(String latestTrade) {
		this.latestTrade = latestTrade;
	}

	@Override
	public String getLast() {
		return price;
	}

	@Override
	public String getTimestamp() {
		return latestTrade;
	}

}
