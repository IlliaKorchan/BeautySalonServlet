package model.services;

import model.dto.UserDto;

import java.util.List;

public interface MasterFinder {
    List<UserDto> findAll(String language);
}
