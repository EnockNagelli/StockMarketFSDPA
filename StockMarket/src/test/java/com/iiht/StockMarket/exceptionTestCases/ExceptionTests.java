package com.iiht.StockMarket.exceptionTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.exceptionTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.services.CompanyInfoService;
import com.iiht.StockMarket.services.StockMarketService;
import com.iiht.StockMarket.utilTestClass.MasterData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest({CompanyInfoService.class, StockMarketService.class})
@RunWith(JUnitPlatform.class)
public class ExceptionTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CompanyInfoService companyInfoService;

	@MockBean
	private StockMarketService stockMarketService;

	//====================================================================================================================
	//			1. Exceptions tests regarding Company Operations
	//====================================================================================================================	
	@Test
	public void testCompanyForExceptionUponAddingNewCompany() throws Exception
	{
		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		companyDto.setCompanyName(null);
		
		Mockito.when(companyInfoService.saveCompanyDetails(companyDto)).thenReturn(companyDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/addCompany")
				.content(MasterData.asJsonString(companyDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}
	//--------------------------------------------------------------------------------------------
	@Test
	public void testCompanyForExceptionUponAddingCompanyWithNullValue() throws Exception
	{
		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();
		companyDto.setStockExchange(null);

		Mockito.when(companyInfoService.saveCompanyDetails(companyDto)).thenReturn(companyDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/addCompany")
				.content(MasterData.asJsonString(companyDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 400 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------
	@Test
	public void testCompanyForExceptionUponFetchingCompanyInfoByNullValue() throws Exception
	{
		Mockito.when(companyInfoService.getCompanyInfoById(2L)).thenReturn(null);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/getCompanyInfoByCode/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------
	@Test
	public void testCompanyForExceptionUponDeletingCompanyByNullValue() throws Exception
	{
		Mockito.when(companyInfoService.deleteCompany(2L)).thenReturn(null);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}	

	//====================================================================================================================
	//			2. Exceptions tests regarding Stock Operations
	//====================================================================================================================	
	@Test
	public void testStockForExceptionUponAddingNewStock() throws Exception
	{
		StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();

		Mockito.when(stockMarketService.saveStockPriceDetails(null)).thenReturn(stockDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/newStock")
				.content(MasterData.asJsonString(MasterData.getStockPriceDetailsDTO()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}		
	//--------------------------------------------------------------------------------------------
	@Test
	public void testStockForExceptionUponAddingStockWithNullValue() throws Exception
	{
		StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();
		stockDto.setCurrentStockPrice(null);

		Mockito.when(stockMarketService.saveStockPriceDetails(stockDto)).thenReturn(stockDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/addStock")
				.content(MasterData.asJsonString(stockDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 400 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------
	@Test
	public void testStockForExceptionUponFetchingStockDetailsByNullValue() throws Exception
	{
		Mockito.when(stockMarketService.getStockByCode(2L)).thenReturn(null);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockByCompanyCode/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------
	@Test
	public void testStockForExceptionUponDeletingStockByNullValue() throws Exception
	{
		List<StockPriceDetailsDTO> stockList = new ArrayList<StockPriceDetailsDTO>();

		Mockito.when(stockMarketService.deleteStock(2L)).thenReturn(stockList);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/stock/deleteStock/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}	
}