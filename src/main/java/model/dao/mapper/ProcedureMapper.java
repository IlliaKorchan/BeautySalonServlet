package model.dao.mapper;

import model.entities.Procedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProcedureMapper implements ObjectMapper<Procedure> {
    @Override
    public Procedure extractFromResultSet(ResultSet rs) throws SQLException {
        Procedure procedure = new Procedure();

        procedure.setId(rs.getInt("procedure_id"));
        procedure.setPrice(rs.getLong("procedure_price"));
        procedure.setTime(rs.getInt("procedure_time"));

        return procedure;
    }

    @Override
    public Procedure makeUnique(Map<Integer, Procedure> cache, Procedure procedure) {
        cache.putIfAbsent(procedure.getId(), procedure);
        return cache.get(procedure.getId());
    }
}
