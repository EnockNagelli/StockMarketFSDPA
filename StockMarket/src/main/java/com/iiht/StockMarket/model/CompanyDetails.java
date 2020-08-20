package com.iiht.StockMarket.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CompanyDetails")
public class CompanyDetails implements Serializable {

	private static final long serialVersionUID = 531050392622113165L;

	@javax.persistence.Id
	private Long Id;
	
	private String stockExchange;
	private Long companyCode;
	private String companyName;
	private String companyCEO;
	private Double turnover;
	private String boardOfDirectors;
	private String companyProfile;								// Brief writeup, about companies Services/Product, etc…
	
	@OneToMany(cascade= {CascadeType.MERGE})
	@JoinColumn(name="companyCode")
	private Set<StockPriceDetails> StockPriceDetails;	
}