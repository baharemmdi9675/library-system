package dto;

public record LoanDto (Long id, Long userId, Long bookId, Boolean activeLoan) {
}
