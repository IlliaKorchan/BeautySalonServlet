package model.services;

import model.entities.User;
import model.exceptions.LoginAlreadyExistsException;

public interface UserRegistration {
    void register(User user) throws LoginAlreadyExistsException;
}
