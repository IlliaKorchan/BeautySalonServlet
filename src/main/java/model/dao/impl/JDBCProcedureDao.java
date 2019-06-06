package model.dao.impl;

import model.dao.ProcedureDao;
import model.dao.mapper.ProcedureMapper;
import model.entities.Procedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static controller.QueryContainer.CREATE_PROCEDURE;
import static controller.QueryContainer.FIND_ALL_PROCEDURES;
import static controller.QueryContainer.UPDATE_PROCEDURE;
import static controller.QueryContainer.FIND_PROCEDURE_BY_ID;


public class JDBCProcedureDao implements ProcedureDao {
    private Connection connection;
    private ProcedureMapper procedureMapper = new ProcedureMapper();
    private Map<Integer, Procedure> procedures = new HashMap<>();

    JDBCProcedureDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Procedure entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PROCEDURE)) {
            statement.setLong(1, entity.getPrice());
            statement.setInt(2, entity.getTime());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Procedure findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_PROCEDURE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            Procedure procedure = procedureMapper.extractFromResultSet(resultSet);

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
    public void update(Procedure entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PROCEDURE)) {

            statement.setLong(1, entity.getPrice());
            statement.setInt(2, entity.getTime());
            statement.setInt(3, entity.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
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
