package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ReadXSystems {

	private List<BibliographicProduct> products;
	private List<User> users;
	private List<String> productsIdentifiers;

	public ReadXSystems() {
		products = new ArrayList<>();
		users = new ArrayList<>();
		productsIdentifiers = new ArrayList<>();
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
		boolean isRegistered = false;

		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		String hexChars = "0123456789ABCDEF";

		do{
			for (int i = 0; i < 3; i++) {
				int index = random.nextInt(hexChars.length());
				char character = hexChars.charAt(index);
				sb.append(character);
			}
			identifier = sb.toString();

			for(int i = 0; i<productsIdentifiers.size() && isRegistered == false; i++){
				if(productsIdentifiers.get(i) == identifier){
					isRegistered = true;
				}
			}
			
		}while(isRegistered == true);

		productsIdentifiers.add(hexChars);

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
	public String addMagazine(String name, int numPages, Calendar publicationDate, int categoryOption, String url, Double price, String periodicityEmission) {
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
		boolean isRegistered = false;

		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		String alphaChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Define the characters that can be used
		
		do{
			for (int i = 0; i < 3; i++) {
				int index = random.nextInt(alphaChars.length());
				sb.append(alphaChars.charAt(index)); // Append a random character from the list of possible characters
			}

			identifier = sb.toString();

			for(int i = 0; i<productsIdentifiers.size() && isRegistered == false; i++){
				if(productsIdentifiers.get(i) == identifier){
					isRegistered = true;
				}
			}
		}while(isRegistered == true);

		productsIdentifiers.add(identifier);

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
	public String modifyMagazineInfo(String magazineID, String name, int numPages, Calendar publicationDate, int categoryOption, String url, Double price, String periodicityEmission) {

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
		String message = "";
		int ownedBooks = 0;
		int ownedMagazines = 0;

		BibliographicProduct purchasedProduct =  searchBibliographicProductByID(productID);
		User purchaser = searchUserByCC(userCC);
		Double productPrice = purchasedProduct.getPrice();
		Transaction bill = new Transaction(getCurrentDate(), productPrice, purchasedProduct);


		if(purchaser instanceof Regular){	
			ownedBooks = ((Regular)purchaser).countBooks();
			ownedMagazines = ((Regular)purchaser).countMagazines();
		}

		if(purchaser instanceof Premium || ownedBooks<0 || ownedMagazines<2){
			purchaser.addBibliographicProduct(purchasedProduct);
			purchaser.addPagesRead(0);
			purchaser.addTransaction(bill);

			int soldCopies = purchasedProduct.getReadPages() + 1;
			purchasedProduct.setReadPages(soldCopies);
		}
		
		message = bill.getTransactionInfo();
		
		return message;
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
		User user = searchUserByCC(userCC);
		BibliographicProduct product = user.searchOwnedProductByID(productID);

		if(product != null){
			return true;
		}else{
			return false;
		}		
	}

	public String startReadingSession(int userPosition, int productPosition, String action){
		User user = users.get(userPosition);
		BibliographicProduct product = user.getOwnedProducts().get(productPosition);
		int startPages = user.getPagesRead().get(productPosition); // the page where the user left
		int maxNumPages = product.getNumPages(); // the total pages of the product
		int numReadPages = startPages; // number of pages read
		int countReadPages = startPages; // counter of the readPages 

		String message = "";

		if(action.equalsIgnoreCase("a")){
			if(countReadPages<maxNumPages){
				if(countReadPages==numReadPages){
					countReadPages++;
					numReadPages++;
				}else{
					countReadPages++;
				}
			}else{
				message += "\nthere is no next page to this one. \n";
			}
		}else if(action.equalsIgnoreCase("s")){
			if(countReadPages>0){
				countReadPages--;
			}else{
				message += "\nthere is no page before this one. \n";
			}
		}else if(action.equalsIgnoreCase("b")){
			message += "\nthe reading session has ended. \n";
		}else{
			message += "\nInvalid option. Please, try again.";
		}

		product.setReadPages(product.getReadPages()+numReadPages);
		user.getPagesRead().set(productPosition, countReadPages);

		message += 
		"*****************************************"+
		"\nReading Session in progress"+
		"\nReading: "+products.get(productPosition).getName()+
		"\nReading page "+countReadPages+" out of "+products.get(productPosition).getNumPages()+
		"\nSelect one option: \nA. Go to the next page \nS. Go to the previous page\nB. Exit\n: ";

		return message;
	}

	//Functional Requeriment 10: Automatically generate objects in the system for each type of user and bibliographic product.

	public String initModel(){
		String message = "";

		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(1996, 7, 1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2021, 5, 1);

		BibliographicProduct product1 = new Book(generateHexIdentifier(), "A Game of Thrones", 694, "join adventures across the seven kingdoms", calendar1, Genre.FANTASY, "AGOT.png", 19.99);
		BibliographicProduct product2 = new Magazine(generateAlphaIdentifier(), "Vogue", 40, calendar2,Category.VARIETIES, "LMV2021.jpg", 4.99,"Mensual");

		products.add(product1);
		products.add(product2);

		User user1 = new Regular("Juan ", "0123456789", getCurrentDate());
		User user2 = new Premium("Camilo", "9876543210", getCurrentDate());

		users.add(user1);
		users.add(user2);

		message = "Users and bibliographic products have been created successfully."+
		"\nproduct test 1: "+product1.getID()+
		"\nproduct test 2: "+product2.getID()+
		"\nuser test 1: "+user1.getCC()+
		"\nuser test 2: "+user2.getCC();
		

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

	public BibliographicProduct getProductByPositon(int position){
		return products.get(position);
	}

	public Book getProductCastedBook(int position){
		return (Book)products.get(position);
	}

	public Magazine getProductCastedMagazine(int position){
		return (Magazine)products.get(position);
	}

	public User getUserByPosition(int position){
		return users.get(position);
	}

}