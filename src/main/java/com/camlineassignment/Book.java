package com.camlineassignment;

public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private int availableCopies;

    public Book(String title, String author, String isbn, int availableCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void decrementAvailableCopies() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    public void incrementAvailableCopies() {
        availableCopies++;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (ISBN: %s, available: %d)", title, author, isbn, availableCopies);
    }
}
