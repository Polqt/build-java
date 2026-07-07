package model;

import service.LibrarySystem;

import java.util.List;

public class Librarian extends Person {
    public Librarian(int id, String name) {
        super(id, name);
    }

    public List<Loan> viewLoans(LibrarySystem system) {
        return system.getLoans();
    }

    public void reportOverdue(LibrarySystem system) {
        for(Loan loan: system.getLoans()) {
            if (loan.isOverdue()) System.out.println(loan);
        }
    }
}


