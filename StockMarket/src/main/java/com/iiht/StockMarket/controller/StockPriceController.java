package com.iiht.StockMarket.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.StockMarket.dto.InvalidStockExceptionResponse;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceIndexDTO;
import com.iiht.StockMarket.exception.InvalidStockException;
import com.iiht.StockMarket.exception.StockNotFoundException;
import com.iiht.StockMarket.services.StockMarketService;

@RestController
@RequestMapping (value = "/stock")
public class StockPriceController {

	@Autowired
	private StockMarketService stockMarketService;
	//-------------------------------------------------------------------------------------------------------------------------------
	// SERVICE OPERATIONS
	//-------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/stockPrice")																				// 1. WORKING
 	public String homePage(){ 		
		return "Welcome to StockMarket Application - Stock Price Details : About Stock Price details for a company.";
 	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@PostMapping(value="/addStock")																						// 2. WORKING
	public ResponseEntity<StockPriceDetailsDTO> addStockDetails(@Valid @RequestBody StockPriceDetailsDTO stockPriceDetailsDTO, BindingResult bindingResult) throws InvalidStockException {
		
		if(bindingResult.hasErrors())
			throw new InvalidStockException("Invalid Stock Details!!!");
		else
			return new ResponseEntity<StockPriceDetailsDTO>(stockMarketService.saveStockPriceDetails(stockPriceDetailsDTO), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/getAllStock", produces = "application/json")											// 3. WORKING
	public ResponseEntity<List<StockPriceDetailsDTO>> getAllStockDetails() {
		return new ResponseEntity<List<StockPriceDetailsDTO>>(stockMarketService.getAllStockDetails(), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/deleteStock/{companyCode}")															// 4. WORKING
	public ResponseEntity<List<StockPriceDetailsDTO>> deleteStockByCompanyCode(@PathVariable Long companyCode) {

		if(stockMarketService.deleteStock(companyCode) == null)
			throw new StockNotFoundException("Invalid Company Code!! Please enter valid companyCode...");
		else	
			return new ResponseEntity<List<StockPriceDetailsDTO>>(stockMarketService.deleteStock(companyCode), HttpStatus.OK);		
		
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getStockByCompanyCode/{companyCode}")															// 5. WORKING
	public ResponseEntity<List<StockPriceDetailsDTO>> getStockByCompanyCode(@PathVariable Long companyCode) {
		
		if(stockMarketService.getStockByCode(companyCode) == null)
			throw new StockNotFoundException("Invalid Company Code!! Please enter valid companyCode...");
		else	
			return new ResponseEntity<List<StockPriceDetailsDTO>>(stockMarketService.getStockByCode(companyCode), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getStockPriceIndex/{companyCode}/{startDate}/{endDate}")										// 6. WORKING
	public ResponseEntity<StockPriceIndexDTO> displayStockPriceIndex(@PathVariable Long companyCode, @PathVariable Date startDate, @PathVariable Date endDate) {
		
		if(stockMarketService.getStockPriceIndex(companyCode, startDate.toLocalDate(), endDate.toLocalDate()) == null)
			throw new StockNotFoundException("Invalid Company Code or Date!!! Please enter valid Details...");
		else	
			return new ResponseEntity<StockPriceIndexDTO>(stockMarketService.getStockPriceIndex(companyCode, startDate.toLocalDate(), endDate.toLocalDate()), HttpStatus.OK);
	}
	
	//================================================================================================
	//			UTITLITY EXCEPTION HANDLERS - 2
	//================================================================================================
	@ExceptionHandler(InvalidStockException.class)
	public ResponseEntity<InvalidStockExceptionResponse> companyHandler(InvalidStockException ex) {
		InvalidStockExceptionResponse resp = new InvalidStockExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<InvalidStockExceptionResponse> response =	new ResponseEntity<InvalidStockExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}
	//------------------------------------------------------------------------------------------------
	@ExceptionHandler(StockNotFoundException.class)
	public ResponseEntity<InvalidStockExceptionResponse> companyHandler(StockNotFoundException ex) {
		InvalidStockExceptionResponse resp = new InvalidStockExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		ResponseEntity<InvalidStockExceptionResponse> response = new ResponseEntity<InvalidStockExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}	
}