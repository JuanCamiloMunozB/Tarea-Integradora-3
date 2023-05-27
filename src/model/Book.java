package model;

import java.util.Calendar;

public class Book extends BibliographicProduct{

	private String review;
	private Genre genre;

	/**
	 * 
	 * @param id
	 * @param name
	 * @param numPages
	 * @param review
	 * @param publicationDate
	 * @param genre
	 * @param url
	 * @param price
	 */
	public Book(String id, String name, int numPages, String review, Calendar publicationDate, Genre genre, String url, Double price) {
		super(id, name, numPages, publicationDate, url, price);
		this.review = review;
		this.genre = genre;
	}

	public String getReview() {
		return this.review;
	}

	public Genre getGenre() {
		return this.genre;
	}

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
	
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * 
	 * @param genre
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
}