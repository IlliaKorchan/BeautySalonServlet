package model.services;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.exceptions.LoginAlreadyExistsException;

import java.util.Objects;

public class UserRegistrationService {
    public void register(User user) throws LoginAlreadyExistsException {
        UserDao userDao = DaoFactory.getInstance().createUserDao();

        if (Objects.nonNull(userDao.findByLogin(user.getLogin()))) {
            throw new LoginAlreadyExistsException();
        }

        DaoFactory.getInstance().createUserDao().create(user);
    }
}
