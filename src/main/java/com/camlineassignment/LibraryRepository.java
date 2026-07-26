package com.camlineassignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryRepository {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();
    private final Map<String, List<String>> checkouts = new HashMap<>();

    public LibraryRepository() {
        seedBooks();
        seedMembers();
        seedCheckouts();
    }

    private void seedBooks() {
        books.put("9780134685991", new Book("Effective Java", "Joshua Bloch", "9780134685991", 3));
        books.put("9781617294945", new Book("Spring in Action", "Craig Walls", "9781617294945", 2));
        books.put("9781492056270", new Book("Designing Data-Intensive Applications", "Martin Kleppmann", "9781492056270", 1));
    }

    private void seedMembers() {
        members.put("M001", new Member("Alice Johnson", "M001"));
        members.put("M002", new Member("Bob Smith", "M002"));
        members.put("M003", new Member("Charlie Brown", "M003"));
    }

    private void seedCheckouts() {
        checkouts.put("M001", new ArrayList<>());
        checkouts.put("M002", new ArrayList<>());
        checkouts.put("M003", new ArrayList<>());
        checkouts.get("M001").add("9780134685991");
    }

    public Map<String, Book> getBooks() {
        return books;
    }

    public Map<String, Member> getMembers() {
        return members;
    }

    public Map<String, List<String>> getCheckouts() {
        return checkouts;
    }

    public boolean memberExists(String memberId) {
        return members.containsKey(memberId);
    }

    public boolean bookExists(String bookId) {
        return books.containsKey(bookId);
    }

    public boolean checkout(String memberId, String bookId) {
        if (!memberExists(memberId) || !bookExists(bookId)) {
            return false;
        }
        if (books.get(bookId).getAvailableCopies() <= 0) {
            return false;
        }

        checkouts.computeIfAbsent(memberId, id -> new ArrayList<>()).add(bookId);
        books.get(bookId).decrementAvailableCopies();
        return true;
    }

    public boolean returnBook(String memberId, String bookId) {
        List<String> memberBooks = checkouts.get(memberId);
        if (memberBooks == null || !memberBooks.remove(bookId)) {
            return false;
        }

        books.get(bookId).incrementAvailableCopies();
        return true;
    }

    public List<String> checkedOutByMember(String memberId) {
        List<String> bookIds = checkouts.getOrDefault(memberId, new ArrayList<>());
        List<String> bookNames = new ArrayList<>();

        for (String bookId : bookIds) {
            Book book = books.get(bookId);
            if (book != null) {
                bookNames.add(book.getTitle());
            }
        }

        return bookNames;
    }

    public int copiesOfBookAvailable(String bookId) {
        if (!bookExists(bookId)) {
            return 0;
        }
        return books.get(bookId).getAvailableCopies();
    }
}
