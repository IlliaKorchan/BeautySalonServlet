package model.dao.impl;

import model.dao.WorkingDayDao;
import model.dao.mapper.WorkingDayMapper;
import model.entities.WorkingDay;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static string.containers.QueryContainer.CREATE_WORKING_DAY;
import static string.containers.QueryContainer.FIND_ALL_WORKING_DAYS;
import static string.containers.QueryContainer.FIND_WORKING_DAYS_BY_MASTER_ID;

public class JDBCWorkingDayDao implements WorkingDayDao {
    private Connection connection;
    private WorkingDayMapper workingDayMapper = new WorkingDayMapper();
    private Map<Integer, WorkingDay> workingDays = new HashMap<>();

    JDBCWorkingDayDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(WorkingDay entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_WORKING_DAY)) {
            statement.setInt(1, entity.getMasterId());
            statement.setDate(2, Date.valueOf(entity.getDate()));

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WorkingDay findById(int id) {
        return null;
    }

    @Override
    public List<WorkingDay> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_WORKING_DAYS)) {
           return findWorkingDaysList(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(WorkingDay entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public List<WorkingDay> findByMasterId(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_WORKING_DAYS_BY_MASTER_ID)) {
            statement.setInt(1, id);
            return findWorkingDaysList(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<WorkingDay> findWorkingDaysList(PreparedStatement statement) {
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                WorkingDay workingDay = workingDayMapper.extractFromResultSet(resultSet);
                workingDayMapper.makeUnique(workingDays, workingDay);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(workingDays.values());
    }
}
