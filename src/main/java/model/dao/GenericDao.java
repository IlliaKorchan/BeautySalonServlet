package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {

    void create(T entity);

    Optional<T> findById(int id);

    List<T> findAll();

    void update(T entity);

    default void delete(Connection connection, String query, int id) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void close();
}
