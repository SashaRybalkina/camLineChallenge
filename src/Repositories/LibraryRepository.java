package Repositories;

import model.Book;
import model.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Repository for managing library books, members, and checkouts.
//It is only concerned with manipulating data, not validation.
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
        books.put("9780134685991",
                new Book("Effective Java", "Joshua Bloch", "9780134685991", 3));

        books.put("9781617294945",
                new Book("Spring in Action", "Craig Walls", "9781617294945", 2));

        books.put("9781492056270",
                new Book("Designing Data-Intensive Applications", "Martin Kleppmann", "9781492056270", 1));
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

    public Boolean memberExists(String memberId) {
        return members.containsKey(memberId);
    }

    public Boolean bookExists(String bookId) {
        return books.containsKey(bookId);
    }

    public Boolean checkout(String memberId, String bookId) {
        try {
            if (!checkouts.containsKey(memberId)) {
                checkouts.put(memberId, new java.util.ArrayList<>());
            }
            checkouts.get(memberId).add(bookId);

            books.get(bookId).decrementAvailableCopies();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean returnBook(String memberId, String bookId) {
        try {
            if (checkouts.containsKey(memberId) && checkouts.get(memberId).contains(bookId)) {
                checkouts.get(memberId).remove(bookId);

                books.get(bookId).incrementAvailableCopies();

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, List<String>> checkedOutByMember(String memberId) {
        if (checkouts.containsKey(memberId)) {
            return Map.of(memberId, checkouts.get(memberId));
        } else {
            return Map.of();
        }
    }

    public int copiesOfBookAvailable(String bookId) {
        if (books.containsKey(bookId)) {
            return books.get(bookId).getAvailableCopies();
        } else {
            return 0;
        }
    }
}