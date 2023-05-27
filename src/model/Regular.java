package model;

import java.util.Calendar;

public class Regular extends User implements Advertisable, IBuy{

	private static final int MAX_BOOKS_PURCHASED = 5;
	private static final int MAX_MAGAZINES_PURCHASED = 2;

	/**
	 * 
	 * @param name
	 * @param cc
	 * @param vinculationDate
	 */
	public Regular(String name, String cc, Calendar vinculationDate) {
		super(name, cc, vinculationDate);
	}

	public int countBooks() {
		int booksCounter = 0;
		
		for (BibliographicProduct product : ownedProducts) {
			if (product instanceof Book) {
				booksCounter++;
			}
		}

		return booksCounter;
	}

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