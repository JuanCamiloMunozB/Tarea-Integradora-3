package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
	The abstract class representing a bibliographic product.
	It provides common attributes and methods for books and magazines.
*/
public abstract class BibliographicProduct {

	/**
	 * The unique identifier of the product
	 */
	private String id;

	/**
	 * The name of the product
	 */
	private String name;

	/**
	 * The number of pages in the product
	 */
	private int numPages;

	/**
	 * The publication date of the product
	 */
	private Calendar publicationDate;

	/**
	 * The URL of the product's cover image
	 */
	private String url;

	/**
	 * The price of the product
	 */
	private Double price;

	/**
	 * The number of copies sold
	 */
	private int soldCopies;

	/**
	 * he number of pages read
	 */
	private int readPages;

	/**
	 * The date formatter for formatting publication dates
	 */
	private DateFormat formatter;

	/**
	 * Constructs a BibliographicProduct object with the specified parameters. 
	 * @param id The unique identifier of the product
	 * @param name The name of the product
	 * @param numPages The number of pages in the product
	 * @param publicationDate The publication date of the product
	 * @param url The URL of the product's cover image
	 * @param price The price of the product
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

	/**
	 * Returns the unique identifier of the product.
	 * @return The unique identifier of the product
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * Returns the name of the product.
	 * @return The name of the product
	*/
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the number of pages in the product.
	 * @return The number of pages in the product
	 */
	public int getNumPages() {
		return this.numPages;
	}

	/**
	 * Returns the publication date of the product.
	 * @return The publication date of the product
	*/
	public Calendar getPublicationDate() {
		return this.publicationDate;
	}

	/**
	 * Returns the formatted publication date of the product.
	 * @return The formatted publication date of the product
	 */
	public String getPublicationDateFormated() {
		return formatter.format(this.publicationDate.getTime());
	}

	/**
	 * Returns the URL of the product's cover image.
	 * @return The URL of the product's cover image
	 */
	public String getURL(){
		return this.url;
	}

	/**
	 * Returns the price of the product.
	 * @return The price of the product */
	public Double getPrice() {
		return this.price;
	}

	/**
	 * Returns the number of copies sold.
	 * @return The number of copies sold
	 */
	public int getSoldCopies() {
		return this.soldCopies;
	}

	/**
	 * Returns the number of pages read.
	 * @return The number of pages read
	 */
	public int getReadPages() {
		return this.readPages;
	}

	/**
	 * Sets the name of the product.
	 * @param name The name of the product
	 */
	public void setName(String name) {
		this.name = name;		
	}

	/**
	 * Sets the number of pages in the product.
	 * @param numPages The number of pages in the product
	 */
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	/**
	 * Sets the publication date of the product.
	 * @param publicationDate The publication date of the product
	 */
	public void setPublishedDate(Calendar publicationDate){
		this.publicationDate = publicationDate;
	}

	/**
	 * Sets the URL of the product's cover image.
	 * @param url The URL of the product's cover image
	 */
	public void setURL(String url) {
		this.url = url;				
	}

	/**
	 * Sets the price of the product.
	 * @param price The price of the product
	 */
	public void setPrice(Double price) {
		this.price = price;
		
	}

	/**
	 * Sets the number of pages read.
	 * @param readPages The number of pages read
	 */
	public void setReadPages(int readPages){
		this.readPages = readPages;
	}

	/**
	 * Sets the number of copies sold.
	 * @param soldCopies The number of copies sold
	 */
	public void setSoldCopies(int soldCopies){
		this.soldCopies = soldCopies;
	}
}