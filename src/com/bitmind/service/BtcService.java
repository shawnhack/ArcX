package com.bitmind.service;

import org.springframework.stereotype.Service;

import com.bitmind.dao.entity.Address;
import com.bitmind.domain.Btc;
import com.bitmind.domain.Coin;
import com.bitmind.web.CoinReader;
import com.bitmind.web.btc.BtcReader;

@Service
public class BtcService extends AbstractCoinService implements CoinService {

	@Override
	public Address getAddress(String addressString) {

		Address address = returnAddress(addressString);
		return address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bitmind.manager.CoinService#getAddressBalance(java.lang.String)
	 */
	@Override
	public Long getAddressBalance(String addressString) {

		Long balance = null;
		Address address = returnAddress(addressString);

		if (address != null) {
			balance = address.getBalance();
		}

		return balance;
	}

	@Override
	Coin getNewCoin() {
		return new Btc();
	}

	@Override
	CoinReader getReader() {
		if (coinReader == null) {
			coinReader = new BtcReader();
		}
		return coinReader;
	}

	/**
	 * @param addressString
	 * @return
	 */
	private Address returnAddress(String addressString) {
		if (addressString == null || addressString.isEmpty()) {
			throw new ServiceException("Not a valid address");
		}

		Address address = getReader().readAddress(addressString);
		return address;
	}
}
