package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * The ReadXSystems class represents a system for managing bibliographic products and users.
 */
public class ReadXSystems {

	/**
	 * The number of rows for the library
	 */
	public static final int ROW = 5; 

	/**
	 * The number of columns for the library.
	 */
	public static final int COLUMN = 5;

	/**
	 * List for storing BibliographicProducts that users can buy.
	 */
	private List<BibliographicProduct> products;

	/**
	 * List for storing all registered users.
	 */
	private List<User> users;

	/**
	 * List for storing all products identifiers.
	 */
	private List<String> productsIdentifiers;

	/**
	 * Constructs a ReadXSystems object with an empty list of products and users.
	 */
	public ReadXSystems() {
		products = new ArrayList<>();
		users = new ArrayList<>();
		productsIdentifiers = new ArrayList<>();
	}

	//Functional Requeriment 0: Register Books
	
	/**
	 * Adds a book to the system with the specified details.
	 * @param name the name of the book
	 * @param numPages the number of pages in the book
	 * @param review the review of the book
	 * @param publicationDate the publication date of the book
	 * @param genreOption the genre option of the book
	 * @param url the URL of the book
	 * @param price the price of the book
	 * @return a message indicating the success of the operation and the identifier of the registered product
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

	/**
	 * Generates a hexadecimal identifier for the products.
	 * @return the generated hexadecimal identifier
	 */
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
	 * Adds a magazine to the system with the specified details.
	 * @param name the name of the magazine
	 * @param numPages the number of pages in the magazine
	 * @param publicationDate the publication date of the magazine
	 * @param categoryOption the category option of the magazine
	 * @param url the URL of the magazine
	 * @param price the price of the magazine
	 * @param periodicityEmission the periodicity emission of the magazine
	 * @return a message indicating the success of the operation and the identifier of the registered product
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

		String message = "the product has been successfully registered. Here is its identifier: "+id;

