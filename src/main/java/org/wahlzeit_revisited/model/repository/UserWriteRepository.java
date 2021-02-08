package org.wahlzeit_revisited.model.repository;


import org.wahlzeit.services.SysLog;
import org.wahlzeit_revisited.database.DatabaseConnection;
import org.wahlzeit_revisited.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserWriteRepository extends WriteRepository<User> {

    /*
     * WriteRepository contract
     */

    @Override
    protected User doInsert(DatabaseConnection dbc, User toInsert) throws SQLException {
        String query = "INSERT INTO users (name, password, email_address, creation_time) VALUES(?, ?, ?, ?) RETURNING id";
        SysLog.logQuery(query);

        PreparedStatement stmt = dbc.getReadingStatement(query);
        stmt.setString(1, toInsert.getName());
        stmt.setString(2, toInsert.getPassword());
        stmt.setString(3, toInsert.getEmail());
        stmt.setLong(4, toInsert.getCreationTime());

        // Extract id from RETURNING statement
        Long persistedId = null;
        try (ResultSet returningSet = stmt.executeQuery()) {
            if (returningSet.next()) {
                persistedId = returningSet.getLong(1);
            }
        }

        // Update id from database
        toInsert.setId(persistedId);
        return toInsert;
    }

    @Override
    protected User doUpdate(DatabaseConnection dbc, User toUpdate) throws SQLException {
        // TODO:
        return toUpdate;
    }

    @Override
    protected User doDelete(DatabaseConnection dbc, User toDelete) throws SQLException {
        // TODO:
        return toDelete;
    }

}
