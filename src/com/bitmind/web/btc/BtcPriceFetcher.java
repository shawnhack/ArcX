package com.bitmind.web.btc;

import com.bitmind.web.AbstractPriceFetcher;

public class BtcPriceFetcher extends AbstractPriceFetcher {

	private static final String URL = "http://api.bitcoinaverage.com/ticker/USD";
	private static final String DATE_FORMAT = "EEE, dd MMM y HH:mm:ss Z";

	public BtcPriceFetcher() {
		super(AveragePrice.class, URL, DATE_FORMAT);
	}

}
