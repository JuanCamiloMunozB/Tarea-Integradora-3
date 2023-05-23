package ui;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.ParseException;

import model.ReadXSystems;

public class Main {

	private Scanner reader;
	private ReadXSystems controller;
	private SimpleDateFormat format;

	public Main(){
		reader = new Scanner(System.in);
		controller = new ReadXSystems();
		//test = new Test();
		format = new SimpleDateFormat("dd/M/yy");
	}

	public static void main(String[] args){
		Main exe = new Main();
		int answer; 

		do{
			exe.printMenu();
			answer = exe.validateInteger("Select one of the above options: ");
			exe.executeOption(answer);

		}while(answer != 0);

		exe.reader.close();
	}

	public void printMenu() {
		System.out.println(
			"\n" +
			"<< --------------------------------------------------------------------- >>\n" +
			"<< -                                Welcome                            - >>\n" +
			"<< --------------------------------------------------------------------- >>\n" +
			"1. Register a bibliographic product. \n" +
			"2. Modify bibliographic product's information. \n" + 
			"3. Eliminate bibliographic product. \n" +
			"4. Register a new user. \n" +
			"5. Register a user transaction\n"+
			"6. Cancel users subscription to magazine\n"+
			"7. Present Users Library of Bibliographic Products\n"+
			"8. Simulate a reading session\n"+
			"9. Automatically generate objects in the system for testing.\n"+
			"10. Generate reports with recorded data\n"+
			"0. Exit program."
		);
	}

	public void executeOption(int selectedOption){
		switch(selectedOption){
			case 0:
			System.out.println("Thanks four using our app!");
			break;

			case 1:
				registerBibliographicProduct();
			break;

			case 2:
				modifyBibliographicProductInfo();
			break;

			case 3:
				eliminateBibliographicProduct();
			break;

			case 4:
				registerUser();
			break;

			case 5:
				makeTransaction();
			break;

			case 6:
				cancelMagazineSubscription();
			break;

			case 7:
				navigateUsersLibrary();
			break;

			case 8:
				simulateReadingSessionByID();
			break;

			case 9:
				innitTest();
			break;

			case 10: 
				generateReport();
			break;

			default:
				System.out.println("Invalid Option. Please, try again");
			break;
		}
	}

	//Functional Requeriment 0: Register Books
	//Functional Requeriment 1: Register Magazines

	public void registerBibliographicProduct() {

		System.out.println("<< --------------------------------------------------------------------- >>");

		System.out.print("\n<<Type the name of the product: ");
		String name = reader.nextLine();
		
		int numPages = validateInteger("\n<<Type the product number of pages: ");

		Calendar publicationDate = readDate("Type the date of publication: ");
		
		System.out.print("\n<<Type the url with the product's cover: ");
		String url = reader.nextLine();
		
		Double price = validateDouble("\n<<Type the price of the product: ");
	

		System.out.println("\nAvailable bibliographic products:\n 1. Books \n 2. Magazine");
		int typeOption = validateOptionInputByRange(" <<Select product's type: ", 1, 2);
	
		
		switch(typeOption){
			case 1:

				System.out.print("\n<<Type the book's review: ");
				String review = reader.nextLine();

				System.out.println("\n<<Avilable book's genres:\n 1.Science fiction\n 2.Fantasy\n 3.Historical novel");
				int genreOption = validateOptionInputByRange(" Select the genre: ", 1, 3);
				
				System.out.println(controller.addBook(name, numPages, review, publicationDate, genreOption, url, price));

				System.out.println("<< --------------------------------------------------------------------- >>");

			break;

			case 2:
				System.out.print("\n<<Type the emission periodicity");
				String periodicityEmission = reader.nextLine();

				System.out.println("\n<<Avilable magazines's categories:\n 1.Varieties\n 2.Design\n 3.Scientific");
				int categoryOption = validateOptionInputByRange(" Select the category: ", 1, 3);

				System.out.println(controller.addMagazine(name, numPages, publicationDate, categoryOption, url, price, periodicityEmission));

				System.out.println("<< --------------------------------------------------------------------- >>");
			break;

		}
		
	}

	//Functional Requeriment 2: Modify Books information
	//Functional Requeriment 3: Modify Magazines information

