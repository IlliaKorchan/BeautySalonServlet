package model.dao;

import model.entities.User;

public interface UserDao extends GenericDao<User> {
//    Optional<User> findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
}
