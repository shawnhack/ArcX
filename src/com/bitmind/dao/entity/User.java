package com.bitmind.dao.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User extends AbstractEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Parent parent;

	@Transient
	private final boolean accountNonExpired;

	@Transient
	private final boolean accountNonLocked;

	@Transient
	private List<GrantedAuthority> authorities;

	@Transient
	private final boolean credentialsNonExpired;

	@Basic
	private String email;

	@Transient
	private final boolean enabled;

	@Basic
	private String lowercaseUsername;

	@Basic
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Portfolio portfolio;

	@Basic
	private int role;

	@Basic
	private String username;

	public User() {
		super();
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
		this.portfolio = new Portfolio();
	}

	public User(String email, String username, String password) {
		this(email, username, password, null);
	}

	public User(String email, String username, String password,
			List<GrantedAuthority> authorities) {
		this();
		this.email = email;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getEmail() {
		return email;
	}

	public String getLowercaseUsername() {
		return lowercaseUsername;
	}

	public Parent getParent() {
		return parent;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public Portfolio getPortfolio() {
		if (portfolio == null) {
			portfolio = new Portfolio();
		}

		return portfolio;
	}

	public int getRole() {
		return role;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@PrePersist
	@PreUpdate
	public void prePersist() {
		if (username != null) {
			lowercaseUsername = username.toLowerCase();
		} else {
			username = null;
		}
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLowercaseUsername(String lowercaseUsername) {
		this.lowercaseUsername = lowercaseUsername;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
