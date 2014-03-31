package com.bitmind.web.ltc;

import java.io.IOException;
import java.math.BigDecimal;

import com.bitmind.util.PriceConverter;
import com.bitmind.web.BlockAddress;
import com.bitmind.web.BlockFetcher;
import com.bitmind.web.WebConnection;

public class LtcBlockFetcher implements BlockFetcher {

	// private final Gson gson = new Gson();
	private final static String DOMAIN = "http://explorer.litecoin.net/chain/Litecoin/q/";
	private final static String ADDRESS = "getreceivedbyaddress/";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bitmind.web.blockchain.Reader#readAddress(java.lang.String)
	 */
	@Override
	public BlockAddress readAddress(String addressString) {
		LtcAddress address = new LtcAddress();

		try {
			String urlString = DOMAIN + ADDRESS + addressString;
			String json = new WebConnection().loadPage(urlString);
			long balance = PriceConverter.btcToSatoshi(new BigDecimal(json));

			address.setBalance(balance);
			address.setAddress(addressString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return address;
	}
}
