package model.dao.mapper;

import model.entities.WorkingDay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class WorkingDayMapper implements ObjectMapper<WorkingDay> {
    @Override
    public WorkingDay extractFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("working_day_id");
        Integer masterId = rs.getInt("working_day_master_id");
        LocalDate date = rs.getDate("working_day_date").toLocalDate();

        return new WorkingDay(id, masterId, date);
    }

    @Override
    public WorkingDay makeUnique(Map<Integer, WorkingDay> cache, WorkingDay workingDay) {
        cache.putIfAbsent(workingDay.getId(), workingDay);

        return cache.get(workingDay.getId());
    }
}
