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
	//private Test test;
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
			"9. Automatically generate objects in the system for each type of user and bibliographic product.\n"+
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

		System.out.println(".................................................");

		System.out.print("Type the name of the product: ");
		String name = reader.nextLine();
		
		int numPages = validateInteger("Type the product number of pages: ");

		Calendar publicationDate = readDate("Type the date of publication: ");
		
		System.out.print("Type the url with the product's cover: ");
		String url = reader.nextLine();
		
		Double price = validateDouble("Type the price of the product: ");
	

		System.out.print("Available bibliographic products:\n1. Books \n2. Magazine\n");
		int typeOption = validateOptionInputByRange("Select product's type: ", 1, 2);
	
		
		switch(typeOption){
			case 1:

				System.out.print("Type the book's review: ");
				String review = reader.nextLine();

				System.out.println("Avilable book's genres:\n1.Science fiction\n2.Fantasy\n3.Historical novel");
				int genreOption = validateOptionInputByRange("Select the genre: ", 1, 3);
				
				System.out.println(controller.addBook(name, numPages, review, publicationDate, genreOption, url, price));

				System.out.println(".................................................");

			break;

			case 2:
				System.out.print("Type the emission periodicity");
				int periodicityEmission = validateInteger(": ");

				System.out.println("Avilable magazines's categories:\n1.Varieties\n2.Design\n3.Scientific");
				int categoryOption = validateOptionInputByRange("Select the category: ", 1, 3);

				System.out.println(controller.addMagazine(name, numPages, publicationDate, categoryOption, url, price, periodicityEmission));

				System.out.println(".................................................");
			break;

		}
		
	}

	//Functional Requeriment 2: Modify Books information
	//Functional Requeriment 3: Modify Magazines information

	public void modifyBibliographicProductInfo() {

		System.out.println(".................................................");

		System.out.print("Type the identifier of the product you want to search: ");
		String searchedProductID = reader.next();

		int searchedProductType = controller.checkProductType(searchedProductID);

		if(searchedProductType != -1){

			System.out.print("Type the name of the product: ");
			String name = reader.next();

			int numPages = validateInteger("Type the product number of pages: ");

			Calendar publicationDate = readDate("Type the date of publication: ");

			System.out.print("Type the url with the product's cover: ");
			String url = reader.next();

			Double price = validateDouble("Type the price of the product: ");

			if(searchedProductType == 0){
				System.out.print("Type the book's review: ");
				String review = reader.next();

				System.out.println("Avilable book's genres:\n1.Science fiction\n2.Fantasy\n3.Historical novel\n");
				int genreOption = validateOptionInputByRange("Select the genre: ", 1, 3);

				System.out.println(".................................................");

				System.out.println(controller.modifyBookInfo(searchedProductID, name, numPages, review, publicationDate, genreOption, url, price));
			}else if(searchedProductType == 1){	
				int periodicityEmission = validateInteger("Type the periodicity emission: ");

				System.out.println("Avilable magazines's categories:\n1.Varieties\n2.Design\n3.Scientific\n");
				int categoryOption = validateOptionInputByRange("Select the category: ", 1, 3);

				System.out.println(".................................................");

				System.out.println(controller.modifyMagazineInfo(searchedProductID, name, numPages, publicationDate, categoryOption, url, price, periodicityEmission));
			}				
		}else{
			System.out.println("We couldn't found the product with the id "+searchedProductID);

			System.out.println(".................................................");
		}
	}

	//Functional Requeriment 4: Eliminate BibliographicProducts
	
	public void eliminateBibliographicProduct() {
		System.out.println(".................................................");

		System.out.print("Type the identifier of the product you want to search: ");
		String searchedProductID = reader.next();

		boolean isProductRemoved = controller.eliminateBibliographicProductFromArray(searchedProductID);

		if(isProductRemoved){
			System.out.println("The product has been removed. ");

			System.out.println(".................................................");
		}else{
			System.out.println("We couldn't found the product with the id "+searchedProductID);

			System.out.println(".................................................");
		}
		
	}

	//Functional Requeriment 5: Register regular and premimum users

	public void registerUser() {
		System.out.println(".................................................");
		
		System.out.print("Available types of user:\n1. Regular \n2. Premium");
		int userOption = validateOptionInputByRange("Select the type of user you want: ",1,2);
		reader.nextLine();

		System.out.print("Type your complete name: ");
		String name = reader.nextLine();

		System.out.print("Type your cc: ");
		String cc = reader.nextLine();

		switch(userOption){
			case 1:
			System.out.println(controller.addRegularUser(name, cc));
			break;

			case 2:
			System.out.println(controller.addPremiumUser(name, cc));
			break;
		}

		System.out.print(".................................................");
	}

	//Functional Requeriment 6: Allow a user to purchase a book and subscribe to a magazine.
	
	//Incomplete: validate if user can buy more products or magazines
	public void makeTransaction() {
		System.out.println(".................................................");

		System.out.print("Type the usersCC: ");
		String searchedUserCC = reader.nextLine();

		int searchedUserType = controller.checkUserType(searchedUserCC);

		if(searchedUserType != -1){
			System.out.print("Type the identifier of the product you want to search: ");
			String searchedProductID = reader.nextLine();

			int searchedProductType = controller.checkProductType(searchedProductID);

			if(searchedProductType != -1){
				System.out.println("The transaction has been successfully completed. \n...\n");
				System.out.println(controller.addBibliographicProductToUser(searchedUserCC, searchedProductID));
				System.out.println(".................................................");
			}else{
				System.out.println("We couldn't found the product with the id "+searchedProductID);

				System.out.println(".................................................");
			}			

			System.out.println(".................................................");
		}else{
			System.out.println("We couldn't found the user with the id "+searchedUserCC);

			System.out.println(".................................................");
		}
		
	}

	//Functional Requeriment 7: Allow a user to unsubscribe from a magazine.

	public void cancelMagazineSubscription() {

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

			System.out.println(".................................................");
		}else{
			System.out.println("We couldn't found the user with the id "+searchedUserCC);

			System.out.println(".................................................");
		}
		
	}

	//Functional Requeriment 8: Present Users Library of Bibliographic Products

	public void navigateUsersLibrary() {
		
		
	}

	//Functional Requeriment 9: Allow a user to simulate a reading session

	public void simulateReadingSessionByCoordinates(){

	}

	public void simulateReadingSessionByID(){ //Incomplete
		System.out.print("Type the users cc that wants to start the reading session: ");
		String searchedUserCC = reader.nextLine();

		int searchedUserType = controller.checkUserType(searchedUserCC);

		if(searchedUserType != -1){
			System.out.print("Type the identifier of the bibliographic product you want to search: ");
			String searchedProductID = reader.nextLine();

			int searchedProductType = controller.checkProductType(searchedProductID);

			if(searchedProductType != -1){
				String answer = "";

				System.out.println("********************************************");
				System.out.println(" Reading session in progress: ");
				int pages = validateInteger("From which page would you like to start reading?");

				do{
					System.out.println("Select one option: \nA. Go to the next page \nS. Go to the previous page\nB. Exit");
					answer = reader.next();


				}while(answer != "B" );
				System.out.println("********************************************");
			}else{
				System.out.println("We couldn't found the product with the id "+searchedProductID);
			}

			System.out.println(".................................................");
		}else{
			System.out.println("We couldn't found the user with the id "+searchedUserCC);

			System.out.println(".................................................");
		}
	}

	//Functional Requeriment 10: Automatically generate objects in the system for each type of user and bibliographic product.

	public void innitTest() {
		
	}

	//Functional Requeriment 11: Generate reports with recorded data

	public void generateReport(){

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
			System.out.println("The date must follow the format: dd/MM/yyyy");
			// Display a message indicating the required date format.
			System.out.print(message); 
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