package com.iiht.StockMarket.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// Bug creation 11:	"@Entity" annotation is removed from "CompanyDetails" class
@Table(name="CompanyDetails")
public class CompanyDetails implements Serializable {

	private static final long serialVersionUID = 531050392622113165L;

	@Id
	@Column(name = "companyCode")
	private Long companyCode;
	private String stockExchange;
	private String companyName;
	private String companyCEO;
	private Double turnover;
	private String boardOfDirectors;
	private String companyProfile;
		
	//---------------------------------------------------------------------------------------------------------------------------------
	public CompanyDetails() {
		super();
	}
	public CompanyDetails(Long companyCode, String stockExchange, String companyName, String companyCEO, Double turnover, String boardOfDirectors, String companyProfile) {
		super();
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.turnover = turnover;
		this.boardOfDirectors = boardOfDirectors;
		this.companyProfile = companyProfile;
	}

	//---------------------------------------------------------------------------------------------------------------------------------
	public Long getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getCompanyCEO() {
		return companyCEO;
	}
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public Double getTurnover() {
		return turnover;
	}
	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}
	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getCompanyProfile() {
		return companyProfile;
	}
	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public Set<StockPriceDetails> getStockPriceDetails() {
		return StockPriceDetails;
	}
	public void setStockPriceDetails(Set<StockPriceDetails> stockPriceDetails) {
		StockPriceDetails = stockPriceDetails;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	@OneToMany(cascade={CascadeType.MERGE})
	@JoinColumn(name="companyCode")
	private Set<StockPriceDetails> StockPriceDetails;
}