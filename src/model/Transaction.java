package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * The Transaction class represents a purchase transaction.
 */
public class Transaction {

	/**
	 * The date when the transaction was made
	 */
	private Calendar realizationDate;

	/**
	 * The amount paid for the transaction
	 */
	private Double paidAmount;

	/**
	 * The product purchased in the transaction
	 */
	private BibliographicProduct purchasedProduct;

	/**
	 * The formatter for date formatting
	 */
	private DateFormat formatter;

	/**
	 * Creates a new Transaction instance.
	 * @param realizationDate The date when the transaction was made.
	 * @param paidAmount The amount paid for the transaction.
	 * @param purchaseProduct The purchased product associated with the transaction.
	 */
	public Transaction(Calendar realizationDate, Double paidAmount, BibliographicProduct purchaseProduct) {
		this.realizationDate = realizationDate;
		this.paidAmount = paidAmount;
		this.purchasedProduct = purchaseProduct;

		this.formatter = new SimpleDateFormat("dd/mm/yyyy");
	}

	/**
	 * Returns the realization date of the transaction.
	 * @return The realization date as a Calendar object.
	 */
	public Calendar getRealizationDate() {
		return this.realizationDate;
	}

	/**
	 * Returns the formatted realization date of the transaction.
	 * @return The formatted realization date as a string.
	 */
	public String getRealizationDateFormated(){
		return formatter.format(this.realizationDate.getTime());
	}

	/**
	 * Returns the amount paid for the transaction.
	 * @return The paid amount as a Double.
	 */
	public Double getPaidAmount() {
		return this.paidAmount;
	}

	/**
	 * Returns the purchased product associated with the transaction.
	 * @return The purchased product as a BibliographicProduct object.
	 */
	public BibliographicProduct getPurchasedProduct(){
		return this.purchasedProduct;
	}

	/**
	 * Returns the transaction information as a formatted string.
	 * @return The transaction information as a string.
	 */
	public String getTransactionInfo(){
		return "\n<<------------------BILL------------------>>\n"+
				"Date of purchse : "+getRealizationDateFormated()+"\n"+
				"Purchased product: "+purchasedProduct.getName()+"\n"+
				"Price: $"+purchasedProduct.getPrice()+"\n"+
				"Paid amount: $"+paidAmount+"\n"+
				"Change: $"+(paidAmount-purchasedProduct.getPrice())+
				"\n<<------------------------------------>>\n";
	}

}