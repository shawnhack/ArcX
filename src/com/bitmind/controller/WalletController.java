package com.bitmind.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitmind.dao.entity.Address;
import com.bitmind.dao.entity.Portfolio;
import com.bitmind.dao.entity.User;
import com.bitmind.dao.entity.Wallet;
import com.bitmind.domain.AssetType;
import com.bitmind.form.AddressForm;
import com.bitmind.service.CoinService;
import com.bitmind.service.ServiceException;

@Controller
public class WalletController extends AbstractController {

	private static Logger log = LoggerFactory.getLogger(WalletController.class);

	@Autowired
	private CoinService coinService;

	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public String addAddress(@ModelAttribute("address") AddressForm form,
			Model model, HttpServletRequest request) {

		AssetType type = AssetType.valueOf(form.getType());
		coinService.buildReader(type);

		User user = getUser();
		Portfolio portfolio = user.getPortfolio();
		Wallet wallet = portfolio.getWallet(type);
		String addressString = form.getAddress();

		boolean hasAddress = wallet.hasAddress(addressString);
		if (hasAddress) {
			throw new ServiceException("Duplicate address");
		}

		Address address = coinService.getAddress(addressString);
		if (address == null) {
			throw new ServiceException("Invalid address");
		}

		wallet.addAddress(address);
		setLastPrice(wallet, false);

		updateUser(user, request);

		model.addAttribute("wallet", wallet);

		return "addressList";
	}

	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	public String deleteAddress(@ModelAttribute("address") AddressForm form,
			Model model, HttpServletRequest request) {

		User user = getUser();
		Portfolio portfolio = user.getPortfolio();
		Wallet wallet = portfolio.getWallet(AssetType.valueOf(form.getType()));
		List<String> addresses = form.getAddresses();

		if (addresses == null || addresses.isEmpty()) {
			throw new ServiceException("Nothing selected");
		}

		wallet.deleteAddresses(addresses);
		wallet.calculatePrices();
		updateUser(user, request);

		model.addAttribute("wallet", wallet);
		addKeyMessage(model, "message.address.deleted");

		return "addressList";
	}

	@ExceptionHandler(ServiceException.class)
	public ModelAndView handleServiceException(ServiceException e) {
		ModelAndView model = new ModelAndView("addressList");
		addError(model, e.getServiceMessage());
		User user = getUser();
		Portfolio portfolio = user.getPortfolio();
		Wallet wallet = portfolio.getWallet(AssetType.BTC);
		model.addObject("wallet", wallet);
		return model;
	}

	@RequestMapping(value = "/viewAddresses/{typePath}", method = RequestMethod.GET)
	public String viewAddresses(Model model, @PathVariable String typePath) {

		AssetType type = AssetType.valueOf(typePath);
		coinService.buildReader(type);

		User user = getUser();
		Portfolio portfolio = user.getPortfolio();
		Wallet wallet = portfolio.getWallet(type);

		setLastPrice(wallet, true);

		model.addAttribute("type", typePath);
		model.addAttribute("wallet", wallet);

		return "addressList";
	}

	private void setLastPrice(Wallet wallet, boolean updatePrice) {
		if (updatePrice) {
			Money lastPrice = coinService.getLastPrice();
			wallet.calculatePrices(lastPrice);
		} else {
			wallet.calculatePrices();
		}
	}
}
