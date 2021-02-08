package org.wahlzeit_revisited.db.repository;

import org.wahlzeit.services.SysLog;
import org.wahlzeit_revisited.db.DatabaseConnection;
import org.wahlzeit_revisited.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserReadRepository extends ReadRepository<User> {

    /*
     * ReadRepository contract
     */

    @Override
    protected User doFindById(DatabaseConnection dbc, Long id) throws SQLException {
        String query = "SELECT * FROM user WHERE id == ?";
        SysLog.logQuery(query);

        User result = null;
        try (PreparedStatement stmt = dbc.getReadingStatement(query)) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    result = parseRow(resultSet);
                }
            }
        }

        return result;
    }

    @Override
    protected List<User> doFindAll(DatabaseConnection dbc) throws SQLException {
        String query = "SELECT * FROM user";
        SysLog.logQuery(query);

        List<User> result = new ArrayList<>();
        try (PreparedStatement stmt = dbc.getReadingStatement(query)) {

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    User foundUser = parseRow(resultSet);
                    result.add(foundUser);
                }
            }
        }

        return result;
    }

    /*
     * business methods
     */

    protected Optional<User> findByUserNamePassword(String username, String hashedPassword) throws SQLException {
        assertIsNonNullArgument(username);
        assertIsNonNullArgument(hashedPassword);

        DatabaseConnection dbc = getDatabaseConnection();

        String query = "SELECT * FROM user WHERE name == ? AND password == ?";
        SysLog.logQuery(query);

        User result = null;
        try (PreparedStatement stmt = dbc.getReadingStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    result = parseRow(resultSet);
                }
            }
        }

        return Optional.ofNullable(result);

    }

    /*
     * helper methods
     */

    private User parseRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        // String nameAsTag = resultSet.getString(3);
        String emailAddress = resultSet.getString(4);
        String password = resultSet.getString(5);

        return new User(id, name, emailAddress, password);
    }

}
