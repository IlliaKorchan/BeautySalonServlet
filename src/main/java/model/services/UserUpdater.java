package model.services;

import model.entities.User;
import model.exceptions.IncorrectPasswordException;

public interface UserUpdater {
    void updatePassword(User user, String currentPassword, String newPassword)
            throws IncorrectPasswordException;
}
