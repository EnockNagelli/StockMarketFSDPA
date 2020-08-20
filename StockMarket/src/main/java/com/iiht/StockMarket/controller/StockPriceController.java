package com.iiht.StockMarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iiht.StockMarket.services.StockMarketService;

@RestController
public class StockPriceController {

	@Autowired
	private StockMarketService stockMarketService;
		
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/stockPrice")													// 1. WORKING
 	public String homePage(){
 		return "Welcome to StockMarket Application - Stock Price Details : About Stock Price details for a company.";
 	}
}
