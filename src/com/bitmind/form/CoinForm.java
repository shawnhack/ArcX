package com.bitmind.form;

import org.hibernate.validator.constraints.NotEmpty;

public class CoinForm {

	@NotEmpty
	private String coin;

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}

}
