package model;

public interface IBuy {
    
    public boolean purchaseBook(Book book, Transaction bill);

    public boolean suscribeMagazine(Magazine magazine, Transaction bill);

    public boolean eliminateBook(Book book, Transaction bill);

    public boolean cancelMagazineSubscription(Magazine magazine, Transaction bill);
}
