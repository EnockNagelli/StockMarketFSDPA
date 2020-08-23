package com.iiht.StockMarket;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StockMarketApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}	 	

	public static void main(String[] args) {
		SpringApplication.run(StockMarketApplication.class, args);
	}
}



//@SpringBootApplication(scanBasePackages ="com.iiht.StockMarket")
//@EnableJpaRepositories(basePackages ="com.iiht.StockMarket.repository")
//@EntityScan("com.iiht.StockMarket.model")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
