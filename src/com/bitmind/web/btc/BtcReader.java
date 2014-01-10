package com.bitmind.web.btc;

import org.joda.money.Money;

import com.bitmind.domain.Address;
import com.bitmind.util.PriceConverter;
import com.bitmind.web.BlockReader;
import com.bitmind.web.CoinReader;
import com.bitmind.web.PriceReader;
import com.bitmind.web.btc.blockchain.BlockchainAddress;
import com.bitmind.web.btc.blockchain.BlockchainReader;
import com.bitmind.web.btc.btcaverage.AveragePriceReader;

public class BtcReader implements CoinReader {

	private final BlockReader blockReader = new BlockchainReader();
	private final PriceReader priceReader = new AveragePriceReader();

	@Override
	public Address readAddress(String addressString) {

		BlockchainAddress blockChainAddress = blockReader
				.readAddress(addressString);
		Address address = null;

		if (blockChainAddress != null) {
			address = new Address();
			address.setAddress(blockChainAddress.getAddress());
			address.setBalance(PriceConverter.satoshiToBtc(blockChainAddress
					.getBalance()));
		}

		return address;
	}

	@Override
	public Money getLastPrice() {
		return priceReader.getLastPrice();
	}

	@Override
	public long getMintedCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