	public void modifyBibliographicProductInfo() {

		System.out.println("<< --------------------------------------------------------------------- >>");

		System.out.print("\n<<Type the identifier of the product you want to search: ");
		String searchedProductID = reader.next();

		int searchedProductPosition = controller.searchUserArrayPosition(searchedProductID);

		if(searchedProductPosition != -1){
			int searchedProductType = controller.checkProductType(searchedProductID);

			System.out.print("\nCurrent name: "+controller.getProductByPositon(searchedProductPosition).getName()+"<<Type the name of the product: ");
			String name = reader.next();

			System.out.println("\nCurrent number of pages: "+controller.getProductByPositon(searchedProductPosition).getNumPages());
			int numPages = validateInteger("<<Type the product number of pages: ");

			System.out.println("\nCurrent publicationDate: "+controller.getProductByPositon(searchedProductPosition).getPublicationDateFormated());
			Calendar publicationDate = readDate("<<Type the date of publication: ");

			System.out.print("\nCurrent URL: "+controller.getProductByPositon(searchedProductPosition).getURL()+"<<Type the url with the product's cover: ");
			String url = reader.next();

			System.out.println("\nCurrent price: "+controller.getProductByPositon(searchedProductPosition).getPrice());
			Double price = validateDouble("<<Type the price of the product: ");

			if(searchedProductType == 0){
				System.out.print("\nCurrent review: "+controller.getProductCastedBook(searchedProductPosition).getReview()+"<<Type the book's review: ");
				String review = reader.next();

				System.out.println("\nCurrent genre: "+controller.getProductCastedBook(searchedProductPosition).getGenreString()+"Avilable book's genres:\n1.Science fiction\n2.Fantasy\n3.Historical novel");
				int genreOption = validateOptionInputByRange("<<Select the genre: ", 1, 3);

				System.out.println("<< --------------------------------------------------------------------- >>");

				System.out.println(controller.modifyBookInfo(searchedProductID, name, numPages, review, publicationDate, genreOption, url, price));

			}else if(searchedProductType == 1){	
				System.out.print("\nCurrent periodicity emission: "+controller.getProductCastedMagazine(searchedProductPosition).getPeriodicityEmission()+"<<Type the emission periodicity");
				String periodicityEmission = reader.nextLine();

				System.out.println("\nCurrent category: "+controller.getProductCastedMagazine(searchedProductPosition).getCategoryString()+"Avilable magazines's categories:\n1.Varieties\n2.Design\n3.Scientific");
				int categoryOption = validateOptionInputByRange("<<Select the category: ", 1, 3);

				System.out.println("<< --------------------------------------------------------------------- >>");

				System.out.println(controller.modifyMagazineInfo(searchedProductID, name, numPages, publicationDate, categoryOption, url, price, periodicityEmission));
			}				
		}else{
			System.out.println("We couldn't found the product with the id "+searchedProductID);

			System.out.println("<< --------------------------------------------------------------------- >>");
		}
	}

	//Functional Requeriment 4: Eliminate BibliographicProducts
	
	public void eliminateBibliographicProduct() {
		System.out.println("<< --------------------------------------------------------------------- >>");

		System.out.print("\n<<Type the identifier of the product you want to search: ");
		String searchedProductID = reader.next();

		boolean isProductRemoved = controller.eliminateBibliographicProductFromArray(searchedProductID);

		if(isProductRemoved){
			System.out.println("The product has been removed. ");

			System.out.println("<< --------------------------------------------------------------------- >>");
		}else{
			System.out.println("We couldn't found the product with the id "+searchedProductID);

			System.out.println("<< --------------------------------------------------------------------- >>");
		}
		
	}

	//Functional Requeriment 5: Register regular and premimum users

	public void registerUser() {
		System.out.println("<< --------------------------------------------------------------------- >>");
		
		System.out.print("Available types of user:\n1. Regular \n2. Premium");
		int userOption = validateOptionInputByRange("\n<<Select the type of user you want: ",1,2);

		System.out.print("\n<<Type your complete name: ");
		String name = reader.nextLine();

		System.out.print("\n<<Type your cc: ");
		String cc = reader.nextLine();

		switch(userOption){
			case 1:
			System.out.println(controller.addRegularUser(name, cc));
			break;

			case 2:
			System.out.println(controller.addPremiumUser(name, cc));
			break;
		}

		System.out.print("<< --------------------------------------------------------------------- >>");
	}

	//Functional Requeriment 6: Allow a user to purchase a book and subscribe to a magazine.
	
	//Incomplete: validate if user can buy more products or magazines
	public void makeTransaction() {
		System.out.println("<< --------------------------------------------------------------------- >>");

		System.out.print("\n<<Type the usersCC: ");
		String searchedUserCC = reader.nextLine();

		int searchedUserType = controller.checkUserType(searchedUserCC);

		if(searchedUserType != -1){
			System.out.print("\n<<Type the identifier of the product you want to search: ");
			String searchedProductID = reader.nextLine();

			int searchedProductType = controller.checkProductType(searchedProductID);

			if(searchedProductType != -1){
				System.out.println("The transaction has been successfully completed. \n...");
				System.out.println(controller.addBibliographicProductToUser(searchedUserCC, searchedProductID));
			}else{
				System.out.println("We couldn't found the product with the id "+searchedProductID);
			}			

			System.out.println("<< --------------------------------------------------------------------- >>");
		}else{
			System.out.println("We couldn't found the user with the id "+searchedUserCC);

			System.out.println("<< --------------------------------------------------------------------- >>");
		}
		
	}

