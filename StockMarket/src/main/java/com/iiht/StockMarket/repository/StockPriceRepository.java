package com.iiht.StockMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.StockMarket.model.StockPriceDetails;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPriceDetails, Long> {
	
	//public StockPriceDetails findStockPriceByCode(Long companyCode);
}