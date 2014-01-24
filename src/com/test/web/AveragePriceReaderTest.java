package com.test.web;

import org.joda.money.Money;
import org.junit.Test;

import com.bitmind.web.PriceFetcher;
import com.bitmind.web.btc.btcaverage.AveragePriceFetcher;

public class AveragePriceReaderTest {

	@Test
	public void testGetLastPrice() {
		PriceFetcher reader = new AveragePriceFetcher();
		Money last = reader.getLastPrice();

		System.out.println(last);

	}

}
