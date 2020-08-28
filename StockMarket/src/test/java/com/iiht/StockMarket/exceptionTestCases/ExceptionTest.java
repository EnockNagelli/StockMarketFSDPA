package com.iiht.StockMarket.exceptionTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

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

import com.iiht.StockMarket.controller.CompanyInfoController;
import com.iiht.StockMarket.controller.StockPriceController;
import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.services.CompanyInfoService;
import com.iiht.StockMarket.services.StockMarketService;
import com.iiht.StockMarket.utilTestClass.MasterData;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest({CompanyInfoController.class, StockPriceController.class})
public class ExceptionTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CompanyInfoService companyService;

	@MockBean
	private StockMarketService stockMarketService;
	
	@Test
	public void testSaveCompanyDetailsException() throws Exception
	{
		CompanyDetailsDTO badData = MasterData.getCompanyDetailsDTO();
		
		badData.setCompanyCode((long)2001);
		badData.setStockExchange("BSE");
		badData.setCompanyName("Cognizant");
		badData.setCompanyCEO("Praful Daga");
		badData.setTurnover(435612.98);
		badData.setBoardOfDirectors("AAA, BBB, CCC, DDD");
		badData.setCompanyProfile("Base location is in Chennai, India.");
		
		when(companyService.saveCompanyDetails(MasterData.getCompanyDetailsDTO())).thenReturn(true);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company/newCompany")
				.content(MasterData.asJsonString(MasterData.getCompanyDetailsDTO()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}
	
	@Test
	public void testSaveStockPriceException() throws Exception
	{
		StockPriceDetailsDTO badData = MasterData.getStockPriceDetailsDTO();
		
		badData.setId(badData.getId());
		badData.setCompanyCode((long)2001);
		badData.setCurrentStockPrice(55.76);

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		badData.setStockPriceDate(LocalDate.parse("08/07/2020", dateFormat));
		badData.setStockPriceTime(LocalTime.parse("10:30:00"));

		when(stockMarketService.saveStockPriceDetails(MasterData.getStockPriceDetailsDTO())).thenReturn(true);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/newStock")
				.content(MasterData.asJsonString(MasterData.getStockPriceDetailsDTO()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), 
		(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}	
}