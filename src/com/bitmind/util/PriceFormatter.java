package com.bitmind.util;

import java.math.BigDecimal;

public class PriceFormatter {

	public static String toBtc(long satoshi) {
		String btcString = "";
		BigDecimal btc = PriceConverter.satoshiToBtc(satoshi);

		btcString = btc.toPlainString() + " BTC";

		return btcString;
	}

}
