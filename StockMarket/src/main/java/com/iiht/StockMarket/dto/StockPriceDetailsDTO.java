package com.iiht.StockMarket.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StockPriceDetailsDTO {
	
	private Long Id;
	
	@NotNull
	@Size(min = 1, max = 10)
	private Long companyCode;

	@NotNull
	@Column(precision=10, scale=2)
	private Double currentStockPrice;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate stockPriceDate;
	
	@NotNull
	@Column(columnDefinition = "TIME")
	private LocalTime stockPriceTime;	

	//---------------------------------------------------------------------------------------------------------------------------------
	public StockPriceDetailsDTO() {
		super();
	}
	public StockPriceDetailsDTO(Long id, @NotNull @Size(min = 1, max = 10) Long companyCode,
			@NotNull Double currentStockPrice, @NotNull LocalDate stockPriceDate, @NotNull LocalTime stockPriceTime) {
		super();
		Id = id;
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