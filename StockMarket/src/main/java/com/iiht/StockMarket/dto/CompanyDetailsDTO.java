package com.iiht.StockMarket.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailsDTO {

	private Long companyCode;

	@NotNull
	@Size(min = 1, max = 100)
	private String stockExchange;

	@NotNull
	@Size(min = 1, max = 100)
	private String companyName;

	@NotNull
	@Size(min = 1, max = 100)
	private String companyCEO;

	@NotNull
	@Column(precision=10, scale=2)
	private Double turnover;

	@NotNull
	@Size(min = 1, max = 255)
	private String boardOfDirectors;

	@NotNull
	@Size(min = 1, max = 255)
	private String companyProfile;								// Brief writeup, about companies Services/Product, etc…
}