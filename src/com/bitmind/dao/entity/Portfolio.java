package com.bitmind.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.bitmind.domain.AssetType;
import com.bitmind.util.PriceConverter;

@Entity
public class Portfolio extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Wallet> wallets;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	public Portfolio() {
		super();
	}

	public Wallet getWallet(AssetType type) {

		for (Wallet wallet : wallets) {
			AssetType atype = wallet.getType();

			if (atype == type) {
				return wallet;
			}
		}

		Wallet wallet = new Wallet();
		wallet.setType(type);
		wallets.add(wallet);

		return wallet;
	}

	public List<Wallet> getWallets() {
		if (wallets == null) {
			wallets = new ArrayList<Wallet>();
		}
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}

	public String getTotalWorth() {
		Money total = Money.zero(CurrencyUnit.USD);

		for (Wallet wallet : wallets) {
			total = total.plus(wallet.getTotalWorthValue());
		}
		return PriceConverter.getDisplayPrice(total);
	}

}
