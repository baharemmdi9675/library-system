package entity;

public class Loan {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Boolean activeLoan;

    public Loan(Integer userId, Integer bookId, Boolean activeLoan) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.activeLoan = activeLoan;
    }

    public Loan(Integer id, Integer userId, Integer bookId, Boolean activeLoan) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.activeLoan = activeLoan;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Boolean getActiveLoan() {
        return activeLoan;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setActiveLoan(Boolean activeLoan) {
        this.activeLoan = activeLoan;
    }
}
