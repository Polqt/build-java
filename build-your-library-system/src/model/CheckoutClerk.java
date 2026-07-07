package model;

import service.LibrarySystem;

// Note (DRY/SOLID): CheckoutClerk and Administrator both just forward to a service with no shared role interface —
// duplicate thin-wrapper pattern; also model package depends on service package (coupling direction questionable)
public class CheckoutClerk extends Person {
    public CheckoutClerk(int id, String name) {
        super(id, name);
    }

    public void checkoutBook(LibrarySystem system, Book book, Borrower borrower) {
        system.checkoutBook(book, borrower);
    }

    public void returnBook(LibrarySystem system, Book book,  Borrower borrower) {
        system.returnBook(borrower, book);
    }
}