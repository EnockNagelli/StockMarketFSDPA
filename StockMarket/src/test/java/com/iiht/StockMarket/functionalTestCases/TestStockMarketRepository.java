package com.iiht.StockMarket.functionalTestCases;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.iiht.StockMarket.model.StockPriceDetails;
import com.iiht.StockMarket.repository.StockPriceRepository;

import static com.iiht.StockMarket.utilTestClass.TestUtils.businessTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
public class TestStockMarketRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StockPriceRepository repository;

    @Test
    public void testFindStockByCompanyCode() throws Exception 
    {
        entityManager.persist(new StockPriceDetails((long)37, (long)5002, 32.76, LocalDate.parse("2020-08-15"), LocalTime.parse("09:30:00")));

        entityManager.persist(new StockPriceDetails((long)38, (long)5002, 52.27, LocalDate.parse("2020-08-25"), LocalTime.parse("09:30:00")));
        
        List<StockPriceDetails> stockInfo = (List<StockPriceDetails>) repository.findStockByCompanyCode((long)5002);
        
	    yakshaAssert(currentTest(), (stockInfo != null ? true : false), businessTestFile);
    }
}