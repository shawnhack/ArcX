package com.bitmind.web.btc.blockchain;

import java.io.IOException;

import com.bitmind.web.BlockReader;
import com.bitmind.web.WebConnection;
import com.google.gson.Gson;

public class BlockchainReader implements BlockReader {

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
	public BlockchainAddress readAddress(String addressString) {
		BlockchainAddress address = null;

		try {
			String urlString = DOMAIN + ADDRESS + addressString + FORMAT_JSON;
			String json = WebConnection.loadPage(urlString);
			address = gson.fromJson(json, BlockchainAddress.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return address;
	}
}
