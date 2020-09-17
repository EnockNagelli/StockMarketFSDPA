package com.iiht.StockMarket.serviceTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.businessTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
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
		
		Mockito.when(companyInfoService.saveCompanyDetails(MasterData.getCompanyDetailsDTO())).thenReturn(companyDetailsDTO); 
		
		CompanyDetailsDTO commentFromdb = companyInfoService.getCompanyInfoById(companyDetailsDTO.getCompanyCode());
	    
		yakshaAssert(currentTest(), commentFromdb != null ? true : false, businessTestFile);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testCompanyInfoServiceImpl() throws Exception {
		
		CompanyDetailsDTO value = companyInfoService.saveCompanyDetails(MasterData.getCompanyDetailsDTO());
	    
		yakshaAssert(currentTest(), value != null ? true : false, businessTestFile);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testViewAllCompanyDetails() throws Exception {
		
		//CompanyDetailsDTO value = MasterData.getCompanyDetailsDTO();
		List<CompanyDetailsDTO> companyList = new ArrayList<CompanyDetailsDTO>();

		//CompanyDetails company = new CompanyDetails((long)5002, "BSE", "IBM", "Navin Gupta", 665332.27, "AAA, BBB, CCC", "Base Location is in Mumbai, India");
		
        Mockito.when(companyInfoServiceImpl.getAllCompanies()).thenReturn(companyList);
		
		List<CompanyDetailsDTO> commentFromdb = companyInfoServiceImpl.getAllCompanies();		
	    
		yakshaAssert(currentTest(), commentFromdb != null ? true : false, businessTestFile);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testViewACompanyDetails() throws Exception {
		
		CompanyDetailsDTO value = MasterData.getCompanyDetailsDTO();

		Mockito.when(companyInfoServiceImpl.getCompanyInfoById(value.getCompanyCode())).thenReturn(value);
		
		CompanyDetailsDTO commentFromdb = companyInfoService.getCompanyInfoById(companyDetailsDTO.getCompanyCode());
	    
		yakshaAssert(currentTest(), commentFromdb == null ? true : false, businessTestFile);
	}
}