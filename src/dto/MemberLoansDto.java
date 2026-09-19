package dto;

import entity.Loan;

//public class MemberLoansDto {
//    private Long memberId;
//    private String memberUsername;
//    private String titleBook;
//}

public record MemberLoansDto(Long memberId, String memberUsername, String titleBook) {
}
