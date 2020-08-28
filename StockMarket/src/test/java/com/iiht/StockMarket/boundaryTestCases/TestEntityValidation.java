package com.iiht.StockMarket.boundaryTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.boundaryTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import java.io.IOException;
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
public class TestEntityValidation
{
    private Validator validator;
	
    //----------------------------------------------------------------------------------------------
    @Before
    public void setUp() {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostCompanyDetailsSuccess() throws IOException {
    	CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
        Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);	    
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostCompanyDetailsFailed() throws IOException {
    	CompanyDetailsDTO companyDetails = MasterData.getCompanyDetailsDTO();
    	companyDetails.setCompanyCode(null);
        Set<ConstraintViolation<CompanyDetailsDTO>> violations = validator.validate(companyDetails);
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostStockPriceDetailsSuccess() throws IOException {
    	StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
        Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostStockPriceDetailsFailed() throws IOException {
    	StockPriceDetailsDTO stockPrice = MasterData.getStockPriceDetailsDTO();
    	stockPrice.setId(null);
    	Set<ConstraintViolation<StockPriceDetailsDTO>> violations = validator.validate(stockPrice);
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
    }
}