import model.Administrator;
import model.Book;
import model.Borrower;
import model.CheckoutClerk;
import service.LibraryCatalog;
import service.LibrarySystem;

public class MainLibrary {
    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        LibraryCatalog catalog = new LibraryCatalog();
        Administrator admin = new Administrator(
                1,
                "Jane"
        );

        CheckoutClerk clerk = new CheckoutClerk(
                1,
                "John"
        );

        Borrower borrower = new Borrower(1, "Pol");

        Book cleanCode = new Book(
                "9780132350884",
                "Clean Code",
                "Robert C. Martin",
                3,
                3
        );

        Book effectiveJava = new Book(
                "9780134685991",
                "Effective Java",
                "Joshua Bloch",
                2,
                2
        );



        admin.addBook(catalog, cleanCode);
        admin.addBook(catalog, effectiveJava);
        clerk.checkoutBook(librarySystem, cleanCode, borrower);
        clerk.checkoutBook(librarySystem, effectiveJava, borrower);

        System.out.println(cleanCode);
        System.out.println(effectiveJava);

        clerk.returnBook(librarySystem, effectiveJava, borrower);
        System.out.println(effectiveJava);
    }
}