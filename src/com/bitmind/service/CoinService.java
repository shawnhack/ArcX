package com.bitmind.service;

import com.bitmind.dao.entity.Address;

public interface CoinService {

	public Address getAddress(String addressString);

	public Long getAddressBalance(String addressString);

	public String getLastPrice();

}