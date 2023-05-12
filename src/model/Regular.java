package model;

import java.util.Calendar;

public class Regular extends User {

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

}