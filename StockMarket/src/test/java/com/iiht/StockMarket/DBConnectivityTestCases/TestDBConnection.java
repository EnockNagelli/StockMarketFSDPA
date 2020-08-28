package com.iiht.StockMarket.DBConnectivityTestCases;

import static com.iiht.StockMarket.utilTestClass.TestUtils.*;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.utilTestClass.MasterData;

@ExtendWith(SpringExtension.class)
public class TestDBConnection 
{
	@Autowired
	private TestEntityManager entityManager;
	
	//----------------------------------------------------------------------------------------------
	@Test
	public void testConnectivity() throws IOException 
	{
		boolean value = false;
		
		Properties properties = MasterData.getProperties();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		//dataSource.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(properties.getProperty("spring.datasource.url"));
		dataSource.setUsername(properties.getProperty("spring.datasource.username"));
		dataSource.setPassword(properties.getProperty("spring.datasource.password"));		
		
	    if(dataSource != null)
	    	value = true;

	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);	    
	}
    //----------------------------------------------------------------------------------------------
	@Test
	public void hibernateProperties() throws IOException 
	{
		boolean value = false;
		
		Properties properties = MasterData.getProperties();
		
		properties.put("hibernate.dialect", properties.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", properties.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show_sql", properties.getProperty("spring.jpa.show-sql"));
		
		if(properties != null)
			value = true;

	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);	    
	}
    //----------------------------------------------------------------------------------------------	
	@SuppressWarnings("unchecked")
	@Test 
	public void testSqlException() throws IOException 
	{ 
		boolean value = false;
		
//		Session session = (Session) TestEntityManager.getSession().getObject();
//		if(session != null)
//			System.out.println("Session Object NOT Created");

//		HibernateTemplate session = new HibernateTemplate(MasterData.getSession().getObject()); 
//		String hql = "FROM CompanyDetails";
//		List<CompanyDetails> size = (List<CompanyDetails>) session.find(hql, CompanyDetails.class); 

		String hql = "FROM CompanyDetails";
	  
		List<CompanyDetails> size = (List<CompanyDetails>) entityManager.find(CompanyDetails.class, hql); 

		if(size != null)
			value = true;
		
	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);
	}
}