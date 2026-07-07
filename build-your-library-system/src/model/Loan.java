package model;


import java.time.LocalDate;

public class Loan {
    private final Book book;
    private final Borrower borrower;
    private final LocalDate borrowedDate;
    private LocalDate returnedDate;
    private final LocalDate dueDate;
    private boolean returned;

    public Loan(Book book, Borrower borrower, LocalDate borrowedDate, LocalDate dueDate) {
        this.book = book;
        this.borrower = borrower;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public Book getBook() {
        return book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public boolean isOverdue() {
        return !returned && LocalDate.now().isAfter(dueDate);
    }

    public void markReturned() {
        if (returned) throw new IllegalStateException("Loan is already returned");
        
        returned = true;
        returnedDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return borrower.getName()
                + " borrowed "
                + book.getIsbn()
                + " | Borrowed:  + borrowedDate"
                + " | Due:  + dueDate"
                + " | Returned:  + returnedDate";
    }

}
