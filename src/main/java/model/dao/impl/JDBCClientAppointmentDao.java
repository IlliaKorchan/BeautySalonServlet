package model.dao.impl;

import model.dao.ClientAppointmentDao;
import model.dao.mapper.ClientAppointmentMapper;
import model.entities.ClientAppointment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for processing queries to table "appointments" joined with data from tables "procedures" and "users"
 * @author Illia Korchan
 * @version 0.7.0
 */
public class JDBCClientAppointmentDao implements ClientAppointmentDao {
    private Connection connection;
    private ClientAppointmentMapper clientAppointmentMapper = new ClientAppointmentMapper();
    private Map<Integer, ClientAppointment> clientAppointments = new HashMap<>();

    public JDBCClientAppointmentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ClientAppointment entity) {

    }

    @Override
    public ClientAppointment findById(int id) {
        return null;
    }

    @Override
    public List<ClientAppointment> findAll() {
        return null;
    }

    @Override
    public void update(ClientAppointment entity) {

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
     * Method for searching appointments by client id
     * @param id
     * @param query
     * @return list of appointments found
     */
    @Override
    public List<ClientAppointment> findByClientId(Integer id, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClientAppointment clientAppointment = clientAppointmentMapper.extractFromResultSet(resultSet);
                clientAppointmentMapper.makeUnique(clientAppointments, clientAppointment);
            }
            resultSet.close();

            return new ArrayList<>(clientAppointments.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for searching appointments by master id and date of appointment
     * @param id
     * @param query
     * @return list of appointments found
     */
    @Override
    public List<ClientAppointment> findByMasterIdAndDate(Integer id, LocalDate date, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setDate(2, Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClientAppointment appointment = clientAppointmentMapper.extractFromResultSet(resultSet);
                clientAppointmentMapper.makeUnique(clientAppointments, appointment);
            }
            resultSet.close();
            return new ArrayList<>(clientAppointments.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
