package com.iiht.StockMarket.functionalTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.businessTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.StockMarket.controller.CompanyInfoController;
import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.services.CompanyInfoService;
import com.iiht.StockMarket.utilTestClass.JSONUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = CompanyInfoController.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TestCompanyInfoController 
{	
	@Mock
	private CompanyInfoService companyInfoService;

	@InjectMocks
	private CompanyInfoController companyInfoController;

	
	// -------------------------------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform setup for Mockito initiations
	 */
	@Before public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	} 
	//-----------------------------------------------------------------------------
	/*
	 * Description : This test is to perform add new company date
	 */
	@Test 
	public void testAddCompanyInfo() throws Exception { 		
        CompanyDetailsDTO addCompany = JSONUtils.createCompanyDetailaDTO((long)5001, "BSE", "Cognizant", "Praveen Kumar", 65432.76, "AAA, BBB, CCC", "Base Location is in Chennai, India");
        ResponseEntity<Boolean> responseEntity = companyInfoController.addCompanyDetails(addCompany);
        yakshaAssert(currentTest(), (responseEntity.getStatusCodeValue() > 0 ? true : false), businessTestFile);
	}
	//-----------------------------------------------------------------------------
	/*
	 * Description : This test is to perform view all the posts from database
	 */
	@Test 
	public void testViewAllCompanies() throws Exception { 
        // Given input
        CompanyDetailsDTO company1 = JSONUtils.createCompanyDetailaDTO((long)5001, "BSE", "CTS", "Praveen Kumar", 565432.76, "AAA, BBB, CCC", "Base Location is in Chennai, India");
        CompanyDetailsDTO company2 = JSONUtils.createCompanyDetailaDTO((long)5002, "NSE", "IBM", "Navin Gupta", 765332.27, "AAA, BBB, CCC", "Base Location is in Mumbai, India");
        CompanyDetailsDTO company3 = JSONUtils.createCompanyDetailaDTO((long)5003, "HSE", "CSC", "Varun Gupta", 865232.47, "AAA, BBB, CCC", "Base Location is in New Delhi, India");
        List<CompanyDetailsDTO> list = new ArrayList<CompanyDetailsDTO>();
        list.add(company1);
        list.add(company2);
        list.add(company3);
        // when
        when(companyInfoService.getAllCompanies()).thenReturn(list);
        
        List<CompanyDetailsDTO> fromController = companyInfoService.getAllCompanies();
        // then
        assertThat(fromController.size()).isEqualTo(3);
        
        assertThat(fromController.get(0).getCompanyCode()).isEqualTo(company1.getCompanyCode());
        assertThat(fromController.get(0).getStockExchange()).isEqualTo(company1.getStockExchange());
        assertThat(fromController.get(0).getCompanyProfile()).isEqualTo(company1.getCompanyProfile());

        assertThat(fromController.get(0).getCompanyCode()).isEqualTo(company1.getCompanyCode());
        assertThat(fromController.get(1).getStockExchange()).isEqualTo(company2.getStockExchange());
        assertThat(fromController.get(2).getCompanyProfile()).isEqualTo(company3.getCompanyProfile());
        
        yakshaAssert(currentTest(), (fromController.size() == 3 ? true : false), businessTestFile);
	}
	  
	//-----------------------------------------------------------------------------
	/*
	 * Description : This test is to perform check the null operation against view all posts operation
	 */
	@Test 
	public void testViewAllCompaniesCase() throws Exception {
 		List<CompanyDetailsDTO> list = new ArrayList<CompanyDetailsDTO>();
		when(companyInfoService.getAllCompanies()).thenReturn(list);
        // when
        List<CompanyDetailsDTO> fromController = companyInfoService.getAllCompanies();
        // then
        assertThat(fromController.size()).isEqualTo(0);
		yakshaAssert(currentTest(), (fromController.size() == 0 ? true : false) ? true : false, businessTestFile); 
	}
}