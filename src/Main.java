import dto.MemberLoansDto;
import entity.Loan;
import entity.Member;
import repository.BookRepository;
import repository.LoanRepository;
import repository.MemberRepository;
import service.BookService;
import service.LibraryService;
import service.LoanService;
import service.MemberService;

import java.lang.classfile.instruction.SwitchCase;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {

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

        LibraryService libraryService = new LibraryService(10, memberService,
                new BookService(new BookRepository()), new LoanService(new LoanRepository()));
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("1 Register member");
            System.out.println("2 Delete account");
            System.out.println("3 Lend book");
            System.out.println("4 Return book");
            System.out.println("5 Exit");

            int choice = input.nextInt();
            input.nextLine();
            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter username: ");
                        String username = input.nextLine();
                        System.out.println("Enter email: ");
                        String email = input.nextLine();
                        Member member = new Member(username, email);
                        libraryService.register(member);
                        System.out.println("Registered successfully");
                        break;
                    case 2:
                        System.out.println("Enter member id: ");
                        int id = input.nextInt();
                        libraryService.deleteAccount(id);
                        System.out.println("Delete account successfully");
                        break;
                    case 3:
                        System.out.println("Enter book id: ");
                        int bookId = input.nextInt();
                        System.out.println("Enter user id: ");
                        int userId = input.nextInt();
                        libraryService.lendBook(bookId, userId);
                        System.out.println("Book lend successfully");
                        break;
                    case 4:
                        System.out.println("Enter book id: ");
                        int newBookId = input.nextInt();
                        libraryService.returnBook(newBookId);
                        System.out.println("Book returned");
                        break;
                    case 5:
                        System.out.println("Bye");
                        input.close();
                        return;
                }
            } catch (RuntimeException e) {
                System.out.println("Error in main block" + e.getMessage());
            }
        }




    }

}
