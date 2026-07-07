package model;

import service.LibraryCatalog;

import java.util.List;

public class Administrator extends Person {
    public Administrator(int id, String name) {
        super(id, name);
    }

    public void addBook(LibraryCatalog catalog, Book book) {
        catalog.addBook(book);
    }

    public void removeBook(LibraryCatalog catalog, Book book) {
        catalog.removeBook(book);
    }
}
