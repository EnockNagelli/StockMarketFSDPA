package com.iiht.StockMarket.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.iiht.StockMarket.dto.StockPriceDetailsDTO;

public interface StockMarketService 
{
	public StockPriceDetailsDTO saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO);
	public List<StockPriceDetailsDTO> deleteStock(Long companyCode);
	//----------------------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> getAllStockDetails();
	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode);
	//----------------------------------------------------------------------------------------
	public Map<String, Object> getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate);
}