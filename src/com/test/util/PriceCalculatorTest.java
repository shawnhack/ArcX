package com.test.util;

import org.joda.time.DateTime;
import org.junit.Test;

import com.bitmind.util.PriceCalculator;

public class PriceCalculatorTest {

	@Test
	public void testCalculatePrice() {

		int days = 0;
		double price = new PriceCalculator().calculatePrice(new DateTime()
				.plusDays(days));
		System.out.println(price);
	}

}
