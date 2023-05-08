package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ReadXSystems {

	private List<BibliographicProduct> products;

	public ReadXSystems() {
		products = new ArrayList<>();
	}

	//Functional Requeriment 0: Register Books
	
	/**
	 * 
	 * @param name
	 * @param numPages
	 * @param review
	 * @param publicationDate
	 * @param genreOption
	 * @param url
	 * @param price
	 */
	public String addBook(String name, int numPages, String review, Calendar publicationDate, int genreOption, String url, Double price) {
		Genre genre = null;
		switch(genreOption){
			case 1:
				genre = Genre.SCIENCE_FICTION;
			break;

			case 2:
				genre = Genre.FANTASY;
			break;

			case 3:
				genre = Genre.HISTORICAL_NOVEL;
			break;
		}

		String id = generateHexIdentifier();// Generate a hexadecimal identifier
		
		BibliographicProduct book = new Book(id, name, numPages, review, publicationDate, genre, url, price);
		products.add(book);
	
		String message = "the product has been successfully registered. Here is its identifier: "+id;

		return message;
	}

	public String generateHexIdentifier(){
		String identifier = "";

		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		String hexChars = "0123456789ABCDEF";

		for (int i = 0; i < 3; i++) {
			int index = random.nextInt(hexChars.length());
			char character = hexChars.charAt(index);
			sb.append(character);
		}

		identifier = sb.toString();

		return identifier;
	}

	//Functional Requeriment 1: Register Magazines

	/**
	 * 
	 * @param name
	 * @param numPages
	 * @param publicationDate
	 * @param categoryOption
	 * @param url
	 * @param price
	 * @param periodicityEmission
	 */
	public String addMagazine(String name, int numPages, Calendar publicationDate, int categoryOption, String url, Double price, int periodicityEmission) {
		Category category = null;

		switch(categoryOption){
			case 1:
				category = Category.VARIETIES;
			break;

			case 2:
				category = Category.DESIGN;
			break;

			case 3:
				category = Category.SCIENTIFIC;
			break;
		}
		
		String id = generateAlphaIdentifier(); // Generate an alphanumeric identifier

		BibliographicProduct magazine = new Magazine(id, name, numPages, publicationDate, category, url, price, periodicityEmission);
		products.add(magazine);

		String message = "the product has been successfully registered. Here is its identifier: "+id;;

		return message;
	}

	public String generateAlphaIdentifier(){
		String identifier = "";

		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		String alphaChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Define the characters that can be used
		
		for (int i = 0; i < 3; i++) {
			int index = random.nextInt(alphaChars.length());
			sb.append(alphaChars.charAt(index)); // Append a random character from the list of possible characters
		}

		identifier = sb.toString();

		return identifier;
	}

	//Functional Requeriment 2: Modify Books information

	/**
	 * 
	 * @param book
	 * @param name
	 * @param numPages
	 * @param review
	 * @param publicationDate
	 * @param genreOption
	 * @param url
	 * @param price
	 */
	public String modifyBookInfo(BibliographicProduct book, String name, int numPages, String review, Calendar publicationDate, int genreOption, String url, Double price) {
		Genre genre = null;
		switch(genreOption){
			case 1:
				genre = Genre.SCIENCE_FICTION;
			break;

			case 2:
				genre = Genre.FANTASY;
			break;

			case 3:
				genre = Genre.HISTORICAL_NOVEL;
			break;
		}

		book.setName(name);
		book.setNumPages(numPages);
		book.setPrice(price);
		book.setPublishedDate(publicationDate);
		book.setURL(url);
		((Book)book).setReview(review);
		((Book)book).setGenre(genre);

		String message = "The product information has been changed";

		return message;
	}

	//Functional Requeriment 3: Modify Magazines information

	/**
	 * 
	 * @param magazine
	 * @param name
	 * @param numPages
	 * @param publicationDate
	 * @param categoryOption
	 * @param url
	 * @param price
	 * @param periodicityEmission
	 */
	public String modifyMagazineInfo(BibliographicProduct magazine, String name, int numPages, Calendar publicationDate, int categoryOption, String url, Double price, int periodicityEmission) {
		Category category = null;
		switch(categoryOption){
			case 1:
				category = Category.VARIETIES;
			break;

			case 2:
				category = Category.DESIGN;
			break;

			case 3:
				category = Category.SCIENTIFIC;
			break;
		}

		magazine.setName(name);
		magazine.setNumPages(numPages);
		magazine.setPrice(price);
		magazine.setPublishedDate(publicationDate);
		magazine.setURL(url);
		((Magazine)magazine).setPeriodicityEmission(periodicityEmission);
		((Magazine)magazine).setCategory(category);

		String message = "The product information has been changed";

		return message;
	}

	//Functional Requeriment 4: Eliminate BibliographicProducts

	//Incomplete
	/**
	 * 
	 * @param bibliographicProduct
	 */
	public String eliminateBibliographicProductFromArray(BibliographicProduct bibliographicProduct) {
		String message = "The product has been deleated";

		products.remove(bibliographicProduct);

		return message;
	}

	//Functional Requeriment 5: Register regular and premimum users

	/**
	 * 
	 * @param userOption
	 * @param name
	 * @param cc
	 */
	public boolean addUser(int userOption, String name, String cc) {
		// TODO - implement ReadXSystems.addUser
		throw new UnsupportedOperationException();
	}

	//Functional Requeriment 6: Allow a user to purchase a book and subscribe to a magazine.

	/**
	 * 
	 * @param user
	 * @param product
	 */
	public boolean addBibliographicProductToUser(User user, BibliographicProduct product) {
		// TODO - implement ReadXSystems.addBibliographicProductToUser
		throw new UnsupportedOperationException();
	}

	//Functional Requeriment 7: Allow a user to unsubscribe from a magazine.

	/**
	 * 
	 * @param user
	 * @param magazine
	 */
	public boolean elimanteMagazineFromUser(User user, BibliographicProduct magazine) {
		// TODO - implement ReadXSystems.elimanteMagazineFromUser
		throw new UnsupportedOperationException();
	}

	//Functional Requeriment 8: Present Users Library of Bibliographic Products

	/**
	 * 
	 * @param user
	 */
	public String showUsersLibrary(User user) {
		// TODO - implement ReadXSystems.showUsersLibrary
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usersLibrary
	 * @param page
	 * @param action
	 */
	public boolean navigateUsersLibrary(String usersLibrary, int page, char action) {
		// TODO - implement ReadXSystems.navigateUsersLibrary
		throw new UnsupportedOperationException();
	}

	//Functional Requeriment 9: Allow a user to simulate a reading session

	/**
	 * 
	 * @param bibliographicProduct
	 * @param action
	 */
	public String startReadingSession(BibliographicProduct bibliographicProduct, char action) {
		// TODO - implement ReadXSystems.startReadingSession
		throw new UnsupportedOperationException();
	}
	
	//Functional Requeriment 11: Generate reports with recorded data

	//Other functionalities
	/**
	 * 
	 * @param productID
	 */
	public BibliographicProduct searchBibliographicProductByID(String searchedProductID) {
		BibliographicProduct searchedProduct = null;
		
		for (BibliographicProduct product : products) {
			if (product.getID() == searchedProductID) {
				searchedProduct = product;
			}
		}

		return searchedProduct;
	}

	public int searchBibliographicProductArrayPosition(String searchedProductID){
		int productPosition = -1;
		boolean isFound = false;
		
		for(int i = 0; i < products.size() && !isFound ; i++){
			if (products.get(i).getID().equalsIgnoreCase(searchedProductID)){
				productPosition = i;
				isFound = true;
			}
		}

		return productPosition;
	}

	/**
	 * 
	 * @param userCC
	 */
	public User searchUserByCC(String userCC) {
		// TODO - implement ReadXSystems.searchUserByCC
		throw new UnsupportedOperationException();
	}
}