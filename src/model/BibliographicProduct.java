package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class BibliographicProduct {

	private String id;
	private String name;
	private int numPages;
	private Calendar publicationDate;
	private String url;
	private Double price;

	private int soldCopies;
	private int readPages;

	private DateFormat formatter;

	/**
	 * 
	 * @param id
	 * @param name
	 * @param numPages
	 * @param publicationDate
	 * @param url
	 * @param price
	 */
	public BibliographicProduct(String id, String name, int numPages, Calendar publicationDate, String url, Double price) {
		this.id = id;
		this.name = name;
		this.numPages = numPages;
		this.publicationDate = publicationDate;
		this.url = url;
		this.price = price;

		this.formatter = new SimpleDateFormat("dd/M/yy");
		
		soldCopies = 0;
		readPages = 0;
	}

	public String getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getNumPages() {
		return this.numPages;
	}

	public Calendar getPublicationDate() {
		return this.publicationDate;
	}

	public String getPublicationDateFormated() {
		return formatter.format(this.publicationDate.getTime());
	}

	public String getURL(){
		return this.url;
	}

	public Double getPrice() {
		return this.price;
	}

	public int getSoldCopies() {
		return this.soldCopies;
	}

	public int getReadPages() {
		return this.readPages;
	}

	public void setName(String name) {
		this.name = name;		
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	public void setPublishedDate(Calendar publicationDate){
		this.publicationDate = publicationDate;
	}

	public void setURL(String url) {
		this.url = url;				
	}

	public void setPrice(Double price) {
		this.price = price;
		
	}

}