package com.iiht.StockMarket.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.iiht.StockMarket.model.StockPriceDetails;

// Bug creation 14:	This is interface need to annotate with '@Repository'
public interface StockPriceRepository extends JpaRepository<StockPriceDetails, Long> {
	
	@Query("select cd FROM StockPriceDetails cd WHERE cd.companyCode=?1")
	public List<StockPriceDetails> findStockByCompanyCode(Long companyCode);

	@Transactional
	@Modifying
	@Query("delete FROM StockPriceDetails de WHERE de.companyCode=?1")
	public Integer deleteStockByCompanyCode(Long companyCode);
	
	@Query("SELECT MAX(currentStockPrice) FROM StockPriceDetails sp WHERE sp.companyCode=?1 AND sp.stockPriceDate BETWEEN ?2 AND ?3")
	public Double findMaxStockPrice(Long companyCode, LocalDate from, LocalDate to);

	@Query("SELECT AVG(currentStockPrice) FROM StockPriceDetails sp WHERE sp.companyCode=?1 AND sp.stockPriceDate BETWEEN ?2 AND ?3")
	public Double findAvgStockPrice(Long companyCode, LocalDate from, LocalDate to);

	@Query("SELECT MIN(currentStockPrice) FROM StockPriceDetails sp WHERE sp.companyCode=?1 AND sp.stockPriceDate BETWEEN ?2 AND ?3")
	public Double findMinStockPrice(Long companyCode, LocalDate from, LocalDate to);
}