package model.dao.mapper;

import model.entities.Procedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProcedureMapper implements ObjectMapper<Procedure> {
    @Override
    public Procedure extractFromResultSet(ResultSet rs) throws SQLException {

        if (rs.next()) {
            String name = rs.getString("procedure_name");
            Integer id = rs.getInt("procedure_id");
            Long price = rs.getLong("procedure_price");
            Integer time = rs.getInt("procedure_time");

            return new Procedure(id,name, price, time);
        }
        return null;
    }

    @Override
    public Procedure makeUnique(Map<Integer, Procedure> cache, Procedure procedure) {
        cache.putIfAbsent(procedure.getId(), procedure);

        return cache.get(procedure.getId());
    }
}
