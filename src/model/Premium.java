package model;

import java.util.Calendar;

/**
 * The Premium class represents a premium user who can purchase and manage bibliographic products.
 * Premium users have no restrictions on the maximum number of books and magazines they can purchase.
 */
public class Premium extends User implements IBuy {

	/**
	 * Creates a new Premium user with the specified name, identification number, and vinculation date.
	 * @param name The name of the user
	 * @param cc The identification number of the user
	 * @param vinculationDate The vinculation date of the user
	 */
	public Premium(String name, String cc, Calendar vinculationDate) {
		super(name, cc, vinculationDate);
	}

	@Override
	public boolean purchaseBook(Book book, Transaction bill){
		boolean isRegistered = false;

		if(!isProductAlreadyPurchased(book)){
			isRegistered = ownedProducts.add(book);
			addPagesRead(0);
			addTransaction(bill);

			book.setSoldCopies(book.getSoldCopies()+1);
		}

		return isRegistered;
	}

	@Override
    public boolean suscribeMagazine(Magazine magazine, Transaction bill){
		boolean isRegistered = false;
		if(!isProductAlreadyPurchased(magazine)){
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