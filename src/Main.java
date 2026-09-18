import repository.MemberRepository;
import service.MemberService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static void main(String[] args) throws SQLException {

        MemberRepository mr = new MemberRepository();
        MemberService memberService = new MemberService(mr);

        Scanner scanner=new Scanner(System.in);
        String username=scanner.next();

        memberService.register(username);
    }

}
