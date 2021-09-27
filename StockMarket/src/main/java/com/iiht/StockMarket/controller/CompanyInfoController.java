package com.iiht.StockMarket.controller;

import java.util.List;

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

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.dto.InvalidCompanyExceptionResponse;
import com.iiht.StockMarket.exception.CompanyNotFoundException;
import com.iiht.StockMarket.exception.InvalidCompanyException;
import com.iiht.StockMarket.services.CompanyInfoService;

@RestController
@RequestMapping (value = "/company")
public class CompanyInfoController {

	@Autowired
	private CompanyInfoService companyInfoService;

	//-------------------------------------------------------------------------------------------------------------------------------
	// SERVICE OPERATIONS
	//-------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/home")																					// 1. WORKING
 	public String landingPage() {
 		return "Welcome to StockMarket Application - Dealing with Stock Market Business - Companies and Stock Price Index.";
 	}
	//-------------------------------------------------------------------------------------------------------------------------------
	// Bug creation 2:	@Valid removed as parameter of addCompanyDetails method
	// Bug creation 3:	"InvalidCompanyException" exception is removed from addCompanyDetails method declaration
	@PostMapping(value="/addCompany")																					// 3. WORKING
	public ResponseEntity<CompanyDetailsDTO> addCompanyDetails(@RequestBody CompanyDetailsDTO companyDetailsDTO, BindingResult bindingResult) {

		if(bindingResult.hasErrors())
			throw new InvalidCompanyException("Invalid Company Details!!!");
		else
			return new ResponseEntity<CompanyDetailsDTO>(companyInfoService.saveCompanyDetails(companyDetailsDTO), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/deleteCompany/{companyCode}")																// 4. WORKING
	public ResponseEntity<CompanyDetailsDTO> deleteCompanyDetails(@PathVariable("companyCode") Long companyCode) {
	
		if(companyInfoService.deleteCompany(companyCode) == null)
			throw new CompanyNotFoundException("Invalid Company Code!! Please enter valid companyCode...");
		else	
			return new ResponseEntity<CompanyDetailsDTO>(companyInfoService.deleteCompany(companyCode), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	// Bug creation 4:	CompanyCode Data type "Long" changed to primitive type "long"
	@GetMapping(value = "/getCompanyInfoById/{companyCode}")															// 5. WORKING
	public ResponseEntity<CompanyDetailsDTO> getCompanyDetailsById(@PathVariable("companyCode") long companyCode) {
		
		if(companyInfoService.getCompanyInfoById(companyCode) == null)
			throw new CompanyNotFoundException("Invalid Company Code!! Please enter valid companyCode...");
		else	
			return new ResponseEntity<CompanyDetailsDTO>(companyInfoService.getCompanyInfoById(companyCode), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getAllCompanies", produces = "application/json")												// 6. WORKING
	public ResponseEntity<List<CompanyDetailsDTO>> getAllCompanies() {		
		return new ResponseEntity<List<CompanyDetailsDTO>>(companyInfoService.getAllCompanies(), HttpStatus.OK);
	}
	
	//================================================================================================
	//			UTITLITY EXCEPTION HANDLERS - 2
	//================================================================================================
	@ExceptionHandler(InvalidCompanyException.class)
	public ResponseEntity<InvalidCompanyExceptionResponse> companyHandler(InvalidCompanyException ex) {
		InvalidCompanyExceptionResponse resp = new InvalidCompanyExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<InvalidCompanyExceptionResponse> response =	new ResponseEntity<InvalidCompanyExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}
	//------------------------------------------------------------------------------------------------
	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<InvalidCompanyExceptionResponse> companyHandler(CompanyNotFoundException ex){
		InvalidCompanyExceptionResponse resp = new InvalidCompanyExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		ResponseEntity<InvalidCompanyExceptionResponse> response = new ResponseEntity<InvalidCompanyExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}	
}



















/*
The @Controller is a common annotation that is used to mark a class as Spring MVC Controller while 
@RestController is a special controller used in RESTFul web services and the equivalent of @Controller + @ResponseBody.

The @Controller annotation indicates that the class is a "Controller" like a web controller while @RestController annotation 
indicates that the class is a controller where @RequestMapping methods assume @ResponseBody semantics 
by default i.e. servicing REST API.

@Target(value=TYPE)
@Retention(value=RUNTIME)
@Documented
@Controller
@ResponseBody
public @interface RestController

@Target(value=TYPE)
@Retention(value=RUNTIME)
@Documented
@Component
public @interface Controller 
 
@Valid, it automatically bootstraps the default JSR 380 implementation — Hibernate Validator — and validates the argument. 
When the target argument fails to pass the validation, Spring Boot throws a MethodArgumentNotValidException exception.

@RequestBody annotation maps the HttpRequest body to a transfer or domain object, enabling automatic deserialization of the 
inbound HttpRequest body onto a Java object.

@ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and 
passed back into the HttpResponse object.

@PathVariable annotation can be used to handle template variables in the request URI mapping

Errors, BindingResult For access to errors from validation and data binding for a command object (that is, a @ModelAttribute argument) 
or errors from the validation of a @RequestBody or @RequestPart arguments. You must declare an Errors, or BindingResult argument 
immediately after the validated method argument.

//-------------------------------------------------------------------------------------------------------------------------------
@RequestMapping (value = "/company")																				// 2. WORKING
	public String homePage(){
		return "Welcome to StockMarket Application - Company Information : List companies is a Stock Exchange.";
	}
	
*/
