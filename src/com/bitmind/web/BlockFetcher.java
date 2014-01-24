package com.bitmind.web;

import com.bitmind.web.btc.blockchain.BlockchainAddress;

public interface BlockFetcher {

	public BlockchainAddress readAddress(String addressString);

}
