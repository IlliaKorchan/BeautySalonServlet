package model.services.impl;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.exceptions.IncorrectDataInputException;
import model.exceptions.IncorrectPasswordException;
import model.services.UserUpdater;
import org.mindrot.jbcrypt.BCrypt;

import static string.containers.RegexContainer.LOGIN_PASSWORD_REGEX;
import static string.containers.StringContainer.INCORRECT_PASSWORD_WARNING;
import static string.containers.StringContainer.PASSWORD_INCORRECT;

public class UserUpdateService implements UserUpdater {
    public void updatePassword(User user, String currentPassword, String newPassword)
                                                    throws IncorrectPasswordException, IncorrectDataInputException {
        if (!BCrypt.checkpw(currentPassword, user.getPassword())) {
            throw new IncorrectPasswordException(INCORRECT_PASSWORD_WARNING);
        } else {
            UserDao userDao = DaoFactory.getInstance().createUserDao();

            checkByRegex(newPassword);
            Integer id = userDao.findByLoginAndPassword(user.getLogin(),
                    BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())).getId();

            DaoFactory.getInstance().createUserDao().updatePassword(id, newPassword);
            userDao.close();
        }
    }

    private void checkByRegex(String password) throws IncorrectDataInputException {
        if (!password.matches(LOGIN_PASSWORD_REGEX)) {
            throw new IncorrectDataInputException(PASSWORD_INCORRECT);
        }
    }
}
