package com.bitmind.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.Money;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

public class PriceConverter {

	private static final BigDecimal SATOSHIS = new BigDecimal("100000000");

	public static BigDecimal satoshiToBtc(long satoshi) {
		BigDecimal btc = BigDecimal.valueOf(satoshi);
		btc = btc.divide(SATOSHIS);
		return btc;
	}

	public static long btcToSatoshi(BigDecimal btc) {
		btc = btc.multiply(SATOSHIS);
		return btc.longValue();
	}

	public static String getDisplayPrice(Money price) {
		MoneyFormatter formatter = new MoneyFormatterBuilder()
				.appendCurrencySymbolLocalized().appendAmount().toFormatter();
		String formattedPrice = formatter.print(price);
		return formattedPrice;
	}

	public static Money getWorth(long satoshi, Money price) {
		Money worth = price.multipliedBy(satoshiToBtc(satoshi),
				RoundingMode.DOWN);
		return worth;
	}

	public static String getDisplayBalance(long satoshi, String symbol) {
		String btcString = "";
		BigDecimal btc = PriceConverter.satoshiToBtc(satoshi);
		btcString = btc.toPlainString() + " " + symbol;
		return btcString;
	}

}
