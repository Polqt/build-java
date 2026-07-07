package model;

import java.time.LocalDate;

public class HoldRequest {
    private final Book book;
    private final Borrower borrower;
    private final LocalDate requestedDate;

    public HoldRequest( Book book, Borrower borrower, LocalDate requestedDate) {
        this.book = book;
        this.borrower = borrower;
        this.requestedDate = requestedDate;
    }

    public Book getBook() {
        return book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

}
