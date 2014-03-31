package com.test.web;

import java.io.IOException;

import org.junit.Test;

import com.bitmind.web.HttpClientConnection;
import com.test.AbstractTest;

public class HttpClientConnectionTest extends AbstractTest {

	@Test
	public void testLoadPage() {
		// String url = "http://api.bitcoinaverage.com/ticker/USD";

		String url = "http://blockchain.info/address/18iPs6ymH81bTrTKatgDvXhznuTPjoop6U?format=json";
		String json = null;
		try {
			json = new HttpClientConnection().loadPage(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(json);
	}
}
