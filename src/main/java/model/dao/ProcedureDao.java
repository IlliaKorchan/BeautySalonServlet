package model.dao;

import model.entities.Procedure;

public interface ProcedureDao extends GenericDao<Procedure> {
    Procedure findByName(String name, String query);
}
