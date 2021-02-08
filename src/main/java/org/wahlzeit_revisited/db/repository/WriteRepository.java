package org.wahlzeit_revisited.db.repository;


import org.wahlzeit_revisited.db.DatabaseConnection;
import org.wahlzeit_revisited.db.Persistent;
import org.wahlzeit_revisited.db.SessionManager;

import java.sql.*;

public abstract class WriteRepository<T extends Persistent> extends Repository<T> {

    /*
     * template methods
     */

    protected abstract T doInsert(DatabaseConnection dbc, T toInsert) throws SQLException;

    protected abstract T doUpdate(DatabaseConnection dbc, T toUpdate) throws SQLException;

    protected abstract T doDelete(DatabaseConnection dbc, T toDelete) throws SQLException;

    /*
     * business methods
     */

    public T insert(T toInsert) throws SQLException {
        assertIsNonNullArgument(toInsert);
        assertNonPersistedObject(toInsert);

        DatabaseConnection conn = SessionManager.getDatabaseConnection();
        T result = doInsert(conn, toInsert);

        assertPersistedObject(result);
        return result;
    }

    public T update(T toUpdate) throws SQLException {
        assertIsNonNullArgument(toUpdate);
        assertPersistedObject(toUpdate);

        DatabaseConnection conn = SessionManager.getDatabaseConnection();
        T result = doUpdate(conn, toUpdate);

        assertPersistedObject(result);
        return result;
    }

    public T delete(T toDelete) throws SQLException {
        assertIsNonNullArgument(toDelete);
        assertPersistedObject(toDelete);

        DatabaseConnection conn = SessionManager.getDatabaseConnection();
        T result = doDelete(conn, toDelete);

        assertNonPersistedObject(result);
        return result;
    }


}
