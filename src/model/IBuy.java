package model;

/**
 * The IBuy interface represents the behavior of a user to buy and manage bibliographic products.
 */
public interface IBuy {
    
    /**
     * Purchases a book and adds the transaction to the bill.
     * @param book The book to purchase
     * @param bill The transaction bill to add the purchase to
     * @return true if the purchase is successful, false otherwise
     */
    public boolean purchaseBook(Book book, Transaction bill);

    /**
     * Subscribes to a magazine and adds the transaction to the bill.
     * @param magazine The magazine to subscribe to
     * @param bill The transaction bill to add the subscription to
     * @return true if the subscription is successful, false otherwise
     */
    public boolean suscribeMagazine(Magazine magazine, Transaction bill);

    /**
     * Eliminates a purchased book and adds the transaction to the bill.
     * @param book The book to eliminate
     * @param bill The transaction bill to add the elimination to
     * @return true if the elimination is successful, false otherwise
     */
    public boolean eliminateBook(Book book, Transaction bill);

    /**
     * Cancels a magazine subscription and adds the transaction to the bill.
     * @param magazine The magazine subscription to cancel
     * @param bill The transaction bill to add the cancellation to
     * @return true if the cancellation is successful, false otherwise
     */
    public boolean cancelMagazineSubscription(Magazine magazine, Transaction bill);
}
