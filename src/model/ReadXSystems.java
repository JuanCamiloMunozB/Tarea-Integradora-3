package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ReadXSystems {

	private List<BibliographicProduct> products;
	private List<User> users;

	public ReadXSystems() {
		products = new ArrayList<>();
		users = new ArrayList<>();
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
	public String modifyBookInfo(String bookID, String name, int numPages, String review, Calendar publicationDate, int genreOption, String url, Double price) {

		BibliographicProduct book = searchBibliographicProductByID(bookID);

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
	public String modifyMagazineInfo(String magazineID, String name, int numPages, Calendar publicationDate, int categoryOption, String url, Double price, int periodicityEmission) {

		BibliographicProduct magazine = searchBibliographicProductByID(magazineID);
		
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

	//Incomplete: check if is necessary to erase a bibliographic product from user
	/**
	 * 
	 * @param bibliographicProduct
	 */
	public boolean eliminateBibliographicProductFromArray(String productID) {
		BibliographicProduct product = searchBibliographicProductByID(productID);

		boolean isProductRemoved = products.remove(product);

		return isProductRemoved;

	}

	//Functional Requeriment 5: Register regular and premimum users

	/**
	 * 
	 * @param userOption
	 * @param name
	 * @param cc
	 */
	public String addRegularUser(String name, String cc) {
		String message = "The user has been registered successfully";

		Calendar vinculationDate = getCurrentDate();

		User regularUser = new Regular(name, cc, vinculationDate);
		users.add(regularUser);

		return message;
	}

	public String addPremiumUser(String name, String cc){
		String message = "The user has been registered successfully";

		Calendar vinculationDate = getCurrentDate();

		User premiumUser = new Premium(name, cc, vinculationDate);
		users.add(premiumUser);

		return message;
	}

	//Functional Requeriment 6: Allow a user to purchase a book and subscribe to a magazine.

	/**
	 * 
	 * @param user
	 * @param product
	 */
	public String addBibliographicProductToUser(String userCC, String productID) {
		BibliographicProduct purchasedProduct =  searchBibliographicProductByID(productID);
		User purchaser = searchUserByCC(userCC);
		Double productPrice = purchasedProduct.getPrice();
		Transaction bill = new Transaction(getCurrentDate(), productPrice, purchasedProduct);

		purchaser.addBibliographicProduct(purchasedProduct);
		purchaser.addTransaction(bill);
		
		return bill.getTransactionInfo();
	}

	//Functional Requeriment 7: Allow a user to unsubscribe from a magazine.

	/**
	 * 
	 * @param user
	 * @param magazine
	 */
	public boolean elimanteMagazineFromUser(String userCC, String magazineID) {
		BibliographicProduct searchedMagazine =  searchBibliographicProductByID(magazineID);
		User searchedUser = searchUserByCC(userCC);

		return searchedUser.removeProduct(searchedMagazine);
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
	public boolean innitReadingSession(String userCC, String productID) { //Incomplete
		String message = "";

		User user = searchUserByCC(userCC);
		BibliographicProduct product = user.searchOwnedProductByID(productID);

		if(product != null){
			message = "Reading: "+product.getName()+"\n";
		}else{
			message = "This bibliographic product does not belong to the user";
		}		
		
		return true;
	}

	//Incomplete
	public String startReadingSession(BibliographicProduct product, String action){

		String message = "";
		int numPages = product.getNumPages(); // maximun number of pages
		int numReadPages = 0; // number of pages read
		int countReadPages = numReadPages; // counter of the readPages

		switch(action){
			case "A":
				if(countReadPages<numPages){
					if(countReadPages==numReadPages){
						countReadPages++;
						numReadPages++;
					}else{
						countReadPages++;
					}
				}else{
					message += "there is no next page to this one. \n";
				}
			break;
			
			case "S":
				if(countReadPages>0){
					countReadPages--;
				}else{
					message += "there is no page before this one. \n";
				}
			break;

			case "B":
				message += "the reading session has ended. \n";
			break;
		}

		product.setReadPages(countReadPages);

		return message;
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
			if (product.getID().equalsIgnoreCase(searchedProductID)) {
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
	public User searchUserByCC(String searchedUserCC) {
		User searchedUser = null;
		
		for (User user : users) {
			if (user.getCC().equalsIgnoreCase(searchedUserCC)) {
				searchedUser = user;
			}
		}

		return searchedUser;
	}

	public int searchUserArrayPosition(String searchedUserCC){
		int userPosition = -1;
		boolean isFound = false;
		
		for(int i = 0; i < users.size() && !isFound ; i++){
			if (users.get(i).getCC().equalsIgnoreCase(searchedUserCC)){
				userPosition = i;
				isFound = true;
			}
		}

		return userPosition;

	}

	public Calendar getCurrentDate(){
		Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        currentDate.set(year, month, day);

		return currentDate;

	}

	public int checkProductType(String productID){
		int productsType = -1;

		BibliographicProduct product = searchBibliographicProductByID(productID);

		if(product instanceof Book){
			productsType = 0;
		}else if(product instanceof Magazine){
			productsType = 1;
		}

		return productsType;
	}

	public int checkUserType(String usersCC){
		int usersType = -1;

		User user = searchUserByCC(usersCC);

		if(user instanceof Regular){
			usersType = 0;
		}else if(user instanceof Premium){
			usersType = 1;
		}

		return usersType;
	}

}