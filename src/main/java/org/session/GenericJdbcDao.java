package org.session;

import lombok.RequiredArgsConstructor;
import org.session.exception.ConnectionException;

import javax.sql.DataSource;
import java.sql.SQLException;

@RequiredArgsConstructor
public class GenericJdbcDao {
    private final DataSource dataSource;

    public <T> T load(EntityKey<T> key){
        try (var connection = dataSource.getConnection()) {
        } catch (SQLException e) {
            throw new ConnectionException("Cannot open database connection", e);
        }
        return null;
    }
}
