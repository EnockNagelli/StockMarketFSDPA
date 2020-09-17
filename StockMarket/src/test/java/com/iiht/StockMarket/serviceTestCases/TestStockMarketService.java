package com.iiht.StockMarket.serviceTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.businessTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.model.StockPriceDetails;
import com.iiht.StockMarket.services.StockMarketService;
import com.iiht.StockMarket.services.StockMarketServiceImpl;
import com.iiht.StockMarket.utilTestClass.MasterData;

public class TestStockMarketService
{
	@Mock
	private StockPriceDetailsDTO stockPriceDetailsDTO;
	
	@Mock
	private StockMarketService stockMarketService;

	@Mock
	private StockMarketServiceImpl stockMarketServiceImpl;
	// -------------------------------------------------------------------------------------------------------------------
	@Before
	public void setup()	{
		MockitoAnnotations.initMocks(this);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testSaveStockMarketServiceImpl() throws Exception {
		StockPriceDetailsDTO stock = MasterData.getStockPriceDetailsDTO();	
		Mockito.when(stockMarketService.saveStockPriceDetails(MasterData.getStockPriceDetailsDTO())).thenReturn(stock); 
		List<StockPriceDetailsDTO> commentFromdb = stockMarketService.getStockByCode(stockPriceDetailsDTO.getCompanyCode());
	    yakshaAssert(currentTest(), commentFromdb != null ? true : false, businessTestFile);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testStockMarketServiceImpl() throws Exception {
		StockPriceDetailsDTO value = stockMarketService.saveStockPriceDetails(MasterData.getStockPriceDetailsDTO());
	    yakshaAssert(currentTest(), value != null ? true : false, businessTestFile);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testViewCurrentStock() throws Exception {
		StockPriceDetailsDTO value = MasterData.getStockPriceDetailsDTO();
        StockPriceDetails stock = new StockPriceDetails((long)111, (long)5001, 32.27, LocalDate.parse("2020-08-01"), LocalTime.parse("09:30:00"));
		when(stockMarketServiceImpl.getStockPriceDetailsDTO(stock)).thenReturn(value);
		StockPriceDetailsDTO commentFromdb = stockMarketServiceImpl.getStockPriceDetailsDTO(stock);		
	    yakshaAssert(currentTest(), commentFromdb == value ? true : false, businessTestFile);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testViewCurrentStockCase() throws Exception {
		when(stockMarketServiceImpl.getStockPriceDetailsDTO(new StockPriceDetails())).thenReturn(null);
		List<StockPriceDetailsDTO> commentFromdb = stockMarketService.getStockByCode(stockPriceDetailsDTO.getCompanyCode());
	    yakshaAssert(currentTest(), commentFromdb==null ? true : false, businessTestFile);
	}
}