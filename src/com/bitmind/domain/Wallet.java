package com.bitmind.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bitmind.dao.entity.Address;

public class Wallet implements Serializable {

	private static final long serialVersionUID = -1345549436226308766L;

	private List<Address> addresses = new ArrayList<Address>();

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		addresses.add(address);
	}
}
