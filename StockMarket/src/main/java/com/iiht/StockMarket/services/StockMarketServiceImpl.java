package com.iiht.StockMarket.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.StockMarket.model.StockPriceDetails;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.repository.StockPriceRepository;

@Service
public class StockMarketServiceImpl implements StockMarketService {

	@Autowired
    private StockPriceRepository repository;
	//----------------------------------------------------------------------------
	public Boolean saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO) {
		
		StockPriceDetails newStock = new StockPriceDetails();

		newStock.setId(stockPriceDetailsDTO.getId());
		newStock.setStockExchange(stockPriceDetailsDTO.getStockExchange());
		newStock.setCompanyCode(stockPriceDetailsDTO.getCompanyCode());
		newStock.setCompanyName(stockPriceDetailsDTO.getCompanyName());
		newStock.setCurrentStockPrice(stockPriceDetailsDTO.getCurrentStockPrice());
		newStock.setStockPriceDate(stockPriceDetailsDTO.getStockPriceDate());
		newStock.setStockPriceTime(stockPriceDetailsDTO.getStockPriceTime());
		
		repository.save(newStock);
		return Boolean.TRUE;
	};
	//----------------------------------------------------------------------------
	public Boolean deleteStock(Long companyCode) {
		
		Integer value = repository.deleteStockByCompanyCode(companyCode);

		if(value != null)
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	};
	//----------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> getAllStockDetails() {

		List<StockPriceDetails> stockDetails = repository.findAll();
		
		if(CollectionUtils.isEmpty(stockDetails))
			return null;
		else
			return stockDetails.stream().map(this::getStockPriceDetailsDTO).collect(Collectors.toList());
	};
	//----------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode){

		List<StockPriceDetails> stockDetails = repository.findStockByCompanyCode(companyCode);
		
		if(CollectionUtils.isEmpty(stockDetails))
			return null;
		else
			return stockDetails.stream().map(this::getStockPriceDetailsDTO).collect(Collectors.toList());
	};
	//----------------------------------------------------------------------------
	public StockPriceDetailsDTO getStockPriceDetailsDTO(StockPriceDetails stockDetails)	{
		return new StockPriceDetailsDTO(stockDetails.getId(), stockDetails.getStockExchange(), stockDetails.getCompanyCode(), stockDetails.getCompanyName(), stockDetails.getCurrentStockPrice(), stockDetails.getStockPriceDate(), stockDetails.getStockPriceTime());
	};	
	//----------------------------------------------------------------------------
	public Double getMaxStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return repository.findMaxStockPrice(companyCode, startDate, endDate);
	};
	public Double getAvgStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return repository.findAvgStockPrice(companyCode, startDate, endDate);
	};
	public Double getMinStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return repository.findMinStockPrice(companyCode, startDate, endDate);
	};
	
	public Map<String, Object> getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate) {
		
		Double maxStockPrice = getMaxStockPrice(companyCode, startDate, endDate);
		Double avgStockPrice = getAvgStockPrice(companyCode, startDate, endDate);
		Double minStockPrice = getMinStockPrice(companyCode, startDate, endDate);
		
		List<StockPriceDetailsDTO> stockList = getStockByCode(companyCode);

		String stockExchange = stockList.get(stockList.size()-1).getStockExchange();
		String companyName = stockList.get(stockList.size()-1).getCompanyName();
		Double currentStockPrice = stockList.get(stockList.size()-1).getCurrentStockPrice();
		
		Map<String, Object> stockPriceIndex = new TreeMap<String, Object>();
		
		stockPriceIndex.put("1. Stock Exchange ", stockExchange);
		stockPriceIndex.put("2. Company Name ", companyName);
		stockPriceIndex.put("3. Company Code ", companyCode);
		stockPriceIndex.put("4. Current Stock Price ", currentStockPrice);
		stockPriceIndex.put("5. From Date ", startDate);
		stockPriceIndex.put("6. To Date ", endDate);
		stockPriceIndex.put("7. Minimum Stock Price ", minStockPrice);
		stockPriceIndex.put("8. Average Stock Price ", avgStockPrice);
		stockPriceIndex.put("9. Maximum Stock Price ", maxStockPrice);
		
		return stockPriceIndex;
	};
}