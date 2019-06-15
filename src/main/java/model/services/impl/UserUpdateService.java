package model.services.impl;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.exceptions.IncorrectPasswordException;
import model.services.UserUpdater;

public class UserUpdateService implements UserUpdater {
    public void updatePassword(User user, String currentPassword, String newPassword)
                                                    throws IncorrectPasswordException {
        if (!user.getPassword().equals(currentPassword)) {
            throw new IncorrectPasswordException();
        } else {
            UserDao userDao = DaoFactory.getInstance().createUserDao();
            Integer id = userDao.findByLoginAndPassword(user.getLogin(), user.getPassword()).getId();
            DaoFactory.getInstance().createUserDao().updatePassword(id, newPassword);
            userDao.close();
        }
    }
}
