package com.bitmind.dao.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.joda.money.Money;

import com.bitmind.domain.AssetType;
import com.bitmind.domain.WalletCalculator;

@Entity
public class Wallet extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<Address>();

	@Transient
	private final WalletCalculator calculator = new WalletCalculator();

	@Transient
	private Money lastPrice;

	private AssetType type;

	public boolean addAddress(Address address) {
		boolean success = addresses.add(address);
		return success;
	}

	public void calculatePrices() {
		calculator.calculateWorth(addresses, lastPrice, type);
	}

	public void calculatePrices(Money lastPrice) {
		this.lastPrice = lastPrice;
		calculatePrices();
	}

	public boolean deleteAddresses(List<String> addressList) {
		List<Address> removing = new ArrayList<Address>();
		for (String address : addressList) {
			removing.add(new Address(address));
		}
		return addresses.removeAll(removing);
	}

	public List<Address> getAddresses() {
		Collections.sort(addresses);
		return addresses;
	}

	public Money getLastPrice() {
		return lastPrice;
	}

	public String getTotalBalance() {
		return calculator.getTotalBalance();
	}

	public String getTotalWorth() {
		return calculator.getTotalWorth();
	}

	public Money getTotalWorthValue() {
		return calculator.getTotalWorthValue();
	}

	public AssetType getType() {
		return type;
	}

	public boolean hasAddress(String address) {
		return addresses.contains(new Address(address));
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void setLastPrice(Money lastPrice) {
		this.lastPrice = lastPrice;
	}

	public void setType(AssetType type) {
		this.type = type;
	}

}
