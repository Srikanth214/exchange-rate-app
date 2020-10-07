package com.hsbc.application.entity;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ExchangeRateApiResponse {
	
	private String base;
	private Map<String,String> rates;
	private String date;
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Map<String, String> getRates() {
		return rates;
	}
	public void setRates(Map<String, String> rates) {
		this.rates = rates;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
