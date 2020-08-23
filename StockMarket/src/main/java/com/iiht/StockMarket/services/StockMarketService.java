package com.iiht.StockMarket.services;

import java.sql.Date;
import java.util.List;

import com.iiht.StockMarket.dto.StockPriceDetailsDTO;

public interface StockMarketService {

	public Boolean saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO);
	public Boolean deleteStock(Long companyCode);
	public List<StockPriceDetailsDTO> getAllStockDetails();
	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode);
	//----------------------------------------------------------------------------------------
	public List<Object> getStockPriceIndex(Long companyCode, Date startDate, Date endDate);
}