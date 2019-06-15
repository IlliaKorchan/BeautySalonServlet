package model.dao.impl;

import model.dao.ClientAppointmentDtoDao;
import model.dao.mapper.ClientAppointmentMapper;
import model.entities.ClientAppointmentDto;

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
public class JDBCClientAppointmentDtoDao implements ClientAppointmentDtoDao {
    private Connection connection;
    private ClientAppointmentMapper clientAppointmentMapper = new ClientAppointmentMapper();
    private Map<Integer, ClientAppointmentDto> clientAppointments = new HashMap<>();

    public JDBCClientAppointmentDtoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ClientAppointmentDto entity) {

    }

    @Override
    public ClientAppointmentDto findById(int id) {
        return null;
    }

    @Override
    public List<ClientAppointmentDto> findAll() {
        return null;
    }

    @Override
    public void update(ClientAppointmentDto entity) {

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
    public List<ClientAppointmentDto> findByClientId(Integer id, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClientAppointmentDto clientAppointment = clientAppointmentMapper.extractFromResultSet(resultSet);
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
    public List<ClientAppointmentDto> findByMasterIdAndDate(Integer id, LocalDate date, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setDate(2, Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClientAppointmentDto appointment = clientAppointmentMapper.extractFromResultSet(resultSet);
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
