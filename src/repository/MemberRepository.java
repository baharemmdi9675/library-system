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
            return extractMember(resultSet);

//            if (resultSet.next()) {
//                long memberId = resultSet.getLong("id");
//                String name = resultSet.getString("name");
//                return new Member(memberId, name);
//            }
//
//            return null;
        }
    }

    public Member findByUsername(String username) throws SQLException {

        String sql = "SELECT id, username from member where username = ?";

        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return extractMember(resultSet);
        }


    }

    public Member extractMember(ResultSet rs) throws SQLException {

        if (rs.next()) {
            Long userid = rs.getLong("id");
            String memberUsername = rs.getString("username");
            return new Member(userid, memberUsername);
        }
        return null;
    }
}
