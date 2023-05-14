package model;

import java.util.Calendar;

public class Magazine extends BibliographicProduct {

	private String periodicityEmission;
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
	public Magazine(String id, String name, int numPages, Calendar publicationDate, Category category, String url, Double price, String periodicityEmission) {
		super(id, name, numPages, publicationDate, url, price);
		this.periodicityEmission = periodicityEmission;
	}

	public String getPeriodicityEmission() {
		return this.periodicityEmission;
	}

	public Category getCategory() {
		return this.category;
	}

	public String getCategoryString(){
		String category = "";
		
		if(this.category == Category.VARIETIES){
			category = "Science Fiction ";
		}else if(this.category == Category.DESIGN){
			category = "Fantasy ";
		}else if(this.category == Category.SCIENTIFIC){
			category = "Historical Novel";
		}

		return category;
	}

	/**
	 * 
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;		
	}

	public void setPeriodicityEmission(String periodicityEmission) {
		this.periodicityEmission = periodicityEmission;
	}

}