


package repository;

import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseRepository<T, ID> {
    protected abstract String insertQuery();
    protected abstract void setPS(T entity, PreparedStatement ps) throws SQLException;

    public void save(T t) {

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = insertQuery(); //"Insert INTO member (username,email) VALUES (?,?)"
            PreparedStatement ps = connection.prepareStatement(sql);
// ps.setString(1, member.getUsername());
// ps.setString(2, member.getEmail());
            setPS(t, ps);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}




