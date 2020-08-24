package com.iiht.StockMarket.controller;

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

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.services.CompanyInfoService;

@RestController
public class CompanyInfoController {

	@Autowired
	private CompanyInfoService companyInfoService;

	//-------------------------------------------------------------------------------------------------------------------------------
	// SERVICE OPERATIONS
	//-------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/")																						// 1. WORKING
 	public String landingPage() {
 		return "Welcome to StockMarket Application - Dealing with Stock Market Business - Companies and Stock Price Index.";
 	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/company")																				// 2. WORKING
 	public String homePage(){
 		return "Welcome to StockMarket Application - Company Information : List companies is a Stock Exchange.";
 	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@PostMapping(value="/company/newCompany")																			// 3. WORKING
	public ResponseEntity<Boolean> addCompanyDetails(@RequestBody CompanyDetailsDTO companyDetailsDTO) {
		Boolean value = companyInfoService.saveCompanyDetails(companyDetailsDTO);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	//-------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/company/deleteCompany/{companyCode}")														// 4. WORKING
	public ResponseEntity<Boolean> deleteCompanyDetails(@PathVariable Long companyCode) {
		Boolean value = companyInfoService.deleteCompany(companyCode);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/company/getAllCompanies", produces = "application/json")										// 5. WORKING
	public ResponseEntity<List<CompanyDetailsDTO>> getAllCompanies() {
		return new ResponseEntity<List<CompanyDetailsDTO>>(companyInfoService.getAllCompanies(), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/company/getCompanyInfoByCode/{companyCode}")													// 6. WORKING
	public ResponseEntity<CompanyDetailsDTO> getCompanyByCode(@PathVariable Long companyCode) {
		return new ResponseEntity<CompanyDetailsDTO>(companyInfoService.getCompanyInfoByCode(companyCode), HttpStatus.OK);
	}	
}