package com.bitmind.util;

import org.joda.time.DateTime;
import org.joda.time.Months;

public class PriceCalculator {

	private static final DateTime START = new DateTime(2009, 1, 1, 0, 0);
	private static final double a = 0.092;
	private static final double b = -2.9124;

	public double calculatePrice(DateTime end) {
		double price = 0;
		double months = Months.monthsBetween(START, end).getMonths();
		double days = end.getDayOfMonth();
		double dayFraction = (days / end.dayOfMonth().getMaximumValue());
		months += dayFraction;
		double exp = (months * a) + b;
		price = Math.pow(10, exp);
		return price;
	}
}
