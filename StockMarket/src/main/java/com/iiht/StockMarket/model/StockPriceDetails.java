package com.iiht.StockMarket.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="StockPriceDetails")
public class StockPriceDetails implements Serializable {

	private static final long serialVersionUID = 3721936374836041464L;

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private Long companyCode;
	private Double currentStockPrice;
	private LocalDate stockPriceDate;
	private LocalTime stockPriceTime;

	//---------------------------------------------------------------------------------------------------------------------------------
	public StockPriceDetails() {
		super();
	}	
	public StockPriceDetails(Long id, Long companyCode, Double currentStockPrice, LocalDate stockPriceDate,	LocalTime stockPriceTime) {
		super();
		this.Id = id;
		this.companyCode = companyCode;
		this.currentStockPrice = currentStockPrice;
		this.stockPriceDate = stockPriceDate;
		this.stockPriceTime = stockPriceTime;
	}

	//---------------------------------------------------------------------------------------------------------------------------------
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public Long getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public Double getCurrentStockPrice() {
		return currentStockPrice;
	}
	public void setCurrentStockPrice(Double currentStockPrice) {
		this.currentStockPrice = currentStockPrice;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public LocalDate getStockPriceDate() {
		return stockPriceDate;
	}
	public void setStockPriceDate(LocalDate stockPriceDate) {
		this.stockPriceDate = stockPriceDate;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public LocalTime getStockPriceTime() {
		return stockPriceTime;
	}
	public void setStockPriceTime(LocalTime stockPriceTime) {
		this.stockPriceTime = stockPriceTime;
	}
}