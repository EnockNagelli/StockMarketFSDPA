package com.iiht.StockMarket.exception;

public class StockNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3149606590630559956L;

	// Bug creation 10:	"super(message)" statement is removed for "StockNotFoundException" constructor
	public StockNotFoundException(String message) {
	}
}