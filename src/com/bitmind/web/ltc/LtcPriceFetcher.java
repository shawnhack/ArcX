package com.bitmind.web.ltc;


public class LtcPriceFetcher extends AltPriceFetcher {

	private static final String SUFFIX = "ltc_usd";

	public LtcPriceFetcher() {
		super(SUFFIX);
	}
}
