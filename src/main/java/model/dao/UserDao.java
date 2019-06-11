package model.dao;

import model.entities.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByLogin(String login);
    User findBySurname(String surname, String query);
    User findByLoginAndPassword(String login, String password);
    void updatePassword(Integer id, String newPassword);
    List<User> findByRole(String role);
}
