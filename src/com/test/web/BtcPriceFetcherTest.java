package com.test.web;

import org.joda.money.Money;
import org.junit.Test;

import com.bitmind.web.PriceFetcher;
import com.bitmind.web.btc.BtcPriceFetcher;
import com.test.AbstractTest;

public class BtcPriceFetcherTest extends AbstractTest {

	@Test
	public void testGetLastPrice() {
		PriceFetcher fetcher = new BtcPriceFetcher();
		Money last = fetcher.getLastPrice();

		System.out.println(last);

	}

}
