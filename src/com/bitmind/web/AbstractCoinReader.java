package com.bitmind.web;

import org.joda.money.Money;

import com.bitmind.dao.entity.Address;
import com.bitmind.domain.AssetType;

public abstract class AbstractCoinReader {

	protected abstract BlockFetcher getBlockFetcher();

	protected abstract PriceFetcher getPriceFetcher();

	protected abstract AssetType getAssetType();

	public Address readAddress(String addressString) {

		BlockAddress blockChainAddress = getBlockFetcher().readAddress(
				addressString);

		Address address = null;

		if (blockChainAddress != null) {
			address = new Address();
			address.setAddress(blockChainAddress.getAddress());
			long balance = blockChainAddress.getBalance();
			address.setBalance(balance);
			address.setType(getAssetType());
		}

		return address;
	}

	public Money getLastPrice() {
		return getPriceFetcher().getLastPrice();
	}

}
