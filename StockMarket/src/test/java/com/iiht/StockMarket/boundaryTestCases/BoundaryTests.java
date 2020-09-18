/*
 * Boundary Tests : To Test for validation of each field member of given model
 */
package com.iiht.StockMarket.boundaryTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.boundaryTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.utilTestClass.MasterData;

@RunWith(SpringRunner.class)
public class BoundaryTests implements Serializable
{
	private static final long serialVersionUID = -6544854658457780865L;

	private Validator validator;

    //----------------------------------------------------------------------------------------------
    @Before
    public void setUp() {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //=============================================================================================
	//			1. CompanyDetiails - Validating length of all properties
    //=============================================================================================
	@Test
	public void testCompanyCodeTitleLength() throws Exception 
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setCompanyCode((long)101);
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testStockExchangeLength() throws Exception 
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setStockExchange("SE");
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testCompanyNameLength() throws Exception 
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setCompanyName("TM");
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testCompanyCEOLength() throws Exception
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setCompanyCEO("abc");
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testTurnoverLength() throws Exception
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setTurnover(87543989766.2334567);
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}	
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testBoardOfDirectorsLength() throws Exception
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setBoardOfDirectors("AAA");
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		//Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validateProperty(companyDetails, "boardOfDirectors");
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testCompanyProfileLength() throws Exception
	{
		CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
		companyDetails.setCompanyProfile("Base location is in New York, USA");
		Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

    //=============================================================================================
	//			1.1 CompanyDetiails - Post Company Details Success or Failure
    //=============================================================================================
    @Test
    public void testPostCompanyDetailsSuccess() throws IOException 
    {
    	CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
        Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);	    
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostCompanyDetailsFailed() throws IOException {
    	CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
    	companyDetails.setCompanyCode(null);
        Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    //=============================================================================================
	//			2. StockPriceDetiails - Validating length of all properties
    //=============================================================================================
	@Test
	public void testCurrentStockPriceLength() throws Exception
	{
		StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
		stockPrice.setCurrentStockPrice(45.67);
        Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testStockPriceDateLength() throws Exception
	{
		StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
		stockPrice.setStockPriceDate(LocalDate.parse("2020-09-08"));
        Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testStockPriceTimeLength() throws Exception
	{
		StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
		stockPrice.setStockPriceTime(LocalTime.parse("09:30:00"));
        Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

    //=============================================================================================
	//			2.1 StockPriceDetiails - Post Stock Price Details Success or Failure
    //=============================================================================================
    @Test
    public void testPostStockPriceDetailsSuccess() throws IOException {
    	StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
        Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostStockPriceDetailsFailed() throws IOException {
    	StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
    	stockPrice.setId(null);
    	Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }
}