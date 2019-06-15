package model.services;

import model.entities.UserDto;

import java.util.List;

public interface MasterFinder {
    List<UserDto> findAll(String language);
}
