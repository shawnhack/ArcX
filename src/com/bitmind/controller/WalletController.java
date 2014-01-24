package com.bitmind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitmind.dao.entity.Address;
import com.bitmind.dao.entity.Portfolio;
import com.bitmind.dao.entity.User;
import com.bitmind.form.AddressForm;
import com.bitmind.service.CoinService;
import com.bitmind.service.ServiceException;
import com.bitmind.service.UserService;

@Controller
public class WalletController extends AbstractController {

	@Autowired
	private CoinService btcService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/viewAddresses", method = RequestMethod.GET)
	public String viewAddresses(Model model) {

		User user = getUser();
		Portfolio portfolio = user.getPortfolio();

		model.addAttribute("addressList", portfolio.getAddresses());

		return "addressList";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String viewDashboard(Model model) {

		User user = getUser();
		Portfolio portfolio = user.getPortfolio();

		model.addAttribute("addressList", portfolio.getAddresses());

		return "dashboard";
	}

	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public String addAddress(@ModelAttribute("address") AddressForm form,
			Model model) {

		User user = getUser();
		Portfolio portfolio = user.getPortfolio();
		String addressString = form.getAddress();

		boolean hasAddress = portfolio.hasAddress(addressString);
		if (hasAddress) {
			throw new ServiceException("Duplicate address");
		}

		Address address = btcService.getAddress(addressString);
		if (address == null) {
			throw new ServiceException("Invalid address");
		}

		portfolio.addAddress(address);

		userService.updateUser(user);

		model.addAttribute("addressList", portfolio.getAddresses());

		return "addressList";
	}

	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	public String deleteAddress(@ModelAttribute("address") AddressForm form,
			Model model) {

		User user = getUser();
		Portfolio portfolio = user.getPortfolio();
		List<String> addresses = form.getAddresses();

		if (addresses == null || addresses.isEmpty()) {
			throw new ServiceException("Nothing selected");
		}

		portfolio.deleteAddresses(addresses);
		userService.updateUser(user);

		model.addAttribute("addressList", portfolio.getAddresses());
		addKeyMessage(model, "message.address.deleted");

		return "addressList";
	}

	@ExceptionHandler(ServiceException.class)
	public ModelAndView handleServiceException(ServiceException e) {
		ModelAndView model = new ModelAndView("addressList");
		addError(model, e.getServiceMessage());
		User user = getUser();
		Portfolio portfolio = user.getPortfolio();
		model.addObject("addressList", portfolio.getAddresses());
		return model;
	}
}
