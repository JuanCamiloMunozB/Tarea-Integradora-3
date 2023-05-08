package ui;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.ParseException;

import model.BibliographicProduct;
import model.Book;
import model.Magazine;
import model.ReadXSystems;

public class Main {

	private Scanner reader;
	private ReadXSystems controller;
	private Test test;
	private SimpleDateFormat format;

	public Main(){
		reader = new Scanner(System.in);
		controller = new ReadXSystems();
		test = new Test();
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
				simulateReadingSession();
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

		System.out.println(".................................................");
		
		int numPages = validateInteger("Type the product number of pages: ");

		System.out.println(".................................................");

		Calendar publicationDate = readDate("Type the date of publication: ");

		System.out.println(".................................................");
		
		System.out.print("Type the url with the product's cover: ");
		String url = reader.nextLine();

		System.out.println(".................................................");
		
		Double price = validateDouble("Type the price of the product: ");
		
		System.out.println(".................................................");

		System.out.print("Available bibliographic products:\n1. Books \n2. Magazine\n");
		int typeOption = validateOptionInputByRange("Select product's type: ", 1, 2);
		
		System.out.println(".................................................");
		
		switch(typeOption){
			case 1:

				System.out.print("Type the book's review: ");
				String review = reader.nextLine();

				System.out.println(".................................................");

				System.out.println("Avilable book's genres:\n1.Science fiction\n2.Fantasy\n3.Historical novel");
				int genreOption = validateOptionInputByRange("Select the genre: ", 1, 3);
				
				System.out.println(".................................................");

				System.out.println(controller.addBook(name, numPages, review, publicationDate, genreOption, url, price));
			break;

			case 2:
				System.out.print("Type the emission periodicity");
				int periodicityEmission = validateInteger(": ");

				System.out.println("Avilable magazines's categories:\n1.Varieties\n2.Design\n3.Scientific");
				int categoryOption = validateOptionInputByRange("Select the category: ", 1, 3);

				System.out.println(controller.addMagazine(name, numPages, publicationDate, categoryOption, url, price, periodicityEmission));
			break;

		}
		
	}

	//Functional Requeriment 2: Modify Books information
	//Functional Requeriment 3: Modify Magazines information

	public void modifyBibliographicProductInfo() {

		System.out.println(".................................................");

		System.out.print("Type the identifier of the product you want to search: ");
		String searchedProductID = reader.next();

		BibliographicProduct searchedProduct = controller.searchBibliographicProductByID(searchedProductID);

		System.out.println(".................................................");

		if(searchedProduct != null){

			System.out.print("Current name : " + searchedProduct.getName() + "\nType the name of the product: ");
			String name = reader.next();

			System.out.println(".................................................");

			System.out.print("Current number of pages : " + searchedProduct.getNumPages()+"\n");
			int numPages = validateInteger("Type the product number of pages: ");

			System.out.println(".................................................");

			System.out.print("Current publication date : " + searchedProduct.getPublicationDate() + "\n");
			Calendar publicationDate = readDate("Type the date of publication: ");

			System.out.println(".................................................");

			System.out.print("Current url : " + searchedProduct.getURL() +"\nType the url with the product's cover: ");
			String url = reader.next();

			System.out.println(".................................................");

			System.out.print("Current price: " + searchedProduct.getPrice() +"\n");
			Double price = validateDouble("Type the price of the product: ");

			System.out.println(".................................................");

			if(searchedProduct instanceof Book){
				System.out.print("Current review: " + ((Book)searchedProduct).getReview() + "Type the book's review: ");
				String review = reader.next();

				System.out.println(".................................................");

				System.out.println("Current genre: " + ((Book)searchedProduct).getGenre() +"Avilable book's genres:\n1.Science fiction\n2.Fantasy\n3.Historical novel\n");
				int genreOption = validateOptionInputByRange("Select the genre: ", 1, 3);

				System.out.println(".................................................");

				System.out.println(controller.modifyBookInfo(searchedProduct, name, numPages, review, publicationDate, genreOption, url, price));
			}else{	
				System.out.print("Current periodicity emission: " + ((Magazine)searchedProduct).getPeriodicityEmission() +"\n");
				int periodicityEmission = validateInteger("Type the periodicity emission: ");

				System.out.println(".................................................");

				System.out.println("Current category: " + ((Magazine)searchedProduct).getCategory() +"Avilable magazines's categories:\n1.Varieties\n2.Design\n3.Scientific\n");
				int categoryOption = validateOptionInputByRange("Select the category: ", 1, 3);

				System.out.println(".................................................");

				System.out.println(controller.modifyMagazineInfo(searchedProduct, name, numPages, publicationDate, categoryOption, url, price, periodicityEmission));
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

		BibliographicProduct searchedProductPosition = controller.searchBibliographicProductByID(searchedProductID);
		
		if(searchedProductPosition != null){
			System.out.println(controller.eliminateBibliographicProductFromArray(searchedProductPosition));
		}else{
			System.out.println("We couldn't found the product with the id "+searchedProductID);

			System.out.println(".................................................");
		}
		
	}

	//Functional Requeriment 5: Register regular and premimum users

	public void registerUser() {
		
	}

	//Functional Requeriment 6: Allow a user to purchase a book and subscribe to a magazine.
	
	public void makeTransaction() {
		
		
	}

	//Functional Requeriment 7: Allow a user to unsubscribe from a magazine.

	public void cancelMagazineSubscription() {
		
		
	}

	//Functional Requeriment 8: Present Users Library of Bibliographic Products

	public void navigateUsersLibrary() {
		
		
	}

	//Functional Requeriment 9: Allow a user to simulate a reading session

	public void simulateReadingSession(){

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