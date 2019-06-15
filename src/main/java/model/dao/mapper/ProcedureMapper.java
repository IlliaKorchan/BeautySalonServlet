package model.dao.mapper;

import model.entities.Procedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Class for processing data, received from table "procedures" after executing queries.
 * @author Illia Korchan
 * @version 0.7.0
 */
public class ProcedureMapper implements ObjectMapper<Procedure> {
    /**
     * Method for fetching data from result set
     * @param rs, that contains data to fetch
     * @return POJO object with fetched data
     * @throws SQLException
     */
    @Override
    public Procedure extractFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("procedure_id");
        String nameUkr = rs.getString("procedure_name_ukr");
        String nameEn = rs.getString("procedure_name_en");
        Long price = rs.getLong("procedure_price");

        return new Procedure(id, nameUkr, nameEn, price);

    }

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param procedure} to {@param cache}
     * if there is no object with the same id
     * @param cache storage of Appointment class instances
     * @param procedure POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param procedure}
     */
    @Override
    public Procedure makeUnique(Map<Integer, Procedure> cache, Procedure procedure) {
        cache.putIfAbsent(procedure.getId(), procedure);

        return cache.get(procedure.getId());
    }
}
