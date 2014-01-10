package com.bitmind.service;

import java.math.BigDecimal;

public interface CoinService {

	public BigDecimal getAddressBalance(String addressString);

}