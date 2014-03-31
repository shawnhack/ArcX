package com.bitmind.web.btc;

import java.io.IOException;

import com.bitmind.web.BlockAddress;
import com.bitmind.web.BlockFetcher;
import com.bitmind.web.WebConnection;
import com.google.gson.Gson;

public class BlockchainFetcher implements BlockFetcher {

	private final Gson gson = new Gson();
	private final static String DOMAIN = "http://blockchain.info/";
	private final static String ADDRESS = "address/";
	private final static String FORMAT_JSON = "?format=json";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bitmind.web.blockchain.Reader#readAddress(java.lang.String)
	 */
	@Override
	public BlockAddress readAddress(String addressString) {
		BlockAddress address = null;

		try {
			String urlString = DOMAIN + ADDRESS + addressString + FORMAT_JSON;
			String json = new WebConnection().loadPage(urlString);
			address = gson.fromJson(json, BlockchainAddress.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return address;
	}
}
