package com.iiht.StockMarket.functionalTestCases;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.model.StockPriceDetails;
import com.iiht.StockMarket.repository.CompanyInfoRepository;

import static com.iiht.StockMarket.utilTestClass.TestUtils.businessTestFile;
import static com.iiht.StockMarket.utilTestClass.TestUtils.currentTest;
import static com.iiht.StockMarket.utilTestClass.TestUtils.yakshaAssert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
public class TestCompanyInfoRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyInfoRepository repository;

    @Test
    public void testFindByCompanyCode() throws Exception
    {
    	Set<StockPriceDetails> spSet = new TreeSet<StockPriceDetails>();
    	
    	StockPriceDetails spd1 = new StockPriceDetails();
    	spd1.setId((long)111);
    	spd1.setCompanyCode((long)5001);
    	spd1.setCurrentStockPrice(56.87);
    	spd1.setStockPriceDate(LocalDate.parse("2020-08-01"));
    	spd1.setStockPriceTime(LocalTime.parse("09:30:00"));
    	
    	StockPriceDetails spd2 = new StockPriceDetails();
    	spd2.setId((long)112);
    	spd2.setCompanyCode((long)5001);
    	spd2.setCurrentStockPrice(76.57);
    	spd2.setStockPriceDate(LocalDate.parse("2020-08-15"));
    	spd2.setStockPriceTime(LocalTime.parse("09:30:00"));

    	spSet.add(spd1);
    	spSet.add(spd2);

        entityManager.persist(new CompanyDetails((long)5001, "BSE", "Cognizant", "Praveen Kumar", 65432.76, "AAA, BBB, CCC", "Base Location is in Chennai, India", spSet));

        StockPriceDetails spd3 = new StockPriceDetails();
    	spd3.setId((long)113);
    	spd3.setCompanyCode((long)5002);
    	spd3.setCurrentStockPrice(56.87);
    	spd3.setStockPriceDate(LocalDate.parse("2020-08-01"));
    	spd3.setStockPriceTime(LocalTime.parse("09:30:00"));
    	
    	StockPriceDetails spd4 = new StockPriceDetails();
    	spd4.setId((long)114);
    	spd4.setCompanyCode((long)5002);
    	spd4.setCurrentStockPrice(76.57);
    	spd4.setStockPriceDate(LocalDate.parse("2020-08-15"));
    	spd4.setStockPriceTime(LocalTime.parse("09:30:00"));

    	spSet.add(spd3);
    	spSet.add(spd4);

        entityManager.persist(new CompanyDetails((long)5002, "BSE", "Virstusa", "Shekar", 485432.76, "AAA, BBB, CCC", "Base Location is in Mumbai, India", spSet));

        CompanyDetails companyInfo = (CompanyDetails) repository.findCompanyDetailsById((long)5002);
            
	    yakshaAssert(currentTest(), (companyInfo != null ? true : false), businessTestFile);	    
    }
}