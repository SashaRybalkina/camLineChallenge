public class LibraryService {

    private final LibraryRepository repo;

    public LibraryService(LibraryRepository repo) {
        this.repo = repo;
    }

    public List<Member> getMembers() {
        return repo.getMembers();
    }

    public List<Book> getBooks() {
        return repo.getBooks();
    }

    public List<Checkout> getCheckouts() {
        return repo.getCheckouts();
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
            return List.of();
        }

        return repo.checkedOutByMember(memberId).getOrDefault(memberId, List.of());
    }

    public String copiesOfBookAvailable(String bookId) {

        if (!repo.bookExists(bookId)) {
            return "Book not found.";
        }

        return "Available copies: " + repo.copiesOfBookAvailable(bookId);
    }
}