package com.bitmind.service;

import java.util.HashMap;
import java.util.Map;

import org.joda.money.Money;
import org.springframework.beans.factory.BeanInitializationException;

import com.bitmind.dao.entity.Address;
import com.bitmind.domain.AssetType;
import com.bitmind.web.CoinReader;

public abstract class AbstractCoinService implements CoinService {

	protected Map<AssetType, CoinReader> readers = new HashMap<AssetType, CoinReader>();
	private CoinReader activeReader;

	@Override
	public abstract void buildReader(AssetType type);

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
	public Money getLastPrice() {
		return getActiveReader().getLastPrice();
	}

	/**
	 * @return
	 */
	protected CoinReader getActiveReader() {
		if (activeReader == null) {
			throw new BeanInitializationException("Reader not initialized!");
		}
		return activeReader;
	}

	/**
	 * @param reader
	 */
	protected void setActiveReader(CoinReader reader) {
		activeReader = reader;
	}

	/**
	 * @param addressString
	 * @return
	 */
	private Address returnAddress(String addressString) {
		if (addressString == null || addressString.isEmpty()) {
			throw new ServiceException("Not a valid address");
		}
		Address address = getActiveReader().readAddress(addressString);
		return address;
	}

	/**
	 * @param type
	 * @param reader
	 */
	protected void activateReader(AssetType type, CoinReader reader) {
		CoinReader foundReader = readers.get(type);

		if (foundReader == null) {
			readers.put(type, reader);
			activeReader = reader;
		} else {
			activeReader = foundReader;
		}
	}

}
