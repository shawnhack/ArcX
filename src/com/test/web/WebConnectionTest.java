package com.test.web;

import java.io.IOException;

import org.junit.Test;

import com.bitmind.web.WebConnection;

public class WebConnectionTest {

	@Test
	public void testLoadPage() {
		// String url = "http://api.bitcoinaverage.com/ticker/USD";
		String url = "http://blockchain.info/address/18iPs6ymH81bTrTKatgDvXhznuTPjoop6U?format=json";
		String json = null;
		try {
			json = WebConnection.loadPage(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(json);
	}
}
