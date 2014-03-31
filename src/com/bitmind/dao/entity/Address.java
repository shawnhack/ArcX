package com.bitmind.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.bitmind.domain.AssetType;
import com.bitmind.util.PriceConverter;

@Entity
public class Address extends AbstractEntity implements Comparable<Address> {

	private static final long serialVersionUID = 1L;

	private String address;
	private AssetType type;
	private Long balance = new Long(0);

	@Transient
	private String worth;

	public Address() {
	}

	public Address(String address) {
		super();
		this.address = address;
	}

	public Address(String address, AssetType type, long balance) {
		super();
		this.address = address;
		this.type = type;
		this.balance = balance;
	}

	@Override
	public int compareTo(Address a2) {
		return a2.getBalance().compareTo(this.getBalance());
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

	public String getAddress() {
		return address;
	}

	public Long getBalance() {
		return balance;
	}

	public String getBalanceView() {
		return PriceConverter.getDisplayBalance(balance, type.getSymbol());
	}

	public AssetType getType() {
		return type;
	}

	public String getWorth() {
		return worth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public void setType(AssetType type) {
		this.type = type;
	}

	public void setWorth(String worth) {
		this.worth = worth;
	}

}
