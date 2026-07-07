package service;

import model.Book;
import model.Borrower;
import model.Loan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private static final int LOAN_PERIOD_DAYS = 14;
    private static final int MAX_LOANS = 5;

    private final List<Loan> loans = new ArrayList<>();

    public void checkoutBook(Book book, Borrower borrower) {
        if (borrower.getBorrowedBooks().size() >= MAX_LOANS) {
            throw new IllegalStateException(
                    borrower.getName() + " already has " + MAX_LOANS + " books. Limit reached."
            );
        }

        book.checkout();
        borrower.borrowBook(book);

        LocalDate today = LocalDate.now();
        loans.add(new Loan(book, borrower, today, today.plusDays(LOAN_PERIOD_DAYS)));
    }

    /**
     * Returns a book from the borrower back to the shelf.
     *
     * @param borrower the member returning the book
     * @param book the title being returned
     * @throws IllegalStateException if the borrower has no active loan for this book
     */
    public void returnBook(Borrower borrower, Book book) {
        Loan loan = findActiveLoan(borrower, book);
        if (loan == null) throw new IllegalStateException(borrower.getName() + " has no active loan for " + book.getIsbn());

        borrower.returnBook(book);
        book.returnCopy();
        loan.markReturned();
    }

    public List<Loan> getLoans() {
        return List.copyOf(loans);
    }

    private Loan findActiveLoan(Borrower borrower, Book book) {
        for (Loan loan : loans) {
            if (!loan.isReturned() && loan.getBorrower().equals(borrower) && loan.getBook().equals(book)) {
                return loan;
            }
        }
        return null;
    }
}
