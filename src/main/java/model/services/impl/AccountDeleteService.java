package model.services.impl;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.services.AccountDelete;

public class AccountDeleteService implements AccountDelete {
    @Override
    public void delete(User user) {
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        Integer id = userDao.findByLogin(user.getLogin()).getId();
        userDao.delete(id);
        userDao.close();
    }
}
