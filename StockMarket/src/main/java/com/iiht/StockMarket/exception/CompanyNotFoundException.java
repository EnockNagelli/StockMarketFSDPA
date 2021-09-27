package com.iiht.StockMarket.exception;

public class CompanyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3156833551002324065L;

	// Bug creation 9:	"super(message)" statement is removed for "CompanyNotFoundException" constructor
	public CompanyNotFoundException(String message) {
	}
}