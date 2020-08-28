package com.iiht.StockMarket.serviceTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.businessTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.services.CompanyInfoService;
import com.iiht.StockMarket.services.CompanyInfoServiceImpl;
import com.iiht.StockMarket.utilTestClass.MasterData;

public class TestCompanyInfoService
{
	@Mock
	private CompanyDetailsDTO companyDetailsDTO;
	
	@Mock
	private CompanyInfoService companyInfoService;

	@Mock
	private CompanyInfoServiceImpl companyInfoServiceImpl;
	// -------------------------------------------------------------------------------------------------------------------
	@Before
	public void setup()	{
		MockitoAnnotations.initMocks(this);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testSaveCompanyInfoServiceImpl() throws Exception {
		when(companyInfoService.saveCompanyDetails(MasterData.getCompanyDetailsDTO())).thenReturn(true); 
		CompanyDetailsDTO commentFromdb = companyInfoService.getCompanyInfoByCode(companyDetailsDTO.getCompanyCode());
	    yakshaAssert(currentTest(), commentFromdb != null ? true : false, businessTestFile);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testCompanyInfoServiceImpl() throws Exception {
		boolean value = companyInfoService.saveCompanyDetails(MasterData.getCompanyDetailsDTO());
	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testViewAllCompanyDetails() throws Exception {
		CompanyDetailsDTO value = MasterData.getCompanyDetailsDTO();
        CompanyDetails company = new CompanyDetails((long)5002, "NSE", "IBM", "Navin Gupta", 765332.27, "AAA, BBB, CCC", "Base Location is in Mumbai, India");
		when(companyInfoServiceImpl.getCompanyDetailsDTO(company)).thenReturn(value);
		CompanyDetailsDTO commentFromdb = companyInfoServiceImpl.getCompanyDetailsDTO(company);		
	    yakshaAssert(currentTest(), commentFromdb == value ? true : false, businessTestFile);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testViewAllCompanyDetailsCase() throws Exception { 
		when(companyInfoServiceImpl.getCompanyDetailsDTO(new CompanyDetails())).thenReturn(null);
		CompanyDetailsDTO commentFromdb = companyInfoService.getCompanyInfoByCode(companyDetailsDTO.getCompanyCode());
	    yakshaAssert(currentTest(), commentFromdb==null ? true : false, businessTestFile);
	}
}