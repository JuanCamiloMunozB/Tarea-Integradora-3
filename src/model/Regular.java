package model;

import java.util.Calendar;

/**
 * The Regular class represents a regular user who can purchase and manage bibliographic products.
 * Regular users have restrictions on the maximum number of books and magazines they can purchase.
 */
public class Regular extends User implements Advertisable, IBuy{

	/**
	 * The maximum number of books that can be purchased by a regular user
	 */
	private static final int MAX_BOOKS_PURCHASED = 5;

	/**
	 * The maximum number of magazines that can be subscribed by a regular user
	 */
	private static final int MAX_MAGAZINES_PURCHASED = 2;

	/**
	 * Creates a new Regular user with the specified name, identification number, and vinculation date.
	 * @param name The name of the user
	 * @param cc The identification number of the user
	 * @param vinculationDate The vinculation date of the user
	 */
	public Regular(String name, String cc, Calendar vinculationDate) {
		super(name, cc, vinculationDate);
	}

	/**
	 * Counts the number of books owned by the user.
	 * @return The number of books owned by the user
	 */
	public int countBooks() {
		int booksCounter = 0;
		
		for (BibliographicProduct product : ownedProducts) {
			if (product instanceof Book) {
				booksCounter++;
			}
		}

		return booksCounter;
	}

	/**
	 * Counts the number of magazines owned by the user.
	 * @return The number of magazines owned by the user
	 */
	public int countMagazines() {
		int magazinesCounter = 0;
		
		for (BibliographicProduct product : ownedProducts) {
			if (product instanceof Magazine) {
				magazinesCounter++;
			}
		}

		return magazinesCounter;
		
	}

	@Override
	public boolean purchaseBook(Book book, Transaction bill){
		boolean isRegistered = false;
		if(countBooks()<MAX_BOOKS_PURCHASED && !isProductAlreadyPurchased(book)){
			isRegistered = ownedProducts.add(book);
			addPagesRead(0);
			addTransaction(bill);

			book.setSoldCopies(book.getSoldCopies()+1);;
		}

		return isRegistered;
	}

	@Override
    public boolean suscribeMagazine(Magazine magazine, Transaction bill){
		boolean isRegistered = false;
		if(countMagazines()<MAX_MAGAZINES_PURCHASED && !isProductAlreadyPurchased(magazine)){
			isRegistered = ownedProducts.add(magazine);
			addPagesRead(0);
			addTransaction(bill);

			magazine.setSoldCopies(magazine.getSoldCopies()+1);;
		}

		return isRegistered;
	}

	@Override
	public boolean eliminateBook(Book book, Transaction bill){
		boolean isRemoved = false;
		if(isProductAlreadyPurchased(book)){
			isRemoved = ownedProducts.remove(book);
			invoices.remove(bill);

			book.setSoldCopies(book.getSoldCopies()-1);
		}

		return isRemoved;
	}

	@Override
	public boolean cancelMagazineSubscription(Magazine magazine, Transaction bill){
		boolean isRemoved = false;
		if(isProductAlreadyPurchased(magazine)){
			isRemoved = ownedProducts.remove(magazine);
			invoices.remove(bill);

			magazine.setSoldCopies(magazine.getSoldCopies()-1);
		}

		return isRemoved;
	}

}