package model;

import java.util.Calendar;

public class Transaction {

	private Calendar realizationDate;
	private Double paidAmount;

	/**
	 * 
	 * @param realizationDate
	 * @param paidAmount
	 */
	public Transaction(Calendar realizationDate, Double paidAmount) {
		this.realizationDate = realizationDate;
		this.paidAmount = paidAmount;
	}

	public Calendar getRealizationDate() {
		return this.realizationDate;
	}

	public Double getPaidAmount() {
		return this.paidAmount;
	}

}