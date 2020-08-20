package com.iiht.StockMarket.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceDetaiksDTO {
	
	private Long Id;
	
	@NotNull
	@Size(min = 1, max = 10)
	private Long companyCode;									// To which Company this Stock Price Info belongs to

	@NotNull
	@Size(min = 1, max = 100)
	private String stockExchange;

	@NotNull
	@Size(min = 1, max = 100)
	private String companyName;

	@NotNull
	@Column(precision=10, scale=2)
	private Double currentStockPrice;							// Stock Price
	
	@NotNull
	private LocalDate stockPriceDate;							// Date of the Stock Price
	
	@NotNull
	private String stockPriceTime;								// Stock Price at this Specific	
}