package com.bitmind.security;

import org.springframework.security.core.GrantedAuthority;

public enum AppRole implements GrantedAuthority {
	ROLE_USER(0), ROLE_ADMIN(1);

	private int bit;

	AppRole(int bit) {
		this.bit = bit;
	}

	@Override
	public String getAuthority() {
		return toString();
	}

	public int getBit() {
		return bit;
	}

}
