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
//Bug creation 19: "@Transactional" annotation is removed from StockMarketServiceImpl class declaration
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
	public List<StockPriceDetailsDTO> getStockByCode(long companyCode){

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
		
		StockPriceIndexDTO stockPriceIndexDto = new StockPriceIndexDTO();
		
		CompanyDetails companyDetails = companyRepository.findCompanyDetailsById(companyCode);
		stockPriceIndexDto.setCompanyDto(StockMarketUtility.convertToCompanyDetailsDTO(companyDetails));
		
		List<StockPriceDetailsDTO> stockPriceList = getStockByCode(companyCode);
		stockPriceIndexDto.setStockPriceList(stockPriceList);

		//Bug creation 20:	Removed to fetch maxStockPrice and setMaxStockPrice 
//		Double maxStockPrice = getMaxStockPrice(companyCode, startDate, endDate);
//		stockPriceIndexDto.setMaxStockPrice(maxStockPrice);
		
		Double avgStockPrice = getAvgStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setAvgStockPrice(avgStockPrice);

		Double minStockPrice = getMinStockPrice(companyCode, startDate, endDate);
		stockPriceIndexDto.setMinStockPrice(minStockPrice);

		return stockPriceIndexDto;
	};
}