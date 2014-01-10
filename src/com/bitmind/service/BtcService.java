package com.bitmind.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bitmind.domain.Address;
import com.bitmind.domain.Btc;
import com.bitmind.domain.Coin;
import com.bitmind.web.CoinReader;
import com.bitmind.web.btc.BtcReader;

@Service
public class BtcService extends AbstractCoinService implements CoinService {

	@Override
	CoinReader getReader() {
		if (coinReader == null) {
			coinReader = new BtcReader();
		}
		return coinReader;
	}

	@Override
	Coin getNewCoin() {
		return new Btc();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bitmind.manager.CoinService#getAddressBalance(java.lang.String)
	 */
	@Override
	public BigDecimal getAddressBalance(String addressString) {
		BigDecimal balance = null;

		Address address = getReader().readAddress(addressString);
		if (address != null) {
			balance = address.getBalance();
		}

		return balance;
	}
}
