package com.test.service;

import org.joda.money.Money;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitmind.dao.entity.Address;
import com.bitmind.domain.AssetType;
import com.bitmind.service.CoinService;

public class CoinServiceTest extends AbstractServiceTest {

	@Autowired
	private CoinService coinService;

	@Test
	public void testGetAddress() {

		String addressString = "18iPs6ymH81bTrTKatgDvXhznuTPjoop6U";

		coinService.buildReader(AssetType.BTC);
		Address address = coinService.getAddress(addressString);

		System.out.println(address.getAddress());
	}

	@Test
	public void testGetPrice() {

		coinService.buildReader(AssetType.BTC);
		Money price = coinService.getLastPrice();

		System.out.println(price);
	}

}
