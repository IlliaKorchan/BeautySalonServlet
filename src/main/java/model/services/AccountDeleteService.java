package model.services;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class AccountDeleteService {
    public void delete(User user) {
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        Integer id = userDao.findByLogin(user.getLogin()).getId();
        userDao.delete(id);
        userDao.close();
    }
}
