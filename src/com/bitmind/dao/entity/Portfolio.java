package com.bitmind.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bitmind.domain.AssetType;
import com.googlecode.objectify.annotation.Embed;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Portfolio extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Embed
	private Set<Address> addresses = new HashSet<Address>();

	public boolean addAddress(String addressString, AssetType type, long balance) {
		Address address = new Address(addressString, type, balance);
		return addAddress(address);
	}

	public boolean addAddress(Address address) {
		return addresses.add(address);
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public boolean deleteAddresses(List<String> addressList) {
		List<Address> removing = new ArrayList<Address>();

		for (String address : addressList) {
			removing.add(new Address(address));
		}
		return addresses.removeAll(removing);
	}

	public boolean hasAddress(String address) {
		return addresses.contains(new Address(address));
	}

}
