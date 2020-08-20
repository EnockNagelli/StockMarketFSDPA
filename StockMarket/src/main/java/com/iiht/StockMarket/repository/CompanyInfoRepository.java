package com.iiht.StockMarket.repository;

import org.springframework.stereotype.Repository;

import com.iiht.StockMarket.model.CompanyDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyDetails, Long>
{
	@Query("select cd FROM CompanyDetails cd WHERE cd.CompanyCode=?1")
	CompanyDetails findCompanyDetailsById(Long companyCode);
}