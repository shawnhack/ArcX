package com.test.web;

import org.joda.money.Money;
import org.junit.Test;

import com.bitmind.web.PriceReader;
import com.bitmind.web.btc.btcaverage.AveragePriceReader;

public class AveragePriceReaderTest {

	@Test
	public void testGetLastPrice() {
		PriceReader reader = new AveragePriceReader();
		Money last = reader.getLastPrice();

		System.out.println(last);

	}

}
