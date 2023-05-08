package model;

import java.util.Calendar;

public class Magazine extends BibliographicProduct {

	private int periodicityEmission;
	private Category category;

	/**
	 * 
	 * @param id
	 * @param name
	 * @param numPages
	 * @param review
	 * @param publicationDate
	 * @param category
	 * @param url
	 * @param price
	 * @param periodicityEmission
	 */
	public Magazine(String id, String name, int numPages, Calendar publicationDate, Category category, String url, Double price, int periodicityEmission) {
		super(id, name, numPages, publicationDate, url, price);
		this.periodicityEmission = periodicityEmission;
	}

	public int getPeriodicityEmission() {
		return this.periodicityEmission;
	}

	public Category getCategory() {
		return this.category;
	}

	/**
	 * 
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;		
	}

	public void setPeriodicityEmission(int periodicityEmission) {
		this.periodicityEmission = periodicityEmission;
	}

}