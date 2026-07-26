package com.camlineassignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {

    private LibraryRepository repo;

    @BeforeEach
    void setUp() {
        repo = new LibraryRepository();
    }

    @Test
    void getBooksReturnsSeededBooks() {
        Map<String, Book> books = repo.getBooks();

        assertNotNull(books);
        assertEquals(3, books.size());
        assertTrue(books.containsKey("9780134685991"));
    }

    @Test
    void getMembersReturnsSeededMembers() {
        Map<String, Member> members = repo.getMembers();

        assertNotNull(members);
        assertEquals(3, members.size());
        assertTrue(members.containsKey("M001"));
    }

    @Test
    void getCheckoutsReturnsSeededCheckouts() {
        Map<String, List<String>> checkouts = repo.getCheckouts();

        assertNotNull(checkouts);
        assertEquals(3, checkouts.size());
        assertTrue(checkouts.get("M001").contains("9780134685991"));
    }

    @Test
    void memberExistsChecksKnownAndUnknownIds() {
        assertTrue(repo.memberExists("M001"));
        assertFalse(repo.memberExists("M999"));
    }

    @Test
    void bookExistsChecksKnownAndUnknownIds() {
        assertTrue(repo.bookExists("9780134685991"));
        assertFalse(repo.bookExists("0000000000000"));
    }

    @Test
    void checkoutSucceedsForValidMemberAndBook() {
        boolean result = repo.checkout("M002", "9781617294945");

        assertTrue(result);
        assertTrue(repo.getCheckouts().get("M002").contains("9781617294945"));
    }

    @Test
    void checkoutFailsForUnknownMemberOrBook() {
        assertFalse(repo.checkout("M999", "9781617294945"));
        assertFalse(repo.checkout("M001", "0000000000000"));
    }

    @Test
    void checkoutFailsWhenNoCopiesRemain() {
        String bookId = "9781492056270";
        while (repo.getBooks().get(bookId).getAvailableCopies() > 0) {
            repo.getBooks().get(bookId).decrementAvailableCopies();
        }

        assertFalse(repo.checkout("M002", bookId));
    }

    @Test
    void returnBookSucceedsWhenBookIsCheckedOutByMember() {
        String memberId = "M002";
        String bookId = "9781617294945";
        assertTrue(repo.checkout(memberId, bookId));

        boolean returned = repo.returnBook(memberId, bookId);

        assertTrue(returned);
        assertFalse(repo.getCheckouts().get(memberId).contains(bookId));
    }

    @Test
    void returnBookFailsWhenBookWasNotCheckedOut() {
        assertFalse(repo.returnBook("M003", "9781617294945"));
        assertFalse(repo.returnBook("M999", "9781617294945"));
    }

    @Test
    void checkedOutByMemberReturnsBookNames() {
        List<String> bookNames = repo.checkedOutByMember("M001");

        assertEquals(1, bookNames.size());
        assertEquals("Effective Java", bookNames.get(0));
    }

    @Test
    void checkedOutByMemberReturnsEmptyListForUnknownMember() {
        List<String> bookNames = repo.checkedOutByMember("M999");

        assertTrue(bookNames.isEmpty());
    }

    @Test
    void copiesOfBookAvailableReturnsCountOrZero() {
        assertEquals(3, repo.copiesOfBookAvailable("9780134685991"));
        assertEquals(0, repo.copiesOfBookAvailable("0000000000000"));
    }
}