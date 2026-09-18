import repository.MemberRepository;

import java.sql.SQLException;

public class Main {
    static void main(String[] args) throws SQLException {

        MemberRepository mr = new MemberRepository();
        System.out.println(mr.findById(3L));
    }
}
