package com.iiht.StockMarket.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceIndexDTO;
import com.iiht.StockMarket.exception.StockNotFoundException;
import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.model.StockPriceDetails;
import com.iiht.StockMarket.repository.CompanyInfoRepository;
import com.iiht.StockMarket.repository.StockPriceRepository;
import com.iiht.StockMarket.utils.StockMarketUtility;

@Service
@Transactional
public class StockMarketServiceImpl implements StockMarketService {

	@Autowired
    private StockPriceRepository stockRepository;

	@Autowired
    private CompanyInfoRepository companyRepository;
	
	//----------------------------------------------------------------------------
	public StockPriceDetailsDTO saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO) {
		
		StockPriceDetails newStock = StockMarketUtility.convertToStockPriceDetails(stockPriceDetailsDTO);

		stockRepository.save(newStock);
		
		return stockPriceDetailsDTO;
	};
	//----------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> deleteStock(Long companyCode) {
		
		Integer value = stockRepository.deleteStockByCompanyCode(companyCode);

		if(value != null)
			return StockMarketUtility.convertToStockPriceDetailsDtoList(stockRepository.findStockByCompanyCode(companyCode));
		else
			throw new StockNotFoundException("Invalid Company Code. No Stock available against this company code.");
	};
	//----------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> getAllStockDetails() {

		List<StockPriceDetails> stockDetails = stockRepository.findAll();
		
		if(CollectionUtils.isEmpty(stockDetails))
			return null;
		else
			return stockDetails.stream().map(StockMarketUtility::convertToStockPriceDetailsDTO).collect(Collectors.toList());
	};
	//----------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode){

		List<StockPriceDetails> stockDetails = stockRepository.findStockByCompanyCode(companyCode);
		
		if(CollectionUtils.isEmpty(stockDetails))
			return null;
		else
			return stockDetails.stream().map(StockMarketUtility::convertToStockPriceDetailsDTO).collect(Collectors.toList());
	};
	//----------------------------------------------------------------------------
	public StockPriceDetailsDTO getStockPriceDetailsDTO(StockPriceDetails stockDetails)	{
		return new StockPriceDetailsDTO(stockDetails.getId(), stockDetails.getCompanyCode(), stockDetails.getCurrentStockPrice(), stockDetails.getStockPriceDate(), stockDetails.getStockPriceTime());
	};	
	//----------------------------------------------------------------------------
	public Double getMaxStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findMaxStockPrice(companyCode, startDate, endDate);
	};
	public Double getAvgStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findAvgStockPrice(companyCode, startDate, endDate);
	};
	public Double getMinStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findMinStockPrice(companyCode, startDate, endDate);
	};
	
	public StockPriceIndexDTO getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate) {
		
		//Map<String, Object> stockPriceIndex = new TreeMap<String, Object>();

		StockPriceIndexDTO stockPriceIndexDto = new StockPriceIndexDTO();
		
		CompanyDetails companyDetails = companyRepository.findCompanyDetailsById(companyCode);
		stockPriceIndexDto.setCompanyDto(StockMarketUtility.convertToCompanyDetailsDTO(companyDetails));
		
		List<StockPriceDetailsDTO> stockPriceList = getStockByCode(companyCode);
		stockPriceIndexDto.setStockPriceList(stockPriceList);

		Double maxStockPrice = getMaxStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setMaxStockPrice(maxStockPrice);
		
		Double avgStockPrice = getAvgStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setAvgStockPrice(avgStockPrice);

		Double minStockPrice = getMinStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setMinStockPrice(minStockPrice);

		//Double currentStockPrice = stockList.get(stockList.size()-1).getCurrentStockPrice();

		/*
		 * stockPriceIndex.put("1. Stock Exchange ", company.getStockExchange());
		 * stockPriceIndex.put("2. Company Name ", company.getCompanyName());
		 * stockPriceIndex.put("3. Company Code ", companyCode);
		 * stockPriceIndex.put("4. Current Stock Price ", currentStockPrice);
		 * stockPriceIndex.put("5. From Date ", startDate);
		 * stockPriceIndex.put("6. To Date ", endDate);
		 * stockPriceIndex.put("7. Minimum Stock Price ", minStockPrice);
		 * stockPriceIndex.put("8. Average Stock Price ", avgStockPrice);
		 * stockPriceIndex.put("9. Maximum Stock Price ", maxStockPrice);
		 */		
		return stockPriceIndexDto;
	};
}