package model.dao;

import model.entities.UserReview;

import java.util.List;

public interface UserReviewDao extends GenericDao<UserReview> {
    List<UserReview> findAllById(Integer id, String query);
}
