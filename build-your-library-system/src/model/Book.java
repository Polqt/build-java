package model;

import java.util.Objects;

/**
 * Represents a book title in the library catalog and tracks how many
 * physical copies are available for checkout.
 *
 * <p>This class owns its own inventory rules: callers must not decrement
 * or increment copy counts directly, they go through {@link #checkout()}
 * and {@link #returnCopy()}.
 */
public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final int totalCopies;
    private int availableCopies;

    /**
     * Creates a book with a fixed identity (ISBN, title, author) and an
     * initial copy count.
     *
     * @param isbn unique identifier of the title
     * @param title display title of the book
     * @param author author of the book
     * @param totalCopies number of physical copies the library owns
     * @param availableCopies copies currently on the shelf; must not exceed {@code totalCopies}
     */
    public Book(String isbn, String title, String author, int totalCopies, int availableCopies) {
        if (totalCopies < 0) throw new IllegalArgumentException("Total copies cannot be less than 0");
        if (availableCopies < 0 || availableCopies > totalCopies)
            throw new IllegalArgumentException("Available copies must be between 0 and totalCopies");

        this.isbn = Objects.requireNonNull(isbn, "ISBN is required");
        this.title = Objects.requireNonNull(title, "Title is required");
        this.author = Objects.requireNonNull(author, "Author is required");
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    /**
     * Checks whether at least one copy is on the shelf.
     *
     * @return {@code true} if a copy can be checked out right now
     */
    public boolean isAvailable() {
        return availableCopies > 0;
    }

    /**
     * Removes one copy from the shelf for a borrower.
     *
     * @throws IllegalStateException if no copies are available
     */
    public void checkout() {
        if (!isAvailable()) throw new IllegalStateException("No copies available.");
        availableCopies--;
    }

    /**
     * Puts one copy back on the shelf.
     *
     * <p>Note: currently unguarded — calling this more times than
     * {@link #checkout()} pushes {@code availableCopies} above
     * {@code totalCopies}. See mentor notes.
     */
    public void returnCopy() {
        if (availableCopies >= totalCopies) throw new IllegalStateException("All copies are already returned.");
        availableCopies++;
    }

    /**
     * Prints a human-readable inventory line to standard output.
     *
     * <p>Note: printing from a model class mixes presentation with domain
     * logic (SRP). See mentor notes for the {@code toString()} alternative.
     */
    // Note (SRP/KISS): fine as toString, but MainLibrary calls System.out.println(book) — presentation format baked into model, no separate formatter
    @Override
    public String toString() {
        return title + " by " + author + " | Available: " + availableCopies + "/" + totalCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
