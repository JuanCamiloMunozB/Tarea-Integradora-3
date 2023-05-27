package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The abstract class representing a User.
 * It provides common attributes and methods for Regular users and Premium users.
 */
public abstract class User {

	/**
	 * The name of the user
	 */
	private String name;

	/**
	 * The identification number of the user
	 */
	private String cc;

	/**
	 * The date of user's vinculation
	 */
	private Calendar vinculationDate;

	/**
	 * The list of products owned by the user
	 */
	protected List<BibliographicProduct> ownedProducts;

	/**
	 * The list of transactions made by the user
	 */
	protected List<Transaction> invoices;

	/**
	 * The list of pages read by the user
	 */
	protected List<Integer> pagesRead;

	/**
	 * Constructs a new User object with the specified parameters.
	 * @param name The name of the user
	 * @param cc The identification number of the user
	 * @param vinculationDate The date of user's vinculation
	 */
	public User(String name, String cc, Calendar vinculationDate) {
		this.name = name;
		this.cc = cc;
		this.vinculationDate = vinculationDate;

		ownedProducts = new ArrayList<>();
		invoices = new ArrayList<>();
		pagesRead = new ArrayList<>();
	}

	/**
	 * Returns the name of the user.
	 * @return The name of the user
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the identification number of the user.
	 * @return The identification number of the user
	 */
	public String getCC() {
		return this.cc;
	}

	/**
	 * Returns the vinculation date of the user.
	 * @return The vinculation date of the user
	 */
	public Calendar getVinculationDate() {
		return this.vinculationDate;
	}

	/**
	 * Returns the list of owned products by the user.
	 * @return The list of owned products
	 */
	public List<BibliographicProduct> getOwnedProducts() {
		return this.ownedProducts;
	}

	/**
	 * Returns the list of transactions made by the user.
	 * @return The list of transactions
	 */
	public List<Transaction> getTransactions() {
		return this.invoices;
	}

	/**
	 * Returns the list of pages read by the user.
	 * @return The list of pages read
	 */
	public List<Integer> getPagesRead(){
		return this.pagesRead;
	}

	/**
	 * Adds the specified number of pages to the list of pages read by the user.
	 * @param pages The number of pages to add
	 * @return true if the operation is successful, false otherwise
	 */
	public boolean addPagesRead(int pages){
		return pagesRead.add(pages);
	}

	/**
	 * Adds the specified transaction to the list of transactions made by the user.
	 * @param transaction The transaction to be added
	 * @return true if the operation is successful, false otherwise
	 */
	public boolean addTransaction(Transaction transaction) {
		return invoices.add(transaction);
	}

	/**
	 * Searches and returns the owned product with the specified ID.
	 * @param searchedProductID The ID of the product to search for
	 * @return The owned product with the specified ID, or null if not found
	 */
	public BibliographicProduct searchOwnedProductByID(String searchedProductID) {
		BibliographicProduct searchedProduct = null;
		
		for (BibliographicProduct product : ownedProducts) {
			if (product.getID().equals(searchedProductID)) {
				searchedProduct = product;
			}
		}
		return searchedProduct;
	}
	
	/**
	 * Searches and returns the position of the owned product with the specified ID in the owned products list.
	 * @param searchedProductID The ID of the product to search for
	 * @return The position of the owned product in the list, or -1 if not found
	 */
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

	/**
	 * Checks if the specified product is already purchased by the user.
	 * @param product The product to check
	 * @return true if the product is already purchased, false otherwise
	 */
	public boolean isProductAlreadyPurchased(BibliographicProduct product){
		return ownedProducts.contains(product);
	}

	/**
	 * Searches and returns the transaction associated with the specified product.
	 * @param product The product to search for
	 * @return The transaction associated with the product, or null if not found
	 */
	public Transaction searchTransactionByProduct(BibliographicProduct product){
		boolean isFound = false;
		Transaction searchedTransaction = null;

		for(int i = 0; i<invoices.size() && isFound; i++){
			if(invoices.get(i).getPurchasedProduct() == product){
				searchedTransaction = invoices.get(i);
			}
		}

		return searchedTransaction;
	}
}