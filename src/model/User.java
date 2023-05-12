package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class User {

	private String name;
	private String cc;
	private Calendar vinculationDate;
	protected List<BibliographicProduct> ownedProducts;
	protected List<Transaction> invoices;

	/**
	 * 
	 * @param name
	 * @param cc
	 * @param vinculationDate
	 */
	public User(String name, String cc, Calendar vinculationDate) {
		this.name = name;
		this.cc = cc;
		this.vinculationDate = vinculationDate;

		ownedProducts = new ArrayList<>();
		invoices = new ArrayList<>();
	}

	public String getName() {
		return this.name;
	}

	public String getCC() {
		return this.cc;
	}

	public Calendar getVinculationDate() {
		return this.vinculationDate;
	}

	public List<BibliographicProduct> getBibliograpicProducts() {
		return this.ownedProducts;
	}

	public List<Transaction> getTransactions() {
		return this.invoices;
	}


	/**
	 * 
	 * @param product
	 */
	public boolean addBibliographicProduct(BibliographicProduct product) {
		return ownedProducts.add(product);
	}

	/**
	 * 
	 * @param transaction
	 */
	public boolean addTransaction(Transaction transaction) {
		return invoices.add(transaction);
	}

	public boolean removeProduct(BibliographicProduct product){
		return ownedProducts.remove(product);
	}

	public BibliographicProduct searchOwnedProductByID(String searchedProductID) {
		BibliographicProduct searchedProduct = null;
		
		for (BibliographicProduct product : ownedProducts) {
			if (product.getID() == searchedProductID) {
				searchedProduct = product;
			}
		}

		return searchedProduct;
	}

	public int searchOwnedProductArrayPosition(String searchedProductID){
		int productPosition = -1;
		boolean isFound = false;
		
		for(int i = 0; i < ownedProducts.size() && !isFound ; i++){
			if (ownedProducts.get(i).getID().equalsIgnoreCase(searchedProductID)){
				productPosition = i;
				isFound = true;
			}
		}

		return productPosition;
	}

}