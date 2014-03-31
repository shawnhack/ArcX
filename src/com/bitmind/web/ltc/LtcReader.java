package com.bitmind.web.ltc;

import com.bitmind.domain.AssetType;
import com.bitmind.web.AbstractCoinReader;
import com.bitmind.web.BlockFetcher;
import com.bitmind.web.CoinReader;
import com.bitmind.web.PriceFetcher;

public class LtcReader extends AbstractCoinReader implements CoinReader {

	@Override
	protected BlockFetcher getBlockFetcher() {
		return new LtcBlockFetcher();
	}

	@Override
	protected PriceFetcher getPriceFetcher() {
		return new LtcPriceFetcher();
	}

	@Override
	protected AssetType getAssetType() {
		return AssetType.LTC;
	}

}
