package com.bitmind.web.btc;

import java.math.RoundingMode;

import org.joda.money.Money;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

import com.bitmind.dao.entity.Address;
import com.bitmind.domain.AssetType;
import com.bitmind.util.PriceConverter;
import com.bitmind.web.BlockFetcher;
import com.bitmind.web.CoinReader;
import com.bitmind.web.PriceFetcher;
import com.bitmind.web.btc.blockchain.BlockchainAddress;
import com.bitmind.web.btc.blockchain.BlockchainFetcher;
import com.bitmind.web.btc.btcaverage.AveragePriceFetcher;

public class BtcReader implements CoinReader {

	private final BlockFetcher blockReader = new BlockchainFetcher();
	private final PriceFetcher priceReader = new AveragePriceFetcher();

	@Override
	public Address readAddress(String addressString) {

		BlockchainAddress blockChainAddress = blockReader
				.readAddress(addressString);

		Address address = null;

		if (blockChainAddress != null) {
			address = new Address();
			address.setAddress(blockChainAddress.getAddress());
			long balance = blockChainAddress.getBalance();
			address.setBalance(balance);
			address.setType(AssetType.BTC);

			Money price = priceReader.getLastPrice();

			price = price.multipliedBy(PriceConverter.satoshiToBtc(balance),
					RoundingMode.DOWN);

			MoneyFormatter formatter = new MoneyFormatterBuilder()
					.appendCurrencySymbolLocalized().appendAmount()
					.toFormatter();
			String displayPrice = formatter.print(price);

			address.setValue(displayPrice);
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
