package com.hsbc.application.entity;

import org.springframework.stereotype.Component;

@Component
public class ExchangeRatesResponse {
	
	private String date;
	private String currency;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "ExchangeRatesResponse [date=" + date + ", currency=" + currency + "]";
	}
	
}
