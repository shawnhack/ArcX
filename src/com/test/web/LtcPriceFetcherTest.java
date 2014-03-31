package com.test.web;

import org.joda.money.Money;
import org.junit.Test;

import com.bitmind.web.PriceFetcher;
import com.bitmind.web.ltc.LtcPriceFetcher;
import com.test.AbstractTest;

public class LtcPriceFetcherTest extends AbstractTest {

	@Test
	public void testGetLastPrice() {
		PriceFetcher fetcher = new LtcPriceFetcher();
		// PriceFetcher fetcher = new PtsPriceFetcher();
		Money last = fetcher.getLastPrice();

		System.out.println(last);

	}

}
