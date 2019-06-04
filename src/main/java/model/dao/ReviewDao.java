package model.dao;

import model.entities.Review;

import java.util.Optional;

public interface ReviewDao extends GenericDao<Review> {
    Optional<Review> findByClientId(int clientId);
    Optional<Review> findByMasterId(int masterId);
}
