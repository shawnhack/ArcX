package com.bitmind.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractPriceFetcher implements PriceFetcher {

	private static Logger log = LoggerFactory
			.getLogger(AbstractPriceFetcher.class);

	private final Gson gson = new GsonBuilder().create();
	private Money lastPrice;
	private DateTime lastTime;

	private final String url;
	private final String dateFormat;
	private int waitTime = 1;

	private final Class<? extends Price> returnType;

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public String getUrl() {
		return url;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public AbstractPriceFetcher(Class<? extends Price> returnType, String url,
			String dateFormat) {
		super();
		this.returnType = returnType;
		this.url = url;
		this.dateFormat = dateFormat;
	}

	@Override
	public Money getLastPrice() {

		// Only call once every minute
		if (lastTime == null
				|| lastTime.plusMinutes(getWaitTime()).isAfter(new DateTime())) {

			try {
				String urlString = getUrl();
				String json = new WebConnection().loadPage(urlString);
				Price ticker = gson.fromJson(json, returnType);
				if (ticker != null) {
					BigDecimal amount = new BigDecimal(ticker.getLast());

					lastPrice = Money.of(CurrencyUnit.USD, amount,
							RoundingMode.FLOOR);

					DateTimeFormatter dateStringFormat = DateTimeFormat
							.forPattern(getDateFormat());

					lastTime = dateStringFormat.parseDateTime(ticker
							.getTimestamp());
				}

			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}

		// log.info("Last call: " + lastTime);
		// log.info("Last price: " + lastPrice);

		return lastPrice;
	}
}
