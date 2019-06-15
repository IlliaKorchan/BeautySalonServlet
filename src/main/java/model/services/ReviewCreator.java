package model.services;

public interface ReviewCreator {
    void sendReview(Integer clientId, Integer masterId, String reviewText);
}
