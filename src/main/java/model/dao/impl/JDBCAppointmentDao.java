package model.dao.impl;

import model.dao.AppointmentDao;
import model.dao.mapper.AppointmentMapper;
import model.entities.Appointment;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static string.containers.QueryContainer.*;

/**
 * Class for processing queries for table "appointments" from beauty_salon db
 * @author Illia Korchan
 * @version 0.7.0
 */
public class JDBCAppointmentDao implements AppointmentDao {
    private Connection connection;
    private AppointmentMapper appointmentMapper = new AppointmentMapper();
    private Map<Integer, Appointment> appointments = new HashMap<>();

    JDBCAppointmentDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method for inserting new appointment
     * @param entity
     */
    @Override
    public void create(Appointment entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_APPOINTMENT)) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getMasterId());
            statement.setDate(3, Date.valueOf(entity.getDate()));
            statement.setTime(4, Time.valueOf(entity.getTime()));
            statement.setInt(5, entity.getProcedureId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method, for searching appointment by id
     * @param id
     * @return appointment found entity
     */
    @Override
    public Appointment findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_APPOINTMENT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Appointment appointment = null;

            if (resultSet.next()) {
                appointment = appointmentMapper.extractFromResultSet(resultSet);
            }

            if (Objects.nonNull(appointment)) {
                appointmentMapper.makeUnique(appointments, appointment);
            }
            resultSet.close();

            return appointment;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for fetching all appointments from the table
     * @return list of all appointments in db
     */
    @Override
    public List<Appointment> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_APPOINTMENTS)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = appointmentMapper.extractFromResultSet(resultSet);
                appointmentMapper.makeUnique(appointments, appointment);
            }
            resultSet.close();
            return new ArrayList<>(appointments.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for updating appointments
     * @param entity
     */
    @Override
    public void update(Appointment entity) { }

    /**
     * Method for deleting appointment by id
     * @param id
     */
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
     * Method, that search for appointments by date of appointment and master
     * @param id of user
     * @param date of appointment
     * @param query to get data
     * @return list of appointments found
     */
    @Override
    public List<Appointment> findByMasterIdAndDate(Integer id, LocalDate date, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setDate(2, Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = appointmentMapper.extractFromResultSet(resultSet);
                appointmentMapper.makeUnique(appointments, appointment);
            }
            resultSet.close();
            return new ArrayList<>(appointments.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method, that search for appointments by date, time of appointment and master
     * @param id
     * @param date
     * @param time
     * @return list of appointments found
     */
    @Override
    public Appointment findByMasterIdAndDateAndTime(Integer id, LocalDate date, LocalTime time) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_APPOINTMENT_BY_MASTER_ID_DATE_TIME)) {
            statement.setInt(1, id);
            statement.setDate(2, Date.valueOf(date));
            statement.setTime(3, Time.valueOf(time));

            ResultSet resultSet = statement.executeQuery();

            Appointment appointment = null;

            if (resultSet.next()) {
                appointment = appointmentMapper.extractFromResultSet(resultSet);
            }

            if (Objects.nonNull(appointment)) {
                appointmentMapper.makeUnique(appointments, appointment);
            }
            resultSet.close();

            return appointment;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
