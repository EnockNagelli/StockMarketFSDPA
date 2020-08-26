package com.iiht.StockMarket.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	private Long companyCode;									// To which Company this Stock Price Info belongs to
	private Double currentStockPrice;							// Stock Price of the company
	private LocalDate stockPriceDate;							// Date of the Stock Price registered
	private LocalTime stockPriceTime;							// Time of the Stock Price registered
}