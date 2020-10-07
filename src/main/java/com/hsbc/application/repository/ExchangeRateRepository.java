package com.hsbc.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hsbc.application.entity.ExchangeRates;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRates, String>  {
		@Query(value="SELECT er.date FROM ExchangeRates er")
		List<String> getLast12MonthsDate();
		
		@Query(value="FROM ExchangeRates er where er.date = :dateToSearch")
		ExchangeRates findRates(@Param("dateToSearch") String dateToSearch);
}
