package com.iiht.StockMarket.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.services.StockMarketService;

@RestController
public class StockPriceController {

	@Autowired
	private StockMarketService stockMarketService;
	//-------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/stockPrice")																				// 1. WORKING
 	public String homePage(){
 		return "Welcome to StockMarket Application - Stock Price Details : About Stock Price details for a company.";
 	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@PostMapping(value="/stock/newStock")																				// 2. WORKING
	public ResponseEntity<Boolean> addStockDetails(@RequestBody StockPriceDetailsDTO stockPriceDetailsDTO)
	{
		Boolean value = stockMarketService.saveStockPriceDetails(stockPriceDetailsDTO);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/stock/getAllStock", produces = "application/json")											// 3. WORKING
	public ResponseEntity<List<StockPriceDetailsDTO>> getAllStockDetails() {
		return new ResponseEntity<List<StockPriceDetailsDTO>>(stockMarketService.getAllStockDetails(), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/stock/deleteStock/{companyCode}")															// 4. WORKING
	public ResponseEntity<Boolean> deleteStockByCompanyCode(@PathVariable Long companyCode) {
		
		Boolean value = stockMarketService.deleteStock(companyCode);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/stock/getStockByCompanyCode/{companyCode}")													// 5. WORKING
	public ResponseEntity<List<StockPriceDetailsDTO>> getStockByCompanyCode(@PathVariable Long companyCode) {
		return new ResponseEntity<List<StockPriceDetailsDTO>>(stockMarketService.getStockByCode(companyCode), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/stock/getStockPriceIndex/{companyCode}/{startDate}/{endDate}")								// 6. WORKING
	public ResponseEntity<List<Object>> displayStockPriceIndex(@PathVariable Long companyCode, @PathVariable Date startDate, @PathVariable Date endDate) {
		return new ResponseEntity<List<Object>>(stockMarketService.getStockPriceIndex(companyCode, startDate, endDate), HttpStatus.OK);
	}	
}