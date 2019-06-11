package model.services;

import model.dao.DaoFactory;
import model.entities.User;

import java.util.List;

public class MasterFinder {
    public List<User> findAll() {
        return DaoFactory.getInstance().createUserDao().findByRole("master");
    }
}
