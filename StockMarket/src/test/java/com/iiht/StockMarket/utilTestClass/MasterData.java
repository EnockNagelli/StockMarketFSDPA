package com.iiht.StockMarket.utilTestClass;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceIndexDTO;

public class MasterData
{
	public static CompanyDetailsDTO getCompanyDetailsDTO() 
	{
		CompanyDetailsDTO companyDetails = new CompanyDetailsDTO();
		
		companyDetails.setCompanyCode((long)1001);
		companyDetails.setStockExchange("BSE");
		companyDetails.setCompanyName("IIHT Pvt Ltd, Bangalore");
		companyDetails.setCompanyCEO("Praveen Kumar");
		companyDetails.setTurnover(65432.87);
		companyDetails.setBoardOfDirectors("AAA, BBB, CCC");
		companyDetails.setCompanyProfile("Base location is Bangalore, India");
		
		return companyDetails;
	}
	
	public static StockPriceDetailsDTO getStockPriceDetailsDTO() 
	{
		StockPriceDetailsDTO spDetails = new StockPriceDetailsDTO();
		
		spDetails.setId((long)1001);
		spDetails.setCompanyCode((long)2001);
		spDetails.setCurrentStockPrice(55.76);

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		spDetails.setStockPriceDate(LocalDate.parse("08/07/2020", dateFormat));
		spDetails.setStockPriceTime(LocalTime.parse("10:30:00"));

		return spDetails;
	}
	
	public static StockPriceIndexDTO getStockPriceIndexDTO() 
	{
		StockPriceIndexDTO stockPriceIndexDTO = new StockPriceIndexDTO();
		
		CompanyDetailsDTO companyDetailsDTO = getCompanyDetailsDTO();

		List<StockPriceDetailsDTO> stockPriceDetailsList = new ArrayList<StockPriceDetailsDTO>();
		
		StockPriceDetailsDTO stockPriceDetailsDTO1 = new StockPriceDetailsDTO();
		stockPriceDetailsDTO1.setId((long)1001);
		stockPriceDetailsDTO1.setCompanyCode((long)2001);
		stockPriceDetailsDTO1.setCurrentStockPrice(55.76);
		DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		stockPriceDetailsDTO1.setStockPriceDate(LocalDate.parse("08/07/2020", dateFormat1));
		stockPriceDetailsDTO1.setStockPriceTime(LocalTime.parse("10:30:00"));
		
		StockPriceDetailsDTO stockPriceDetailsDTO2 = new StockPriceDetailsDTO();
		stockPriceDetailsDTO2.setId((long)1002);
		stockPriceDetailsDTO2.setCompanyCode((long)2002);
		stockPriceDetailsDTO2.setCurrentStockPrice(75.76);
		DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		stockPriceDetailsDTO2.setStockPriceDate(LocalDate.parse("08/10/2020", dateFormat2));
		stockPriceDetailsDTO2.setStockPriceTime(LocalTime.parse("09:30:00"));		
		
		stockPriceDetailsList.add(stockPriceDetailsDTO1);
		stockPriceDetailsList.add(stockPriceDetailsDTO2);
			
		stockPriceIndexDTO.setCompanyDto(companyDetailsDTO);
		stockPriceIndexDTO.setStockPriceList(stockPriceDetailsList);
		stockPriceIndexDTO.setMaxStockPrice(85.55);
		stockPriceIndexDTO.setAvgStockPrice(47.15);
		stockPriceIndexDTO.setMinStockPrice(20.25);

		return stockPriceIndexDTO;
	}
	
	/*
	 * public static Properties getProperties() throws IOException { FileReader
	 * reader = new FileReader("src\\main\\resources\\application.properties");
	 * Properties properties = new Properties(); properties.load(reader); return
	 * properties; }
	 */
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	/*
	 * public static LocalSessionFactoryBean getSession() throws IOException {
	 * LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean(); Properties
	 * properties = getProperties();
	 * 
	 * DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 * 
	 * dataSource.setUrl(properties.getProperty("spring.datasource.url"));
	 * dataSource.setUsername(properties.getProperty("spring.datasource.username"));
	 * dataSource.setPassword(properties.getProperty("spring.datasource.password"));
	 * 
	 * lsfb.setDataSource(dataSource);
	 * lsfb.setPackagesToScan("com.iiht.StockMarket.model");
	 * 
	 * properties.put("hibernate.dialect",
	 * properties.getProperty("spring.jpa.properties.hibernate.dialect"));
	 * properties.put("hibernate.hbm2ddl.auto",
	 * properties.getProperty("spring.jpa.hibernate.ddl-auto"));
	 * properties.put("hibernate.show_sql",
	 * properties.getProperty("spring.jpa.show-sql"));
	 * 
	 * lsfb.setHibernateProperties(properties);
	 * 
	 * try { lsfb.afterPropertiesSet(); } catch (IOException e) {
	 * e.printStackTrace(); } return lsfb; }
	 */	
}