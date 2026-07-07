package service;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryCatalog {
    private final List<Book> books = new ArrayList<>();

    // Note: leaks mutable internal list — same issue as Borrower.getBorrowedBooks(), callers can mutate catalog without going through addBook/removeBook
    public List<Book> getBooks() {
        return List.copyOf(books);
    }

    public void addBook(Book book) {
        if (books.contains(book)) throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already in catalog.");
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
