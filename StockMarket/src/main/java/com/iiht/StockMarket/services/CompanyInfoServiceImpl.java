package com.iiht.StockMarket.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.repository.CompanyInfoRepository;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
	
	@Autowired
	private CompanyInfoRepository repository; 
	
	public Boolean saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO) {

		CompanyDetails newCompany = new CompanyDetails();
		
		newCompany.setCompanyCode(companyDetailsDTO.getCompanyCode());
		newCompany.setStockExchange(companyDetailsDTO.getStockExchange());
		newCompany.setCompanyName(companyDetailsDTO.getCompanyName());
		newCompany.setCompanyCEO(companyDetailsDTO.getCompanyCEO());
		newCompany.setTurnover(companyDetailsDTO.getTurnover());
		newCompany.setBoardOfDirectors(companyDetailsDTO.getBoardOfDirectors());
		newCompany.setCompanyProfile(companyDetailsDTO.getCompanyProfile());

		repository.save(newCompany);
		return Boolean.TRUE;
	};
	//----------------------------------------------------------------------------
	public Boolean deleteCompany(Long companyCode) {
		Integer value = repository.deleteByCompanyCode(companyCode);
		if(value != null)
			return true;
		else
			return false;
	};
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO getCompanyInfoByCode(Long companyCode) {
		
		CompanyDetails companyInfo = repository.findCompanyDetailsById(companyCode);

		return getCompanyDetailsDTO(companyInfo);
	};
	
	//----------------------------------------------------------------------------
	public List<CompanyDetailsDTO> getAllCompanies() {
		
		List<CompanyDetails> companyInfo = repository.findAll();
		
		if(CollectionUtils.isEmpty(companyInfo))
			return null;
		else
			return companyInfo.stream().map(this::getCompanyDetailsDTO).collect(Collectors.toList());
	};
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO getCompanyDetailsDTO(CompanyDetails companyInfo)	{
		return new CompanyDetailsDTO(companyInfo.getCompanyCode(), companyInfo.getStockExchange(), companyInfo.getCompanyName(), companyInfo.getCompanyCEO(), companyInfo.getTurnover(), companyInfo.getBoardOfDirectors(), companyInfo.getCompanyProfile());
	};
}