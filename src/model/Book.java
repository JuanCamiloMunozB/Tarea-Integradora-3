package model;

import java.util.Calendar;

/**
 * The Book class represents a book, which is a type of bibliographic product.
 * It extends the BibliographicProduct class and adds additional properties specific to books.
 */
public class Book extends BibliographicProduct{

	/**
	 * The short review of the product
	 */
	private String review;

	/**
	 * The genre of the product
	 */
	private Genre genre;

	/**
	 * 
	 * Constructs a new Book object with the specified parameters. 
	 * @param id The unique identifier of the book 
	 * @param name The name of the book 
	 * @param numPages The number of pages in the book
	 * @param review The short review of the book
	 * @param publicationDate The publication date of the book
	 * @param genre The genre of the book
	 * @param url The URL of the book's cover image
	 * @param price The price of the book
	 */
	public Book(String id, String name, int numPages, String review, Calendar publicationDate, Genre genre, String url, Double price) {
		super(id, name, numPages, publicationDate, url, price);
		this.review = review;
		this.genre = genre;
	}

	/**
	 * Returns the review of the book.
	 * @return The review of the book
	 */
	public String getReview() {
		return this.review;
	}

	/**
	 * Returns the genre of the book.
	 * @return The genre of the book
	 */
	public Genre getGenre() {
		return this.genre;
	}

	/**
	 * Returns the genre of the book as a string. 
	 * @return The genre of the book as a string
	 */
	public String getGenreString(){
		String genre = "";
		
		if(this.genre == Genre.SCIENCE_FICTION){
			genre = "Science Fiction ";
		}else if(this.genre == Genre.FANTASY){
			genre = "Fantasy ";
		}else if(this.genre == Genre.HISTORICAL_NOVEL){
			genre = "Historical Novel";
		}

		return genre;
	}
	
	/**
	 * Sets the review of the book.
	 * @param review The review of the book
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * Sets the genre of the book. 
	 * @param genre The genre of the book
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
}