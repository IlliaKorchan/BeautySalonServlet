package model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Interface for processing data, received after executing queries.
 * @author Illia Korchan
 * @version 0.7.0
 */
public interface ObjectMapper<T> {
    /**
     * Method for fetching data from result set
     * @param rs, that contains data to fetch
     * @return POJO object with fetched data
     * @throws SQLException
     */
    T extractFromResultSet(ResultSet rs) throws SQLException;

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param entity} to {@param cache}
     * if there is no object with the same id
     * @param cache storage of instances
     * @param entity POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param entity}
     */
    T makeUnique(Map<Integer, T> cache, T entity);
}
