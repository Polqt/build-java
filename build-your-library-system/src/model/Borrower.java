package model;

import java.util.ArrayList;
import java.util.List;

public class Borrower extends Person {
    private final List<Book> borrowedBooks = new ArrayList<>();

    public Borrower(int id, String name) {
        super(id, name);
    }

    /**
     * Returns the books this borrower currently holds.
     *
     * <p>Note: this exposes the internal mutable list — callers can add or
     * remove books without going through {@link #borrowBook(Book)}. See
     * mentor notes on defensive copies / unmodifiable views.
     *
     * @return live list of currently borrowed books
     */
    public List<Book> getBorrowedBooks() {
        return List.copyOf(borrowedBooks);
    }

    /**
     * Records that this borrower took a copy of the given book.
     *
     * @param book the book being borrowed
     */
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    /**
     * Records that this borrower gave back the given book.
     *
     * <p>Note: silently does nothing if the book was never borrowed —
     * {@code List.remove} returns a boolean that is currently ignored.
     *
     * @param book the book being returned
     */
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}
