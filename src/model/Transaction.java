package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Transaction {

	private Calendar realizationDate;
	private Double paidAmount;
	private BibliographicProduct purchasedProduct;

	private DateFormat formatter;

	/**
	 * 
	 * @param realizationDate
	 * @param paidAmount
	 */
	public Transaction(Calendar realizationDate, Double paidAmount, BibliographicProduct purchaseProduct) {
		this.realizationDate = realizationDate;
		this.paidAmount = paidAmount;
		this.purchasedProduct = purchaseProduct;

		this.formatter = new SimpleDateFormat("dd/M/yy");
	}

	public Calendar getRealizationDate() {
		return this.realizationDate;
	}

	public String getRealizationDateFormated(){
		return formatter.format(this.realizationDate.getTime());
	}

	public Double getPaidAmount() {
		return this.paidAmount;
	}

	public BibliographicProduct getPurchasedProduct(){
		return this.purchasedProduct;
	}

	public String getTransactionInfo(){
		return "\n******************BILL******************\n"+
				"Date of purchse : "+getRealizationDateFormated()+"\n"+
				"Paid amount: "+paidAmount+"\n"+
				"PurchasedProduct: "+purchasedProduct.getName()+
				"\n****************************************\n";
	}

}