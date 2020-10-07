package com.hsbc.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeRates {
	
	@Id
	private String date;
	@Column
	private String gbp;
	@Column
	private String usd;
	@Column
	private String hkd;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGbp() {
		return gbp;
	}
	public void setGbp(String gbp) {
		this.gbp = gbp;
	}
	public String getUsd() {
		return usd;
	}
	public void setUsd(String usd) {
		this.usd = usd;
	}
	public String getHkd() {
		return hkd;
	}
	public void setHkd(String hkd) {
		this.hkd = hkd;
	}
	@Override
	public String toString() {
		return "ExchangeRates [date=" + date + ", gbp=" + gbp + ", usd=" + usd + ", hkd=" + hkd + "]";
	}
}
