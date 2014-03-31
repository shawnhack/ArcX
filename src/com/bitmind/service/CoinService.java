package com.bitmind.service;

import org.joda.money.Money;

import com.bitmind.dao.entity.Address;
import com.bitmind.domain.AssetType;

public interface CoinService {

	public Address getAddress(String addressString);

	public Long getAddressBalance(String addressString);

	public Money getLastPrice();

	public void buildReader(AssetType type);

}