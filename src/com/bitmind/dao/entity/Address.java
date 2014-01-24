package com.bitmind.dao.entity;

import java.io.Serializable;

import com.bitmind.domain.AssetType;
import com.bitmind.util.PriceFormatter;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Address extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String address;
	private AssetType type;
	private long balance;
	private String value;

	public Address(String address, AssetType type, long balance) {
		super();
		this.address = address;
		this.type = type;
		this.balance = balance;
	}

	public Address(String address) {
		super();
		this.address = address;
	}

	public Address() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBalanceView() {
		return PriceFormatter.toBtc(balance);
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public AssetType getType() {
		return type;
	}

	public void setType(AssetType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
