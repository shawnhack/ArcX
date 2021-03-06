package com.bitmind.form;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class AddressForm {

	@NotEmpty
	private String address;

	@NotEmpty
	private String type;

	private List<String> addresses;

	public String getAddress() {

		if (address != null) {
			address = address.trim();
		}

		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