	//Functional Requeriment 7: Allow a user to unsubscribe from a magazine.

	public void cancelMagazineSubscription() {

		System.out.println("<< --------------------------------------------------------------------- >>");

		System.out.print("Type the users cc: ");
		String searchedUserCC = reader.nextLine();

		int searchedUserType = controller.checkUserType(searchedUserCC);

		if(searchedUserType != -1){
			System.out.print("Type the identifier of the magazine you want to search: ");
			String searchedProductID = reader.nextLine();

			int searchedProductType = controller.checkProductType(searchedProductID);

			switch(searchedProductType){
				case -1:
				System.out.println("We couldn't found the product with the id "+searchedProductID);
				break;

				case 0:
				System.out.println("The product with the id "+ searchedProductID+" is not a magazine. ");
				break;

				case 1:
				controller.elimanteMagazineFromUser(searchedUserCC, searchedProductID);
				System.out.println("The suscription to the magazine was canceled. ");
				break;
			}

			System.out.println("<< --------------------------------------------------------------------- >>");
		}else{
			System.out.println("We couldn't found the user with the id "+searchedUserCC);

			System.out.println("<< --------------------------------------------------------------------- >>");
		}
		
	}

	//Functional Requeriment 8: Present Users Library of Bibliographic Products

	public void navigateUsersLibrary() {
		int libraryPage = 0;

		System.out.print("<<Type the users cc: ");
		String searchedUserCC = reader.nextLine();

		System.out.println(controller.showUsersLibrary(searchedUserCC, libraryPage));
		
		System.out.println("<<Type what you want to do:\n 1. Navigate library\n 2. Start Reading session\n :");
		int answer = reader.nextInt();

		switch(answer){
			case 1:
				String action;

				do{
					System.out.println("\nSelect one option: \nA. Go to the next page \nS. Go to the previous page\nE. Exit\n:");
					action = reader.next();

					if(action.equalsIgnoreCase("a")){
						libraryPage++;
					}else if(action.equalsIgnoreCase("s")){
						libraryPage--;
					}else if(action.equalsIgnoreCase("e")){
						System.out.println("\nreturning to the menu. \n");
					}else{
						System.out.println("\nInvalid option. Please, try again.");
					}
					
					
				}while(!action.equalsIgnoreCase("e"));

			break;

			case 2:
			break;
		}

	}

	//Functional Requeriment 9: Allow a user to simulate a reading session

	public void simulateReadingSessionByCoordinates(){
		System.out.println("");
	}

	public void simulateReadingSessionByID(){

		System.out.println("<< --------------------------------------------------------------------- >>");

		System.out.print("\n<<Type the users cc that wants to start the reading session: ");
		String searchedUserCC = reader.nextLine();

		int userPosition = controller.searchUserArrayPosition(searchedUserCC);

		if(userPosition != -1){
			System.out.print("\n<<Type the identifier of the bibliographic product you want to search: ");
			String searchedProductID = reader.nextLine();

			int productPosition = controller.getUserByPosition(userPosition).searchOwnedProductArrayPosition(searchedProductID);

			if(productPosition != -1){
				String action = "";
				
				System.out.print(
					"*****************************************"+
					"\nReading Session in progress"+
					"\nReading: "+controller.getUserByPosition(userPosition).getOwnedProducts().get(productPosition).getName()+
					"\nReading page "+controller.getUserByPosition(userPosition).getPagesRead().get(productPosition)+" out of "+controller.getUserByPosition(userPosition).getOwnedProducts().get(productPosition).getNumPages()+
					"\nSelect one option: \nA. Go to the next page \nS. Go to the previous page\nB. Exit\n: "
				);
				
				do{
					action = reader.next();
					System.out.print(controller.startReadingSession(userPosition, productPosition, action));
				}while(!action.equalsIgnoreCase("B"));

			}else{
				System.out.println("The user doesn't have the product with the id "+searchedProductID);
			}

		}else{
			System.out.println("We couldn't found the user with the id "+searchedUserCC);

		}

		System.out.println("<< --------------------------------------------------------------------- >>");
	}

	//Functional Requeriment 10: Automatically generate objects in the system for each type of user and bibliographic product.

	public void innitTest() {
		System.out.println(controller.initModel());
	}

	//Functional Requeriment 11: Generate reports with recorded data

