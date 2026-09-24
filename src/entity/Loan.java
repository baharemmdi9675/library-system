package entity;

public class Loan {
    private Long id;
    private Long userId;
    private Long bookId;
    private Boolean activeLoan;

    public Loan(Long userId, Long bookId, Boolean activeLoan) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.activeLoan = activeLoan;
    }

    public Loan(Long id, Long userId, Long bookId, Boolean activeLoan) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.activeLoan = activeLoan;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public Boolean getActiveLoan() {
        return activeLoan;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setActiveLoan(Boolean activeLoan) {
        this.activeLoan = activeLoan;
    }
}
