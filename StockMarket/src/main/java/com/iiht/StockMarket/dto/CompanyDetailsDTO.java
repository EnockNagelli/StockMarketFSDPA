package com.iiht.StockMarket.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailsDTO {

	private Long companyCode;

	@Size(min = 1, max = 100)
	private String stockExchange;

	@Size(min = 1, max = 100)
	private String companyName;

	@Size(min = 1, max = 100)
	private String companyCEO;

	@Column(precision=10, scale=2)
	private Double turnover;

	@Size(min = 1, max = 255)
	private String boardOfDirectors;

	@Size(min = 1, max = 255)
	private String companyProfile;								// Brief writeup, about companies Services/Product, etc…
}