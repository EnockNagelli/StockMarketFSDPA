package com.iiht.StockMarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.StockMarket.repository.StockPriceRepository;

@Service
public class StockMarketServiceImpl implements StockMarketService{

	@Autowired
    private StockPriceRepository repository;

}
