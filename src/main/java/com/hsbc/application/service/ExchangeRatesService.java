package com.hsbc.application.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hsbc.application.entity.ExchangeRateApiResponse;
import com.hsbc.application.entity.ExchangeRates;
import com.hsbc.application.entity.ExchangeRatesResponse;
import com.hsbc.application.repository.ExchangeRateRepository;

@Service
public class ExchangeRatesService {
	
	@Autowired
	ExchangeRateRepository exchangeRateRepository;
	
	

	public String insertExchangeRates() throws ParseException {
		
		RestTemplate restTemplate =new RestTemplate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(Calendar.getInstance().getTime());

		List<String> last12Monthsdates = new ArrayList<>();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFormat.parse(currentDate));
		cal.add(Calendar.MONTH, -1);
		
		for (int i = 1; i <= 12; i++) {
		    String month_name = dateFormat.format(cal.getTime());
		    last12Monthsdates.add(month_name);
		    cal.add(Calendar.MONTH, -1);
		}
		
		Iterator<String> dateIterator = last12Monthsdates.iterator();
	    while (dateIterator.hasNext()) {
	    	ExchangeRateApiResponse response=restTemplate.getForEntity("https://api.ratesapi.io/api/"+dateIterator.next()+"?symbols=USD,GBP,HKD" , ExchangeRateApiResponse.class).getBody();
			ExchangeRates exchangeRates=new ExchangeRates();
			exchangeRates.setDate(response.getDate());
			exchangeRates.setGbp(response.getRates().get("GBP"));
			exchangeRates.setUsd(response.getRates().get("USD"));
			exchangeRates.setHkd(response.getRates().get("HKD"));
			exchangeRateRepository.save(exchangeRates);
	        
	    } 
		return "SUCCESS"; 
	}



	public List<ExchangeRatesResponse> readRates(String userProvidedDate,String currencyRateToFind) throws ParseException {
		String currency = currencyRateToFind.toUpperCase();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar givenDate = Calendar.getInstance();
		givenDate.setTime(dateFormat.parse(userProvidedDate));
		
		List<String> last12MonthsDates = exchangeRateRepository.getLast12MonthsDate();
		Iterator<String> dateIterator = last12MonthsDates.iterator();
		
		List<String> datesToSearch = new ArrayList<String>();
		
		while (dateIterator.hasNext()) {
			String iteratorDate = dateIterator.next();
			Calendar dateToCompare = Calendar.getInstance();
			dateToCompare.setTime(dateFormat.parse(iteratorDate));
			
			if(dateToCompare.compareTo(givenDate) == 1)
				datesToSearch.add(iteratorDate);
		}
		
		List<ExchangeRates> exchangeRatesResponse = new ArrayList<ExchangeRates>();
		Iterator<String> datesToSearchIterator = last12MonthsDates.iterator();
		
		while(datesToSearchIterator.hasNext()) {
			exchangeRatesResponse.add(exchangeRateRepository.findRates(datesToSearchIterator.next()));
		}
		
		Iterator<ExchangeRates> currencyIterator = exchangeRatesResponse.iterator();
		List<ExchangeRatesResponse> exchangeRateResponseList = new ArrayList<ExchangeRatesResponse>();
		ExchangeRatesResponse exResponse = new ExchangeRatesResponse();
		
		if(currency.equals("GBP"))
		while(currencyIterator.hasNext()) {
			ExchangeRates exRates = currencyIterator.next();
			exResponse.setDate(exRates.getDate());
			exResponse.setCurrency(exRates.getGbp());
			exchangeRateResponseList.add(exResponse);
		}
		
		if(currency.equals("USD"))
		while(currencyIterator.hasNext()) {
			ExchangeRates exRates = currencyIterator.next();
			exResponse.setDate(exRates.getDate());
			exResponse.setCurrency(exRates.getUsd());
			exchangeRateResponseList.add(exResponse);
		}
		
		if(currency.equals("HKD"))
		while(currencyIterator.hasNext()) {
			ExchangeRates exRates = currencyIterator.next();
			exResponse.setDate(exRates.getDate());
			exResponse.setCurrency(exRates.getHkd());
			exchangeRateResponseList.add(exResponse);
		}
		
		return exchangeRateResponseList;
	}
	

}
