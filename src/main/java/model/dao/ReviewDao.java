package model.dao;

import model.entities.Review;

import java.util.List;

public interface ReviewDao extends GenericDao<Review> {
    List<Review> findById(Integer id, String query);
}
