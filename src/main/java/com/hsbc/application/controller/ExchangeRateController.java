package com.hsbc.application.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hsbc.application.entity.ExchangeRatesResponse;
import com.hsbc.application.service.ExchangeRatesService;

@RestController
public class ExchangeRateController {
	
	@Autowired
	ExchangeRatesService exchangeRatesService;
	
	@GetMapping("/saveRates")
	public String saveExchangeRates() throws ParseException {
		return exchangeRatesService.insertExchangeRates();
	}
	
	@GetMapping("/readRates/{currencyRateToFind}/{userProvidedDate}")
	public List<ExchangeRatesResponse> readExchangeRates(@PathVariable("userProvidedDate") String userProvidedDate,@PathVariable("currencyRateToFind") String currencyRateToFind) throws ParseException {
		return exchangeRatesService.readRates(userProvidedDate,currencyRateToFind);
	}
}