package com.bitmind.web.btc.btcaverage;

import java.io.IOException;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.bitmind.web.PriceFetcher;
import com.bitmind.web.WebConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AveragePriceFetcher implements PriceFetcher {

	private final Gson gson = new GsonBuilder().create();
	private final static String DOMAIN = "http://api.bitcoinaverage.com/";
	private final static String TICKER = "ticker/";
	private final static String CODE_USD = "USD";
	private Money lastPrice;
	private DateTime lastTime;
	private final DateTimeFormatter dateStringFormat = DateTimeFormat
			.forPattern("EEE, dd MMM y HH:mm:ss Z");

	@Override
	public Money getLastPrice() {

		// Only call once every minute
		if (lastTime == null || lastTime.plusMinutes(1).isAfter(new DateTime())) {
			try {
				String urlString = DOMAIN + TICKER + CODE_USD;
				String json = WebConnection.loadPage(urlString);
				AverageTicker ticker = gson.fromJson(json, AverageTicker.class);
				lastPrice = Money.parse(CODE_USD + " " + ticker.getLast());

				lastTime = dateStringFormat
						.parseDateTime(ticker.getTimestamp());

				System.out.println(lastTime);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return lastPrice;
	}
}
