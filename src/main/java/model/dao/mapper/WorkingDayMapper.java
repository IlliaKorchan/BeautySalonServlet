package model.dao.mapper;

import model.entities.WorkingDay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

/**
 * Class for processing data, received from table "working_days" after executing queries.
 * @author Illia Korchan
 * @version 0.7.0
 */
public class WorkingDayMapper implements ObjectMapper<WorkingDay> {
    /**
     * Method for fetching data from result set
     * @param rs, that contains data to fetch
     * @return POJO object with fetched data
     * @throws SQLException
     */
    @Override
    public WorkingDay extractFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("working_day_id");
        Integer masterId = rs.getInt("working_day_master_id");
        LocalDate date = rs.getDate("working_day_date").toLocalDate();

        return new WorkingDay(id, masterId, date);
    }

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param workingDay} to {@param cache}
     * if there is no object with the same id
     * @param cache storage of Appointment class instances
     * @param workingDay POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param workingDay}
     */
    @Override
    public WorkingDay makeUnique(Map<Integer, WorkingDay> cache, WorkingDay workingDay) {
        cache.putIfAbsent(workingDay.getId(), workingDay);

        return cache.get(workingDay.getId());
    }
}
