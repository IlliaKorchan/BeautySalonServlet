package model.dao;

import model.entities.Review;

public interface ReviewDao extends GenericDao<Review> {
    Review findByClientId(int clientId);
    Review findByMasterId(int masterId);
}
