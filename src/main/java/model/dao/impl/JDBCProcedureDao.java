package model.dao.impl;

import model.dao.ProcedureDao;
import model.dao.mapper.ProcedureMapper;
import model.entities.Procedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static string.containers.QueryContainer.*;

/**
 * Class for processing queries for table "procedures" from beauty_salon db
 * @author Illia Korchan
 * @version 0.7.0
 */
public class JDBCProcedureDao implements ProcedureDao {
    private Connection connection;
    private ProcedureMapper procedureMapper = new ProcedureMapper();
    private Map<Integer, Procedure> procedures = new HashMap<>();

    JDBCProcedureDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method for inserting new procedure
     * @param entity
     */
    @Override
    public void create(Procedure entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PROCEDURE)) {
            statement.setString(1, entity.getNameUkr());
            statement.setString(2, entity.getNameEn());
            statement.setLong(2, entity.getPrice());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for searching for procedure by id
     * @param id
     * @return procedure found
     */
    @Override
    public Procedure findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_PROCEDURE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Procedure procedure = null;

            if (resultSet.next()) {
               procedure = procedureMapper.extractFromResultSet(resultSet);
            }

            if (Objects.nonNull(procedure)) {
                procedureMapper.makeUnique(procedures, procedure);
            }
            resultSet.close();

            return procedure;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for fetching all procedures from the table
     * @return list of all procedures in db
     */
    @Override
    public List<Procedure> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_PROCEDURES)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Procedure procedure = procedureMapper.extractFromResultSet(resultSet);
                procedureMapper.makeUnique(procedures, procedure);
            }
            resultSet.close();

            return new ArrayList<>(procedures.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {

    }

    /**
     * Method for updating all procedures from the table
     */
    @Override
    public void update(Procedure entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PROCEDURE)) {

            statement.setLong(1, entity.getPrice());
            statement.setInt(2, entity.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Procedure findByName(String name, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            Procedure procedure = null;

            if (resultSet.next()) {
                procedure = procedureMapper.extractFromResultSet(resultSet);
            }

            if (Objects.nonNull(procedure)) {
                procedureMapper.makeUnique(procedures, procedure);
            }
            resultSet.close();

            return procedure;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
}
