package com.bitmind.util;

import java.math.BigDecimal;

public class PriceFormatter {

	public static String toBtc(BigDecimal btc) {
		String btcString = "";

		if (btc != null) {
			btcString = btc.toPlainString() + " BTC";
		}

		return btcString;
	}

}
