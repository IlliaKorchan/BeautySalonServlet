package model.services.impl;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.entities.UserDto;
import model.services.MasterFinder;

import java.util.ArrayList;
import java.util.List;

import static string.containers.StringContainer.LOCALE_UKR;

public class MasterFinderService implements MasterFinder {
    public List<UserDto> findAll(String language) {
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        List<User> masters = userDao.findByRole("master");
        List<UserDto> masterDtos = new ArrayList<>();

        masters.forEach(master -> masterDtos.add(new UserDto(master, language.equals(LOCALE_UKR) ? master.getSurnameUkr()
                                                                                            : master.getSurnameEn())));

        userDao.close();

        return masterDtos;
    }
}
