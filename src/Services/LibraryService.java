public class LibraryService {
    private LibraryRepository repo;

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

    public Boolean checkout(String memberId, String bookId) {
        if (!repo.memberExists(memberId)) {
            return false;
        }
        if (!repo.bookExists(bookId)) {
            return false;
        }
        return repo.checkout(memberId, bookId);
    }

    public Boolean returnBook(String memberId, String bookId) {
        if (!repo.memberExists(memberId)) {
            return false;
        }
        if (!repo.bookExists(bookId)) {
            return false;
        }
        return repo.returnBook(memberId, bookId);
    }

    public List<String> checkedOutByMember(String memberId) {
        if (!repo.memberExists(memberId)) {
            return List.of();
        }
        return repo.checkedOutByMember(memberId).getOrDefault(memberId, List.of());
    }

    public int copiesOfBookAvailable(String bookId) {
        if (!repo.bookExists(bookId)) {
            return 0;
        }
        return repo.copiesOfBookAvailable(bookId);
    }
}
