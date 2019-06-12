package model.services;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.entities.UserDto;

import java.util.ArrayList;
import java.util.List;

public class MasterFinder {
    public List<UserDto> findAll(String language) {
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        List<User> masters = userDao.findByRole("master");
        List<UserDto> masterDtos = new ArrayList<>();

        masters.forEach(master -> masterDtos.add(new UserDto(master, language.equals("uk") ? master.getSurnameUkr()
                                                                                            : master.getSurnameEn())));

        userDao.close();

        return masterDtos;
    }
}
