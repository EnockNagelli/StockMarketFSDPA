package com.iiht.StockMarket.services;

import java.time.LocalDate;
import java.util.List;

import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceIndexDTO;

public interface StockMarketService 
{
	public StockPriceDetailsDTO saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO);
	public List<StockPriceDetailsDTO> deleteStock(Long companyCode);
	//----------------------------------------------------------------------------------------
	//Bug creation 18: parameter type "Long" changed to "long"  
	public List<StockPriceDetailsDTO> getStockByCode(long companyCode);
	//----------------------------------------------------------------------------------------
	public StockPriceIndexDTO getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate);
}