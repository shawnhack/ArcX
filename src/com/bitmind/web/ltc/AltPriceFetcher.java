package com.bitmind.web.ltc;

import com.bitmind.web.AbstractPriceFetcher;

public class AltPriceFetcher extends AbstractPriceFetcher {

	private static final String URL = "http://www.cryptocoincharts.info/v2/api/tradingPair/";
	private static final String DATE_FORMAT = "yyyy-mm-dd HH:mm:ss";

	public AltPriceFetcher(String suffix) {
		super(CryptochartPrice.class, URL + suffix, DATE_FORMAT);
	}

}