		return message;
	}

	/**
	 * Generates an alphanumeric identifier for the products.
	 * @return the generated alphanumeric identifier
	 */
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
	 * Modifies the information of a book in the system.
	 * @param bookPosition the position of the book to modify on the list
	 * @param name the new name of the book
	 * @param numPages the new number of pages in the book
	 * @param review the new review of the book
	 * @param publicationDate the new publication date of the book
	 * @param genreOption the new genre option of the book
	 * @param url the new URL of the book
	 * @param price the new price of the book
	 * @return a message indicating the success of the operation
	 */
	public String modifyBookInfo(int bookPosition, String name, int numPages, String review, Calendar publicationDate, int genreOption, String url, Double price) {

		BibliographicProduct book = products.get(bookPosition);

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
	 * Modifies the information of a magazine in the system.
	 * @param magazinePosition the position of the magazine to modify on the list
	 * @param name the new name of the magazine
	 * @param numPages the new number of pages in the magazine
	 * @param publicationDate the new publication date of the magazine
	 * @param categoryOption the new category option of the magazine
	 * @param url the new URL of the magazine
	 * @param price the new price of the magazine
	 * @param periodicityEmission the new periodicity emission of the magazine.
	 * @return a message indicating the success of the operation
	 */
	public String modifyMagazineInfo(int magazinePosition, String name, int numPages, Calendar publicationDate, int categoryOption, String url, Double price, String periodicityEmission) {

		BibliographicProduct magazine = products.get(magazinePosition);
		
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

	/**
	 * Removes a bibliographic product from the system.
	 * @param productID the ID of the product to be removed
	 * @return true if the product was successfully removed, false otherwise
	 */
	public boolean eliminateBibliographicProductFromSystem(String productID) {
		BibliographicProduct searchedProduct = searchBibliographicProductByID(productID);
		boolean isProductRemoved = false;

		if(searchedProduct != null){
			isProductRemoved = products.remove(searchedProduct);

			for(int i = 0; i<users.size(); i++){
				Transaction searchedTransaction = users.get(i).searchTransactionByProduct(searchedProduct);

				if(searchedProduct instanceof Book){
					((IBuy)users.get(i)).eliminateBook((Book)searchedProduct, searchedTransaction);
				}else if(searchedProduct instanceof Magazine){
					((IBuy)users.get(i)).cancelMagazineSubscription((Magazine)searchedProduct, searchedTransaction);
				}
			}
		}
		
		return isProductRemoved;
	}

	//Functional Requeriment 5: Register regular and premimum users

	/**
	 * Adds a regular user to the system.
	 * @param name the name of the user
	 * @param cc the identification number of the user
	 * @return a message indicating the successful registration of the user
	 */
	public String addRegularUser(String name, String cc) {
		String message = "The user has been registered successfully";

		Calendar vinculationDate = getCurrentDate();

		User regularUser = new Regular(name, cc, vinculationDate);
		users.add(regularUser);

		return message;
	}

	/**
	 * Adds a premium user to the system.
	 * @param name the name of the user
	 * @param cc the identification number of the user
	 * @return a message indicating the successful registration of the user
	 */
	public String addPremiumUser(String name, String cc){
		String message = "The user has been registered successfully";

		Calendar vinculationDate = getCurrentDate();

		User premiumUser = new Premium(name, cc, vinculationDate);
		users.add(premiumUser);

		return message;
	}

	/**
	 * Checks if a given credit card number (cc) is already registered by any user.
	 * @param cc the credit card number to check.
	 * @return true if the credit card number is already registered, false otherwise.
	 */
	public boolean isCCAlreadyRegistered(String cc){
		boolean isFound = false;

		for(int i = 0; i<users.size(); i++){
			if(users.get(i).getCC().equals(cc)){
				isFound = true;
			}
		}

		return isFound;
	}

	//Functional Requeriment 6: Allow a user to purchase a book and subscribe to a magazine.

	/**
	 * Adds a bibliographic product to a user's account and completes the payment.
	 * @param userCC the identification number of the user
	 * @param productID the ID of the bibliographic product to be purchased
	 * @param paidAmount the amount paid for the product
	 * @return a message indicating the result of the transaction
	 */
	public String addBibliographicProductToUser(String userCC, String productID, Double paidAmount) {
		String message = "you do not have enough money to complete the payment";
		BibliographicProduct purchasedProduct =  searchBibliographicProductByID(productID);
		User purchaser = searchUserByCC(userCC);

		Double change = purchasedProduct.getPrice()-paidAmount;
		
		if(change <= 0){
			Transaction bill = new Transaction(getCurrentDate(), paidAmount, purchasedProduct);
			boolean isPurchased = false;

			if(purchasedProduct instanceof Book){
				isPurchased = ((IBuy)purchaser).purchaseBook((Book)purchasedProduct, bill);
			}else{
				isPurchased = ((IBuy)purchaser).suscribeMagazine((Magazine)purchasedProduct, bill);
			}

			if(isPurchased){
				purchaser.addPagesRead(0);
				purchaser.addTransaction(bill);

				message = "The transaction has been successfully completed. \n...\n"+bill.getTransactionInfo();
			}else{
				message = "payment could not be completed";
			}
		}
		return message;
	}

	//Functional Requeriment 7: Allow a user to cancel magazine subscription.

	/**
	 * Eliminates a magazine subscription from a user's account.
	 * @param userCC the identification number of the user
	 * @param magazineID the ID of the magazine to be eliminated
	 * @return a message indicating the result of the operation
	 */
	public String eliminateMagazineFromUser(String userCC, String magazineID) {
		String message = "This user isn't subscribed to the magazine";

		BibliographicProduct searchedMagazine =  searchBibliographicProductByID(magazineID);
		User searchedUser = searchUserByCC(userCC);
		Transaction searchedTransaction = searchedUser.searchTransactionByProduct(searchedMagazine);

		if(((IBuy)searchedUser).cancelMagazineSubscription((Magazine)searchedMagazine, searchedTransaction)){
			message = "The suscription to the magazine was canceled";
		}

		return message;
	}

	//Functional Requeriment 8: Present Users Library of Bibliographic Products

	/**
	 * Displays the user's library, showing the content of a specific shelf.
	 * @param userPosition the searched user position.
	 * @param shelve the shelf number to display
	 * @return a message containing the content of the specified shelf in the user's library
	 */
	public String showUsersLibrary(int userPosition, int shelve) {
		String message = "";
		User user = users.get(userPosition);
		List<BibliographicProduct[][]> library = initLibrary(user);
		
		if (shelve >= 0 && shelve < library.size()) {
			BibliographicProduct[][] libraryShelve = library.get(shelve);

			message = "Shelve "+ (shelve + 1) +" out of "+library.size()+"\n";

			message += "  |";
			for (int j = 0; j < COLUMN; j++) {
				message += "  " + j + "  |";
			}
			message += "\n";	

			for (int i = 0; i < ROW; i++) {
				message += i + " | ";
				for (int j = 0; j < COLUMN; j++) {
					if (libraryShelve[i][j] != null) {
						message += libraryShelve[i][j].getID() + " | ";
					} else {
						message += "___ | ";
					}
				}
				message += "\n";
			}
		} else {
			message = "Invalid shelve number";
		}
		
		return message;
	}
	
	/**
	 * Initializes the library for a user by organizing their owned products by the publication date into shelves.
	 * @param user the user whose library is being initialized
	 * @return a list of shelves containing the user's owned products
	 */
	public List<BibliographicProduct[][]> initLibrary(User user) {
		List<BibliographicProduct[][]> library = new ArrayList<>();
		int index = 0;

		List<BibliographicProduct> temp = new ArrayList<>(user.getOwnedProducts());
		List<BibliographicProduct> productsByPublicationDate = new ArrayList<>();

		while (!temp.isEmpty()) {
			int minPublicationDateIndex = 0;
	
			for (int j = 1; j < temp.size(); j++) {
				if (temp.get(j).getPublicationDate().compareTo(temp.get(minPublicationDateIndex).getPublicationDate()) < 0) {
					minPublicationDateIndex = j;
				}
			}
	
			productsByPublicationDate.add(temp.get(minPublicationDateIndex));
			temp.remove(minPublicationDateIndex);
		}

		while (index < productsByPublicationDate.size()) {
			BibliographicProduct[][] shelve = new BibliographicProduct[ROW][COLUMN];
			for (int i = 0; i < ROW; i++) {
				for (int j = 0; j < COLUMN; j++) {
					if (index < productsByPublicationDate.size()) {
						shelve[i][j] = productsByPublicationDate.get(index);
						index++;
					} else {
						break;
					}
				}
			}
			library.add(shelve);
		}
	
		return library;
	}


	//Functional Requeriment 9: Allow a user to simulate a reading session
	
	/**
	 * Retrieves the position of a product in a user's owned products list based on its coordinates in the library.
	 * @param x the x-coordinate of the product in the library
	 * @param y the y-coordinate of the product in the library
	 * @param shelve the index of the shelve containing the product
	 * @param userPosition the position of the user in the users list
	 * @return the position of the searched product in the user's owned products list
	 */
	public int getProductPositionByCordinates(int x, int y, int shelve, int userPosition){
		User user = users.get(userPosition);
		List<BibliographicProduct[][]> library = initLibrary(user);
		BibliographicProduct searchedProduct = library.get(shelve)[x][y];
		return user.getOwnedProducts().indexOf(searchedProduct);
	}

	/**
	 * Displays the information about a reading session for a specific user and product.
	 * @param userPosition the position of the user in the users list
	 * @param productPosition the position of the product in the user's owned products list
	 * @return a string containing the details of the reading session and options to navigate through the pages
	 */
	public String showReadingSession(int userPosition, int productPosition){
		User user = users.get(userPosition);

		return "<<------------------------------------>>"+
		"\nReading Session in progress"+
		"\nReading: "+user.getOwnedProducts().get(productPosition).getName()+
		"\nReading page "+user.getPagesRead().get(productPosition)+" out of "+user.getOwnedProducts().get(productPosition).getNumPages()+
		"\nA. Go to the next page \nS. Go to the previous page\nB. Exit\n"+
		"<<------------------------------------>>"+
		"\nSelect one option: ";
	}

	/**
	 * Changes the page of a reading session based on the user's action.
	 * @param userPosition the position of the user in the users list
	 * @param productPosition the position of the product in the user's owned products list
	 * @param action the action performed by the user ('A' for next page, 'S' for previous page, 'B' for exit)
	 * @return a string containing the message and any additional information related to the page change
	 */
	public String changePage(int userPosition, int productPosition, String action){
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
			message += "\nInvalid option. Please, try again.\n";
		}

		if (product instanceof Book && countReadPages % 20 == 0) {
			message += getAdvertisement(user);
		}else if (product instanceof Magazine && countReadPages % 5 == 0) {
			message += getAdvertisement(user);
		}
		

		product.setReadPages(product.getReadPages()+numReadPages);
		user.getPagesRead().set(productPosition, countReadPages);

		return message;
	}

	//Functional Requeriment 10: Automatically generate objects in the system for each type of user and bibliographic product.

	/**
	 * Initializes the model by generating random products and users, and assigning purchases to users.
	 * @return a string containing information about the generated products, users, and their acquisitions
	 */
	public String initModel(){
		Random random = new Random();

		int pInitialSize = products.size();
		int uInitialSize = users.size();
		int numberOfProducts = 30;
		int numberOfUsers = 10;

		for(int i = 0; i<numberOfProducts; i++){
			int randomNumber = random.nextInt(3) + 1;
			Genre genre = null;

			switch(randomNumber){
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

			int pNumber = products.size()+1;

			BibliographicProduct book = new Book(generateHexIdentifier(), "book"+pNumber, i+50, "test book #"+pNumber, getCurrentDate(), genre, "book"+pNumber+".jpg", 15.0);
			products.add(book);
		}

		for(int i = 0; i<numberOfProducts; i++){
			int randomNumber = random.nextInt(3) + 1;
			Category category = null;

			switch(randomNumber){
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

			int pNumber = products.size()+1;

			Magazine magazine = new Magazine(generateAlphaIdentifier(), "magazine"+pNumber, i+20, getCurrentDate(), category, "magazine"+pNumber, 15.0, "Every month");
			products.add(magazine);
		}

		for(int i = 0; i<numberOfUsers; i++){
			int uNumber = users.size()+1;
			User regularUser = new Regular("user"+uNumber, "regular"+uNumber, getCurrentDate());
			users.add(regularUser);
		}

		for(int i = 0; i<numberOfUsers; i++){
			int uNumber = users.size()+1;
			User premiumUser = new Premium("user"+uNumber, "premium"+uNumber, getCurrentDate());
			users.add(premiumUser);
		}

		int regularUserPos = uInitialSize + random.nextInt(numberOfUsers);
		int premiumUserPos = uInitialSize + numberOfUsers + random.nextInt(numberOfUsers);


		int booksToAdd = 5;
		int magazinesToAdd = 2;

		for (int i = pInitialSize; i < products.size(); i++) {
			Transaction bill = new Transaction(getCurrentDate(), products.get(i).getPrice(), products.get(i));

    		if (products.get(i) instanceof Book && booksToAdd > 0) {
				((Regular)users.get(regularUserPos)).purchaseBook((Book) products.get(i), bill);
				booksToAdd--;

			} else if (products.get(i) instanceof Magazine && magazinesToAdd > 0) {
				((Regular)users.get(regularUserPos)).suscribeMagazine((Magazine) products.get(i), bill);
				magazinesToAdd--;
				
			}
		}
		
		for (int i = pInitialSize; i < products.size(); i++) {
			Transaction bill = new Transaction(getCurrentDate(), products.get(i).getPrice(), products.get(i));
			if (products.get(i) instanceof Book) {
				((Premium)users.get(premiumUserPos)).purchaseBook((Book) products.get(i), bill);

			} else if (products.get(i) instanceof Magazine) {
				((Premium)users.get(premiumUserPos)).suscribeMagazine((Magazine) products.get(i), bill);
			}
		}
		
		String message = "\n<<-PRODUCTS ID-------------------------------------------------------------------------->>\n"+
		"Number of products generated: "+numberOfProducts*2+"\n";

		for (int i = pInitialSize; i<products.size(); i++) {
			message += " | "+products.get(i).getID()+" | ";
		}

		message += "\n<<-USERS-------------------------------------------------------------------------------->>\n"+
		"Number of users generated: "+numberOfUsers*2+"\n";

		for (int i = uInitialSize; i<users.size(); i++) {
			message += " | "+ users.get(i).getCC()+" |";
		}		

		message += "\n<<-------------------------------------------------------------------------------------->>\n"+
		"Random premium user that acquiered every product generated before: "+users.get(premiumUserPos).getCC()+
		"\n<<-------------------------------------------------------------------------------------->>\n"+
		"Random regular user that acquiered the first 5 generated books and the 2 first magazines: "+users.get(regularUserPos).getCC()+"\n"+
		"-Acquired Books: \n";

		for(int i = 0; i < users.get(regularUserPos).getOwnedProducts().size(); i++){
			if(users.get(regularUserPos).getOwnedProducts().get(i) instanceof Book){
				message += " | "+users.get(regularUserPos).getOwnedProducts().get(i).getID()+" | ";
			}
		}

		message += "\n-Acquired Magazines: \n";

		for(int i = 0; i < users.get(regularUserPos).getOwnedProducts().size(); i++){
			if(users.get(regularUserPos).getOwnedProducts().get(i) instanceof Magazine){
				message += " | "+users.get(regularUserPos).getOwnedProducts().get(i).getID()+" | ";
			}
		}

		message +="\n<<-------------------------------------------------------------------------------------->>\n";

		return message;
	}

	//Functional Requeriment 11: Generate reports with recorded data

	/**
	 * Retrieves the total number of pages read for each type of product (books and magazines).
	 * @return a string containing the total number of pages read for books and magazines
	 */
	public String getTotalPagesReadByType(){
		String message = "";
		int booksReadPages = 0;
		int magReadPages = 0;

		for(int i = 0; i<products.size(); i++){
			if(products.get(i) instanceof Book){
				booksReadPages +=products.get(i).getReadPages();
			}
		}

		for(int i = 0; i<products.size(); i++){
			if(products.get(i) instanceof Magazine){
				magReadPages +=products.get(i).getReadPages();
			}
		}

		message = " -Books total read pages: "+booksReadPages+"\n -Magazines total read pages: "+magReadPages;

		return message;
	}

	/**
	 * Retrieves the most read genre and category based on the total number of pages read.
	 * @return a string containing the most read genre and category with their respective total pages read
	 */
	public String getMostReadGenreAndCategory(){
		String message = "";

		int contScienceFiction = 0;
		int contFantasy = 0;
		int contHistoricalNovel = 0;

		int contVarieties = 0;
		int contDesign = 0;
		int contScientific = 0;
		
		for(int i = 0; i<products.size(); i++){
			if(products.get(i) instanceof Book){
				if(((Book)products.get(i)).getGenre() == Genre.SCIENCE_FICTION){
					contScienceFiction += products.get(i).getReadPages();

				}else if(((Book)products.get(i)).getGenre() == Genre.FANTASY){
					contFantasy += products.get(i).getReadPages();

				}else{
					contHistoricalNovel += products.get(i).getReadPages();
				}
			}else{
				if(((Magazine)products.get(i)).getCategory() == Category.VARIETIES){
					contScienceFiction += products.get(i).getReadPages();

				}else if(((Magazine)products.get(i)).getCategory() == Category.SCIENTIFIC){
					contFantasy += products.get(i).getReadPages();

				}else{
					contHistoricalNovel += products.get(i).getReadPages();
				}
			}
		}

		if(contScienceFiction>=contFantasy && contScienceFiction>=contHistoricalNovel){
			message = " -The most read genre is ScienceFiction with: "+ contScienceFiction+ " pages read\n";
		}else if(contFantasy>=contScienceFiction && contFantasy>=contHistoricalNovel){
			message = " -The most read genre is Fantasy with: "+ contFantasy+ " pages read\n";
		}else{
			message = " -The most read genre is Historical Novel with: "+ contHistoricalNovel+ " pages read\n";
		}

		if(contVarieties>=contDesign && contVarieties>=contScientific){
			message += " -The most read category is Varieties with: "+ contVarieties+ " pages read";
		}else if(contDesign>=contVarieties && contDesign>=contScientific){
			message += " -The most read category is Design with: "+ contDesign+ " pages read";
		}else{
			message += " -The most read category is Scientific with: "+ contScientific+ " pages read";
		}

		return message;
	}

	/**
	 * Retrieves a list of the top 5 most read books.
	 * @return a string containing the information of the top 5 books, including name, genre, and read pages.
	 */
	public String getTop5BookList(){
		String message = "";
		List<BibliographicProduct> temp = products;
		List<BibliographicProduct> top5BookList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			int maxReadBookIndex = 0;
	
			for (int j = 1; j < temp.size(); j++) {
				if (temp.get(j).getReadPages() > temp.get(maxReadBookIndex).getReadPages() && temp.get(i) instanceof Book) {
					maxReadBookIndex = j;
				}
			}
	
			top5BookList.add(temp.remove(maxReadBookIndex));
		}

		for(int i = 0; i<top5BookList.size(); i++){
			message = i+". Name: "+top5BookList.get(i).getName()+"\n "+
			" -Genre: "+((Book)top5BookList.get(i)).getGenreString()+"\n "+
			" -Read pages: "+top5BookList.get(i).getReadPages()+"\n ";
		}

		return message;
	}

	/**
	 * Retrieves a list of the top 5 most read magazines.
	 * @return a string containing the information of the top 5 magazines, including name, category, and read pages.
	 */
	public String getTop5MagazinesList(){
		String message = "";
		List<BibliographicProduct> temp = products;
		List<BibliographicProduct> top5MagazineList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			int maxReadMagazineIndex = 0;
	
			for (int j = 1; j < temp.size(); j++) {
				if (temp.get(j).getReadPages() > temp.get(maxReadMagazineIndex).getReadPages() && temp.get(i) instanceof Book) {
					maxReadMagazineIndex = j;
				}
			}
			top5MagazineList.add(temp.remove(maxReadMagazineIndex));
		}

		for(int i = 0; i<5; i++){
			message = i+". Name: "+top5MagazineList.get(i).getName()+"\n "+
			" -Category: "+((Magazine)top5MagazineList.get(i)).getCategoryString()+"\n "+
			" -Read pages: "+top5MagazineList.get(i).getReadPages()+"\n ";
		}

		return message;
	}

	/**
	 * Retrieves sales and revenue information by genre.
	 * @return a string containing the sales and revenue information for each genre.
	 */
	public String informSalesByGenre(){
		String message = "";
		int scienceFictionSales = 0;
		Double scienceFictionRevenue = 0.0;

		int fantasySales = 0;
		Double fantasyRevenue = 0.0;

		int historicalNovelSales = 0;
		Double historicalNovelRevenue = 0.0;

		for(int i = 0; i<products.size(); i++){
			if(products.get(i) instanceof Book){
				if(((Book)products.get(i)).getGenre() == Genre.SCIENCE_FICTION){
					scienceFictionSales++;
					scienceFictionRevenue += products.get(i).getPrice()*products.get(i).getSoldCopies();

				}else if(((Book)products.get(i)).getGenre() == Genre.FANTASY){
					fantasySales++;
					fantasyRevenue += products.get(i).getPrice()*products.get(i).getSoldCopies();
				}else{
					historicalNovelSales++;
					historicalNovelRevenue += products.get(i).getPrice()*products.get(i).getSoldCopies();
				}
			}
		}

		message = "Genres: \n"+
		" Science Fiction: \n -Sales: "+scienceFictionSales+"\n -Revenue: "+scienceFictionRevenue+"\n"+
		" Fantasy: \n -Sales: "+fantasySales+"\n -Revenue: "+fantasyRevenue+"\n"+
		" Historical Novel: \n -Sales: "+historicalNovelSales+"\n -Revenue: "+historicalNovelRevenue;

		return message;
	}

	/**
	 * Retrieves sales and revenue information by category.
	 * @return a string containing the sales and revenue information for each category.
	 */
	public String informSalesByCategory(){
		String message = "";
		int varietiesSales = 0;
		Double varietiesRevenue = 0.0;

		int designSales = 0;
		Double designRevenue = 0.0;

		int ScientificSales = 0;
		Double ScientificRevenue = 0.0;

		for(int i = 0; i<products.size(); i++){
			if(products.get(i) instanceof Magazine){
				if(((Magazine)products.get(i)).getCategory() == Category.VARIETIES){
					varietiesSales++;
					varietiesRevenue += products.get(i).getPrice()*products.get(i).getSoldCopies();

				}else if(((Magazine)products.get(i)).getCategory() == Category.DESIGN){
					designSales++;
					designRevenue += products.get(i).getPrice()*products.get(i).getSoldCopies();
				}else{
					ScientificSales++;
					ScientificRevenue += products.get(i).getPrice()*products.get(i).getSoldCopies();
				}
			}
		}

		message = "Category: \n"+
		" Varieties: \n -Sales: "+varietiesSales+"\n -Revenue: "+varietiesRevenue+"\n"+
		" Design: \n -Sales: "+designSales+"\n -Revenue: "+designRevenue+"\n"+
		" Scientific: \n -Sales: "+ScientificSales+"\n -Revenue: "+ScientificRevenue;

		return message;
	}

	//Tools

	/**
	 * Searches for a bibliographic product by its ID.
	 * @param searchedProductID the ID of the product to search for.
	 * @return the bibliographic product found, or null if no product matches the ID.
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

	/**
	 * Searches for the position of a bibliographic product in the array based on its ID.
	 * @param searchedProductID the ID of the product to search for.
	 * @return the position of the product in the array, or -1 if the product is not found.
	 */
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
	 * Searches for a user in the user list based on their CC number.
	 * @param searchedUserCC the CC number of the user to search for.
	 * @return the User object if found, or null if the user is not found.
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

	/**
	 * Searches for the position of a user in the user list based on their CC number.
	 * @param searchedUserCC the CC number of the user to search for.
	 * @return the index of the user in the user list if found, or -1 if the user is not found.
	 */
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

	/**
	 * Retrieves the current date as a Calendar object.
	 * @return the current date.
	 */
	public Calendar getCurrentDate(){
		Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        currentDate.set(year, month, day);

		return currentDate;
	}

	/**
	 * Checks the type of a bibliographic product based on its ID.
	 * @param productID the ID of the product to check.
	 * @return an integer representing the product type: 0 for Book, 1 for Magazine, and -1 if the product is not found.
	 */
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

	/**
	 * Checks the type of a user based on their CC (Credit Card) number.
	 * @param usersCC the CC number of the user to check.
	 * @return an integer representing the user type: 0 for Regular user, 1 for Premium user, and -1 if the user is not found.
	 */
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

	/**
	 * Retrieves a bibliographic product from the products list based on its position
	 * @param position the position of the product in the list.
	 * @return the bibliographic product at the specified position.
	 */
	public BibliographicProduct getProductByPositon(int position){
		return products.get(position);
	}

	/**
	 * Retrieves a book from the products list and casts it to the Book class based on its position.
	 * @param position the position of the book in the list.
	 * @return the book at the specified position.
	 */
	public Book getProductCastedBook(int position){
		return (Book)products.get(position);
	}

	/**
	 * Retrieves a magazine from the products list and casts it to the Magazine class based on its position.
	 * @param position the position of the magazine in the list.
	 * @return the magazine at the specified position.
	 */
	public Magazine getProductCastedMagazine(int position){
		return (Magazine)products.get(position);
	}

	/**
	 * Retrieves a user from the users list based on its position.
	 * @param position the position of the user in the list.
	 * @return the user at the specified position.
	 */
	public User getUserByPosition(int position){
		return users.get(position);
	}

	/**
	 * Generates an advertisement message for the given user.
	 * @param user the user for whom to generate the advertisement.
	 * @return the generated advertisement message, or an empty string if the user is not advertisable.
	 */
	public String getAdvertisement(User user){
		String message = "";

		if(user instanceof Advertisable){
			message = ((Advertisable)user).generateAdvertisement();
		}

		return message;
	}
}