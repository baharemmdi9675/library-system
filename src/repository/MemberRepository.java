package repository;

import entity.Member;
import util.ConnectionUtil;

import java.sql.*;

public class MemberRepository {
    public void save(Member member) throws SQLException {

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "Insert INTO member (username) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, member.getUsername());
            ps.execute();
        }
    }

    public Member findById(Long id) throws SQLException {

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "SELECT id, username FROM member WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                long memberId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                return new Member(memberId, name);
            }

            return null;
        }
    }


}
