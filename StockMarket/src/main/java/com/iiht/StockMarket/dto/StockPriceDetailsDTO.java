package com.iiht.StockMarket.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceDetailsDTO {
	
	private Long Id;
	
	@Size(min = 1, max = 100)
	private String stockExchange;

	@Size(min = 1, max = 10)
	private Long companyCode;									// To which Company this Stock Price Info belongs to

	@Size(min = 1, max = 100)
	private String companyName;

	@Column(precision=10, scale=2)
	private Double currentStockPrice;							// Stock Price
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date stockPriceDate;							// Date of the Stock Price
	
	private String stockPriceTime;								// Stock Price at this Specific	
}