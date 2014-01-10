package com.bitmind.view.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitmind.domain.Address;
import com.bitmind.domain.AssetType;
import com.bitmind.domain.User;
import com.bitmind.domain.Wallet;
import com.bitmind.service.CoinService;
import com.bitmind.view.form.SignupForm;

@Controller
public class HomeController {

	@Autowired
	private User user;

	@Autowired
	private CoinService btcManager;

	@RequestMapping(value = "/logon", method = RequestMethod.GET)
	public String logon(Model model) {

		user.setUserName("Arrow");
		user.createWallet(AssetType.BTC);
		model.addAttribute("user", user);

		return "home";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {

		System.out.println("Sign Up");

		return "signup";
	}

	@RequestMapping(value = "/signupSubmit", method = RequestMethod.POST)
	public String signupSubmit(@ModelAttribute("address") SignupForm form) {

		System.out.println(form.getEmail());

		return "signup";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		return "home";
	}

	@RequestMapping(value = "/viewAddresses", method = RequestMethod.GET)
	public String viewAddresses(Model model) {

		Wallet wallet = user.getWallet(AssetType.BTC);
		model.addAttribute("addressList", wallet.getAddresses());

		return "addressList";
	}

	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public String addAddress(@ModelAttribute("address") Address address,
			Model model) {

		BigDecimal balance = btcManager.getAddressBalance(address.getAddress());
		address.setBalance(balance);

		Wallet wallet = user.getWallet(AssetType.BTC);
		wallet.addAddress(address);
		model.addAttribute("addressList", wallet.getAddresses());

		return "addressList";
	}
}
