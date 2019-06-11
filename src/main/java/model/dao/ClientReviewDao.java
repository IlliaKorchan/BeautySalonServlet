package model.dao;

import model.entities.ClientReviewDto;

import java.util.List;

public interface ClientReviewDao extends GenericDao<ClientReviewDto> {
    List<ClientReviewDto> findReviewsByClientId(Integer id, String query);
}
