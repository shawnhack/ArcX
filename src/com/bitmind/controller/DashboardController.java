package com.bitmind.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitmind.dao.entity.Portfolio;
import com.bitmind.dao.entity.User;
import com.bitmind.dao.entity.Wallet;
import com.bitmind.domain.AssetType;
import com.bitmind.form.CoinForm;
import com.bitmind.service.CoinService;
import com.bitmind.service.ServiceException;
import com.google.common.collect.Lists;

@Controller
public class DashboardController extends AbstractController {

	@Autowired
	private CoinService coinService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String viewDashboard(Model model) {

		User user = getUser();
		Portfolio portfolio = user.getPortfolio();
		List<AssetType> existingTypes = new ArrayList<AssetType>();

		// For each wallet calculate prices
		for (Wallet wallet : portfolio.getWallets()) {
			AssetType type = wallet.getType();
			existingTypes.add(type);

			coinService.buildReader(type);
			wallet.calculatePrices(coinService.getLastPrice());
		}

		List<AssetType> newTypes = Lists.newArrayList(AssetType.values());
		newTypes.removeAll(existingTypes);

		model.addAttribute("portfolio", portfolio);
		model.addAttribute("types", newTypes);

		return "dashboard";
	}

	@RequestMapping(value = "/addCoin", method = RequestMethod.POST)
	public String addCoin(@ModelAttribute("address") CoinForm form, Model model) {

		User user = getUser();
		Portfolio portfolio = user.getPortfolio();
		Wallet wallet = portfolio.getWallet(AssetType.valueOf(form.getCoin()));

		model.addAttribute("wallet", wallet);
		model.addAttribute("type", form.getCoin());

		return "addressList";
	}

	@ExceptionHandler(ServiceException.class)
	public ModelAndView handleServiceException(ServiceException e) {
		ModelAndView model = new ModelAndView("dashboard");
		addError(model, e.getServiceMessage());
		User user = getUser();
		Portfolio portfolio = user.getPortfolio();
		model.addObject("portfolio", portfolio);
		return model;
	}
}
