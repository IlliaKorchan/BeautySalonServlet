package model.services;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.exceptions.IncorrectPasswordException;

public class UserUpdateService {
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
