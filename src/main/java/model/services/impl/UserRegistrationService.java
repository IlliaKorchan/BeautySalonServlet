package model.services.impl;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.exceptions.LoginAlreadyExistsException;
import model.services.UserRegistration;

import java.util.Objects;

public class UserRegistrationService implements UserRegistration {
    @Override
    public void register(User user) throws LoginAlreadyExistsException {
        UserDao userDao = DaoFactory.getInstance().createUserDao();

        if (Objects.nonNull(userDao.findByLogin(user.getLogin()))) {
            throw new LoginAlreadyExistsException();
        }

        userDao.create(user);
        userDao.close();
    }
}
