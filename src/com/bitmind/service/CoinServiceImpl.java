package com.bitmind.service;

import org.springframework.stereotype.Service;

import com.bitmind.domain.AssetType;
import com.bitmind.web.CoinReader;
import com.bitmind.web.btc.BtcReader;
import com.bitmind.web.ltc.LtcReader;

@Service
public class CoinServiceImpl extends AbstractCoinService implements CoinService {

	@Override
	public void buildReader(AssetType type) {

		CoinReader reader = null;

		switch (type) {
		case BTC:
			reader = new BtcReader();
			break;
		case LTC:
			reader = new LtcReader();
			break;
		default:
			reader = new BtcReader();
			break;
		}

		this.activateReader(type, reader);
	}

}
