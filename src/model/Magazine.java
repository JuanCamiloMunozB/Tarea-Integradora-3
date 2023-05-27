package model;

import java.util.Calendar;

/**
 * The Magazine class represents a magazine, which is a type of bibliographic product.
 * It extends the BibliographicProduct class and adds additional properties specific to magazines.
 */
public class Magazine extends BibliographicProduct {

	/**
	 * The periodicity of the magazine's emission
	 */
	private String periodicityEmission;
	
	/**
	 * The category of the magazine
	 */
	private Category category;

	/**
	 * Constructs a new Magazine object with the specified parameters.
	 * @param id The unique identifier of the magazine
	 * @param name The name of the magazine
	 * @param numPages The number of pages in the magazine
	 * @param publicationDate The publication date of the magazine
	 * @param category The category of the magazine
	 * @param url The URL to the repository with the magazine cover
	 * @param price The price of the magazine
	 * @param periodicityEmission The periodicity of emission of the magazine
	 */
	public Magazine(String id, String name, int numPages, Calendar publicationDate, Category category, String url, Double price, String periodicityEmission) {
		super(id, name, numPages, publicationDate, url, price);
		this.periodicityEmission = periodicityEmission;
	}

	/**
	 * Returns the periodicity of emission of the magazine.
	 * @return The periodicity of emission
	 */
	public String getPeriodicityEmission() {
		return this.periodicityEmission;
	}

	/**
	 * Returns the category of the magazine.
	 * @return The category of the magazine
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * Returns the category of the magazine as a string.
	 * @return The category of the magazine as a string
	 */
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
	 * Sets the category of the magazine.
	 * @param category The category to be set
	 */
	public void setCategory(Category category) {
		this.category = category;		
	}

	/**
	 * Sets the periodicity of emission of the magazine.
	 * @param periodicityEmission The periodicity of emission to be set
	 */
	public void setPeriodicityEmission(String periodicityEmission) {
		this.periodicityEmission = periodicityEmission;
	}

}