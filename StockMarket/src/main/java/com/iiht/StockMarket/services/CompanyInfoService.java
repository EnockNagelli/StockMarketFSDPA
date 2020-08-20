package com.iiht.StockMarket.services;

import java.util.List;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;

public interface CompanyInfoService {

	public Boolean saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO);
	public Boolean deleteCompany(Long companyCode);
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO getCompanyInfoByCode(Long companyCode);
	public List<CompanyDetailsDTO> getAllCompanies();
}