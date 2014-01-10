package com.bitmind.web;

import com.bitmind.web.btc.blockchain.BlockchainAddress;

public interface BlockReader {

	public BlockchainAddress readAddress(String addressString);

}
