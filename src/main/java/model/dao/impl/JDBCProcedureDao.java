package model.dao.impl;

import model.dao.ProcedureDao;
import model.dao.mapper.ProcedureMapper;
import model.entities.Procedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

import static controller.QueryContainer.*;

public class JDBCProcedureDao implements ProcedureDao {
    private Connection connection;
    private ProcedureMapper procedureMapper = new ProcedureMapper();
    private Map<Integer, Procedure> procedures = new HashMap<>();

    public JDBCProcedureDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Procedure entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PROCEDURE)) {
            statement.setLong(1, entity.getPrice());
            statement.setInt(2, entity.getTime());

            statement.execute();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Procedure> findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_PROCEDURE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            Procedure procedure = procedureMapper.extractFromResultSet(resultSet);
            procedureMapper.makeUnique(procedures, procedure);

            resultSet.close();
            close();
            return Optional.of(procedure);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Procedure> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_PROCEDURES)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Procedure procedure = procedureMapper.extractFromResultSet(resultSet);
                procedureMapper.makeUnique(procedures, procedure);
            }
            resultSet.close();
            close();
            return new ArrayList<>(procedures.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Procedure entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PROCEDURE)) {
            statement.setLong(1, entity.getPrice());
            statement.setInt(2, entity.getTime());

            statement.setInt(3, entity.getId());

            statement.execute();
            close();
        } catch (SQLException e) {
            System.out.println("Unable to update procedure!");
        }
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
