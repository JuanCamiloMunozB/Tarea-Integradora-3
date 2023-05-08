package model;

import java.util.Calendar;

public abstract class User {

	private String name;
	private String cc;
	private Calendar vinculationDate;

	/**
	 * 
	 * @param name
	 * @param cc
	 * @param vinculationDate
	 */
	public User(String name, String cc, Calendar vinculationDate) {
		// TODO - implement User.User
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return this.name;
	}

	public String getCC() {
		return this.cc;
	}

	public Calendar getVinculationDate() {
		return this.vinculationDate;
	}

	public BibliographicProduct[] getBibliograpicProducts() {
		// TODO - implement User.getBibliograpicProducts
		throw new UnsupportedOperationException();
	}

	public Transaction[] getTransactions() {
		// TODO - implement User.getTransactions
		throw new UnsupportedOperationException();
	}

	public void setBibliographicProduct() {
		// TODO - implement User.setBibliographicProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param product
	 */
	public boolean addBibliographicProduct(BibliographicProduct product) {
		// TODO - implement User.addBibliographicProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param transaction
	 */
	public boolean addTransaction(Transaction transaction) {
		// TODO - implement User.addTransaction
		throw new UnsupportedOperationException();
	}

}