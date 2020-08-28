package com.iiht.StockMarket.functionalTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.businessTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
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

import com.iiht.StockMarket.controller.StockPriceController;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.services.StockMarketService;
import com.iiht.StockMarket.utilTestClass.JSONUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = StockPriceController.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TestStockPriceController 
{	
	@Mock
	private StockMarketService stockMarketService;

	@InjectMocks
	private StockPriceController stockPriceController;
	
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
	public void testAddNewStock() throws Exception {
        StockPriceDetailsDTO addStock = JSONUtils.createStockPriceDetailsDTO((long)111, (long)5001, 32.76, LocalDate.parse("2020-08-01"), LocalTime.parse("09:30:00"));
        ResponseEntity<Boolean> responseEntity = stockPriceController.addStockDetails(addStock);
        yakshaAssert(currentTest(), (responseEntity.getStatusCodeValue() > 0 ? true : false), businessTestFile);
	}
	//-----------------------------------------------------------------------------
	/*
	 * Description : This test is to perform view all the posts from database
	 */
	@Test 
	public void testViewAllStock() throws Exception { 
        // Given input
        StockPriceDetailsDTO stock1 = JSONUtils.createStockPriceDetailsDTO((long)111, (long)5001, 32.76, LocalDate.parse("2020-08-01"), LocalTime.parse("09:30:00"));
        StockPriceDetailsDTO stock2 = JSONUtils.createStockPriceDetailsDTO((long)112, (long)5001, 42.56, LocalDate.parse("2020-08-15"), LocalTime.parse("09:30:00"));
        StockPriceDetailsDTO stock3 = JSONUtils.createStockPriceDetailsDTO((long)113, (long)5001, 52.66, LocalDate.parse("2020-08-30"), LocalTime.parse("09:30:00"));

        List<StockPriceDetailsDTO> list = new ArrayList<StockPriceDetailsDTO>();
        list.add(stock1);
        list.add(stock2);
        list.add(stock3);
        // when
        when(stockMarketService.getAllStockDetails()).thenReturn(list);
        
        List<StockPriceDetailsDTO> fromController = stockMarketService.getAllStockDetails();
        // then
        assertThat(fromController.size()).isEqualTo(3);
        
        assertThat(fromController.get(0).getId()).isEqualTo(stock1.getId());
        assertThat(fromController.get(0).getCompanyCode()).isEqualTo(stock1.getCompanyCode());
        assertThat(fromController.get(0).getCurrentStockPrice()).isEqualTo(stock1.getCurrentStockPrice());

        assertThat(fromController.get(0).getId()).isEqualTo(stock1.getId());
        assertThat(fromController.get(1).getCompanyCode()).isEqualTo(stock2.getCompanyCode());
        assertThat(fromController.get(2).getCurrentStockPrice()).isEqualTo(stock3.getCurrentStockPrice());
        
        yakshaAssert(currentTest(), (fromController.size() == 3 ? true : false), businessTestFile);
	}
	  
	//-----------------------------------------------------------------------------
	/*
	 * Description : This test is to perform check the null operation against view all posts operation
	 */
	@Test 
	public void testViewAllStockCase() throws Exception {
 		List<StockPriceDetailsDTO> list = new ArrayList<StockPriceDetailsDTO>();
		when(stockMarketService.getAllStockDetails()).thenReturn(list);
        // when
        List<StockPriceDetailsDTO> fromController = stockMarketService.getAllStockDetails();
        // then
        assertThat(fromController.size()).isEqualTo(0);
		yakshaAssert(currentTest(), (fromController.size() == 0 ? true : false) ? true : false, businessTestFile); 
	}
}