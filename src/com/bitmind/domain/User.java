package com.bitmind.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String userName;
	private Map<AssetType, Wallet> wallets = new HashMap<AssetType, Wallet>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<AssetType, Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(Map<AssetType, Wallet> wallets) {
		this.wallets = wallets;
	}

	public void createWallet(AssetType type) {
		wallets.put(type, new Wallet());
	}

	public Wallet getWallet(AssetType type) {
		return wallets.get(type);
	}

}
