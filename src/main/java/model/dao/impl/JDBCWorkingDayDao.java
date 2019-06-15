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

/**
 * Class for processing queries for table "working_days" from beauty_salon db
 * @author Illia Korchan
 * @version 0.7.0
 */
public class JDBCWorkingDayDao implements WorkingDayDao {
    private Connection connection;
    private WorkingDayMapper workingDayMapper = new WorkingDayMapper();
    private Map<Integer, WorkingDay> workingDays = new HashMap<>();

    JDBCWorkingDayDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method for inserting new working day
     * @param entity that contains data to insert
     */
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

    /**
     * Method for fetching all working days from the table
     * @return list of all working days in db
     */
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

    /**
     * Method for closing connection to db
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for searching for working days of user by id
     * @param id of user to find working days
     * @return list of working days found
     */
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

    /**
     * Method that executes query from statement and fetches working days data from result set.
     * Method to eliminate duplicate code
     * @param statement to execute
     * @return list of working days found by executing query
     */
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
