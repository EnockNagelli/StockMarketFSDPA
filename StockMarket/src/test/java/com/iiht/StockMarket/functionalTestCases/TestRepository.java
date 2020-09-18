package com.iiht.StockMarket.functionalTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.businessTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.model.StockPriceDetails;
import com.iiht.StockMarket.repository.CompanyInfoRepository;
import com.iiht.StockMarket.repository.StockPriceRepository;
import com.iiht.StockMarket.utilTestClass.MasterData;
import com.iiht.StockMarket.utils.StockMarketUtility;

@RunWith(SpringRunner.class)
public class TestRepository {

    @Mock
    private CompanyInfoRepository companyRepository;
    
    @Mock
    private StockPriceRepository stockRepository;
    
    //============================================================================================================================
	@Test
    public void testCompanyRepository() throws Exception
    {
		CompanyDetails companyDetails = StockMarketUtility.convertToCompanyDetails(MasterData.getCompanyDetailsDTO());

		Mockito.when(companyRepository.save(companyDetails)).thenReturn(companyDetails);
        
		CompanyDetails companyData = companyRepository.findCompanyDetailsById(companyDetails.getCompanyCode());
	    
		yakshaAssert(currentTest(), (companyData != null ? true : false), businessTestFile);
    }
    //============================================================================================================================
	@Test
    public void testStockRepository() throws Exception
    {
		StockPriceDetails stockDetails = StockMarketUtility.convertToStockPriceDetails(MasterData.getStockPriceDetailsDTO());
		
		Mockito.when(stockRepository.save(stockDetails)).thenReturn(stockDetails);
        
		List<StockPriceDetails> stockData = stockRepository.findStockByCompanyCode(stockDetails.getCompanyCode());
	    
		yakshaAssert(currentTest(), (stockData != null ? true : false), businessTestFile);
    }	
}