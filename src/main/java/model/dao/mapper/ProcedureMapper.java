package model.dao.mapper;

import model.entities.Procedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProcedureMapper implements ObjectMapper<Procedure> {
    @Override
    public Procedure extractFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("procedure_id");
        String nameUkr = rs.getString("procedure_name_ukr");
        String nameEn = rs.getString("procedure_name_en");
        Long price = rs.getLong("procedure_price");
        Integer time = rs.getInt("procedure_time");

        return new Procedure(id, nameUkr, nameEn, price, time);

    }

    @Override
    public Procedure makeUnique(Map<Integer, Procedure> cache, Procedure procedure) {
        cache.putIfAbsent(procedure.getId(), procedure);

        return cache.get(procedure.getId());
    }
}
