package model.dao.impl;

import model.dao.AppointmentDao;
import model.dao.mapper.AppointmentMapper;
import model.entities.Appointment;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static string.containers.QueryContainer.CREATE_APPOINTMENT;
import static string.containers.QueryContainer.FIND_ALL_APPOINTMENTS;
import static string.containers.QueryContainer.FIND_APPOINTMENT_BY_ID;


public class JDBCAppointmentDao implements AppointmentDao {
    private Connection connection;
    private AppointmentMapper appointmentMapper = new AppointmentMapper();
    private Map<Integer, Appointment> appointments = new HashMap<>();

    JDBCAppointmentDao(Connection connection) {
        this.connection = connection;
    }

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

    @Override
    public void update(Appointment entity) {
//        try (PreparedStatement statement = connection.prepareStatement(UPDATE_APPOINTMENT)) {
//            statement.setInt(1, entity.getUserId());
//            statement.setInt(2, entity.getMasterId());
//            statement.setDate(3, Date.valueOf(entity.getDate()));
//            statement.setTime(4, Time.valueOf(entity.getTime()));
//            statement.setInt(5, entity.getProcedureId());
//
//            statement.setInt(6, entity.getId());
//
//            statement.execute();
//            close();
//        } catch (SQLException e) {
//            System.out.println("Unable to update appointment!");
//        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
