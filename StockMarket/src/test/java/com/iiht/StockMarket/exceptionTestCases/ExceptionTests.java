package com.iiht.StockMarket.exceptionTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.exceptionTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.test.context.junit4.SpringRunner;
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
@RunWith(SpringRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest({CompanyInfoService.class})
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
		CompanyDetailsDTO companyData = MasterData.getCompanyDetailsDTO();
	
		Mockito.when(companyInfoService.saveCompanyDetails(MasterData.getCompanyDetailsDTO())).thenReturn(companyData);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/addCompany")
				.content(MasterData.asJsonString(MasterData.getCompanyDetailsDTO()))
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
		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();

		Mockito.when(companyInfoService.getCompanyInfoById(null)).thenReturn(companyDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/getCompanyInfoByCode/"+null)
				.content(MasterData.asJsonString(companyDto))
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
		CompanyDetailsDTO companyDto = MasterData.getCompanyDetailsDTO();

		Mockito.when(companyInfoService.deleteCompany(null)).thenReturn(companyDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/deleteCompany/"+null)
				.content(MasterData.asJsonString(companyDto))
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
		StockPriceDetailsDTO stockDetails = MasterData.getStockPriceDetailsDTO();

		when(stockMarketService.saveStockPriceDetails(MasterData.getStockPriceDetailsDTO())).thenReturn(stockDetails);
				
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
		List<StockPriceDetailsDTO> stockList = new ArrayList<StockPriceDetailsDTO>();
		//StockPriceDetailsDTO stockDto = MasterData.getStockPriceDetailsDTO();

		Mockito.when(stockMarketService.getStockByCode(null)).thenReturn(stockList);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/getStockByCompanyCode/"+null)
				.content(MasterData.asJsonString(stockList))
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

		Mockito.when(stockMarketService.deleteStock(null)).thenReturn(stockList);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/stock/deleteStock/"+null)
				.content(MasterData.asJsonString(stockList))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}	
}