	public void generateReport(){
		System.out.println("<< --------------------------------------------------------------------- >>");
		System.out.println("Which report do you want to generate?");
		System.out.println("1. Report the total accumulated number of pages read on the entire platform");
		System.out.println("2. Report the most read book genre and magazine category for the entire platform.");
		System.out.println("3. Report the Top 5 most read books and Top 5 most read magazines on the platform.");
		System.out.println("4. Report the number of books sold and total sales value, for each gender");
		System.out.println("5. Report the number of active subscriptions and the total value paid for subscriptions.");

		int answer = validateOptionInputByRange("", 1, 5);

		switch(answer){

			case 1:
				System.out.println("Report 1 \n"+controller.getTotalPagesReadByType());
				
			break;
		
			case 2:
				System.out.println("Report 2 \n"+controller.getMostReadGenreAndCategory());
			break;

			case 3:
				System.out.println("Report 3 \n"+
				"Top 5 most read books: \n"+ controller.getTop5BookList()+
				"Top 5 most read magazines: \n"+ controller.getTop5MagazinesList());
			break;

			case 4:
				System.out.println("Report 4 \n"+controller.informSalesByGenre());
			break;

			case 5:
				System.out.println("Report 5 \n"+controller.informSalesByCategory());
			break;
		
		}

		System.out.println("<< --------------------------------------------------------------------- >>");

	}

	//Other functionalities

	/**
     * This method reads and validate a date typed by the user.
     * @param msg : String the message that will be displayed to the user to indicate what or where to write their response.
     * @return : Calendar the input of the user.
     */
	public Calendar readDate2(String message){
		
		Calendar calendarTime = new GregorianCalendar();
		String date = "";
		boolean isDateSet; //flag type variable.

		do{
            isDateSet = false;
            System.out.println("The date must follow the format: dd/M/yyyy");
            System.out.println(message); 
            date = reader.nextLine();
		
            try {
                calendarTime.setTime(format.parse(date));
                isDateSet= true;
            } catch (ParseException error) {
                System.out.println("Invalid option. Please, try again");
				reader.nextLine();
            }

        }while(isDateSet == false);
		
		return calendarTime;
	}

	public Calendar readDate(String message){
		Calendar calendarTime = new GregorianCalendar();
		// Create an instance of the Calendar class with the current date.
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		// Create an instance of SimpleDateFormat with the pattern "dd/MM/yyyy".
		format.setLenient(false);
		// Set strict mode on the SimpleDateFormat object.
		String date = "";
		// Initialize a variable to store the input date string.
		boolean validDate = false;
		// Initialize a flag to check if the input date is valid.
	
		while (!validDate) {
			// Start a loop while the input date is not valid.
			// Display a message indicating the required date format.
			System.out.print("\n<<The date must follow the format: dd/MM/yyyy\n "+ message); 
			// Display the custom message passed as an argument.
			date = reader.nextLine();
			// Read the date input by the user.
			try {
				calendarTime.setTime(format.parse(date));
				// Attempt to parse the input date using the SimpleDateFormat object.
				validDate = true;
				// If parsing is successful, set the "validDate" flag to true to exit the loop.
			} catch (ParseException error) {
				System.out.println("Invalid date format. Please try again.");
				// If an exception occurs while parsing the date, display an error message and prompt for user input again.
			}
		}
	
		return calendarTime;
		// Once the input date is validated, return an instance of Calendar that represents that date.
	}

    /**
     * This method validates an integer input by the user.
     * @param message : String the message that will be displayed to the user to indicate what or where to write their response.
     * @return : int the input of the user.
     */
	public int validateInteger(String message){
        int input = -1;
        boolean stopCondition = false; //flag type variable.
        
        do{
            System.out.print(message);

            if(reader.hasNextInt()){
                stopCondition = true;
                input = reader.nextInt();
                reader.nextLine();
                                      
            }
            else{
                System.out.println("Invalid option. Please, try again.");
                reader.nextLine();
            }

        }while(stopCondition != true);

        return input;
    }

    /**
     * This method validates a double input by the user.
     * @param message : String the message that will be displayed to the user to indicate what or where to write their response.
     * @return : double the input of the user.
     */
    public double validateDouble(String message){
        Double input = -1.0;
        boolean stopCondition = false; //flag type variable

        do{
            System.out.print(message);

            if(reader.hasNextDouble()){
                stopCondition = true;
                input = reader.nextDouble();          
            }
            else{
                System.out.println("Invalid option. Please, try again");
                reader.nextLine();
            }
        }while(stopCondition != true);

        return input;
	}

	public int validateOptionInputByRange(String message, int leftEndpoint, int rightEndPoint){
		int option = 0;

		do{
			option = validateInteger(message);

			if(option<leftEndpoint || option>rightEndPoint){
				System.out.println("the option is out of range. Plese, try again");
			}

		}while(option<leftEndpoint || option>rightEndPoint);

		return option;
	}
}