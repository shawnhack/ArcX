package com.bitmind.util;

import java.math.BigDecimal;

public class PriceConverter {

	private static final BigDecimal SATOSHIS = new BigDecimal("100000000");

	public static BigDecimal satoshiToBtc(long satoshi) {
		BigDecimal btc = BigDecimal.valueOf(satoshi);
		btc = btc.divide(SATOSHIS);
		return btc;
	}

}
