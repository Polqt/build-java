package service;

import model.Book;
import model.Borrower;
import model.HoldRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HoldRequestOperations {
    private final List<HoldRequest> holds = new ArrayList<>();

    public void placeHold(Book book, Borrower borrower) {
        holds.add(new HoldRequest(book, borrower, LocalDate.now()));
    }

    public void cancelHold(HoldRequest request) {
        holds.remove(request);
    }

    public void fulfillHold(HoldRequest request, LibrarySystem system) {
        system.checkoutBook(request.getBook(), request.getBorrower());
    }
}