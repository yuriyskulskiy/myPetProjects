package DAO.BaseDaoTT;

import domain.users.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Þðà on 03.07.2016.
 */
//TODO this is just half-made mock-class for dao factory
public class UserDaoImpl implements UserDao {

    Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create() {
        return null;
    }

    @Override
    public User read(String login) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> selectAll() throws SQLException {
        return null;
    }
}
