package com.iiht.StockMarket.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="StockPriceDetails")
public class StockPriceDetails implements Serializable {

	private static final long serialVersionUID = 3721936374836041464L;

	@javax.persistence.Id
	private Long Id;
	
	private Long companyCode;									// To which Company this Stock Price Info belongs to
	private String stockExchange;
	private String companyName;
	private Double currentStockPrice;							// Stock Price
	private LocalDate stockPriceDate;							// Date of the Stock Price
	private String stockPriceTime;		
}