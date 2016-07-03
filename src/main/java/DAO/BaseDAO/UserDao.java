package DAO.BaseDAO;

import domain.users.User;

import java.util.List;

/**
 * Created by Yura on 03.07.2016.
 */

//CRUD operations
public interface UserDao {
    /**
     * Returns User @return
     */
    User create();

    User read(String login);

    void update(User user);

    void delete(User user);

    List<User> getAll();
}
