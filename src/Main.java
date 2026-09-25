import dto.MemberLoansDto;
import entity.Loan;
import entity.Member;
import repository.LoanRepository;
import repository.MemberRepository;
import service.MemberService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static void main(String[] args) throws SQLException {

        MemberRepository mr = new MemberRepository();
        MemberService memberService = new MemberService(mr);

//        Scanner scanner=new Scanner(System.in);
//        String username=scanner.next();
//
//        memberService.register(username);

//        memberService.register("bahar","bahar@gamil.com");
//        memberService.register("ali");
//        memberService.deleteMember("ali");
//        memberService.deleteMember("ali");
//        memberService.deleteMember("bahar");
//        LoanRepository loanRepository = new LoanRepository();
//        loanRepository.save(new Loan(1L, 12L, true));
//        MemberLoansDto memberLoansDto = new MemberLoansDto(1L, "bahar", "crime and punishment");
//

    }

}
