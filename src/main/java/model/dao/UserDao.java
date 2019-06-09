package model.dao;

import model.entities.User;

public interface UserDao extends GenericDao<User> {
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    void updatePassword(Integer id, String newPassword);
}
