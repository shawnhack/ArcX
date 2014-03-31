package com.test.web;

import org.junit.Test;

import com.bitmind.web.BlockAddress;
import com.bitmind.web.ltc.LtcBlockFetcher;
import com.test.AbstractTest;

public class LtcBlockFetcherTest extends AbstractTest {

	@Test
	public void testReadAddress() {
		LtcBlockFetcher fetcher = new LtcBlockFetcher();
		String addressString = "Lb7Ai9oKYjHDMcLMZNexU5WqfrhWVakco4";
		BlockAddress address = fetcher.readAddress(addressString);

		System.out.println(address.getBalance());
	}

}
