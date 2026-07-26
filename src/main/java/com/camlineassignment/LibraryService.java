package com.camlineassignment;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private final LibraryRepository repo;

    public LibraryService(LibraryRepository repo) {
        this.repo = repo;
    }

    public List<Member> getMembers() {
        return new ArrayList<>(repo.getMembers().values());
    }

    public List<Book> getBooks() {
        return new ArrayList<>(repo.getBooks().values());
    }

    public String checkout(String memberId, String bookId) {
        if (!repo.memberExists(memberId)) {
            return "Member not found.";
        }
        if (!repo.bookExists(bookId)) {
            return "Book not found.";
        }
        return repo.checkout(memberId, bookId) ? "Checkout successful." : "Checkout failed.";
    }

    public String returnBook(String memberId, String bookId) {
        if (!repo.memberExists(memberId)) {
            return "Member not found.";
        }
        if (!repo.bookExists(bookId)) {
            return "Book not found.";
        }
        return repo.returnBook(memberId, bookId) ? "Book returned successfully." : "Return failed.";
    }

    public List<String> checkedOutByMember(String memberId) {
        if (!repo.memberExists(memberId)) {
            return new ArrayList<>();
        }
        return repo.checkedOutByMember(memberId);
    }

    public String copiesOfBookAvailable(String bookId) {
        if (!repo.bookExists(bookId)) {
            return "Book not found.";
        }
        return "Available copies: " + repo.copiesOfBookAvailable(bookId);
    }
}
