package model.dao;

import model.entities.ReviewDto;

import java.util.List;

public interface ReviewDtoDao extends GenericDao<ReviewDto> {
    List<ReviewDto> findAllById(Integer id, String query);
}
