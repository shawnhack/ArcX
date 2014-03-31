package com.bitmind.web.btc;

import com.bitmind.domain.AssetType;
import com.bitmind.web.AbstractCoinReader;
import com.bitmind.web.BlockFetcher;
import com.bitmind.web.CoinReader;
import com.bitmind.web.PriceFetcher;

public class BtcReader extends AbstractCoinReader implements CoinReader {

	@Override
	protected BlockFetcher getBlockFetcher() {
		return new BlockchainFetcher();
	}

	@Override
	protected PriceFetcher getPriceFetcher() {
		return new BtcPriceFetcher();
	}

	@Override
	protected AssetType getAssetType() {
		return AssetType.BTC;
	}

}
