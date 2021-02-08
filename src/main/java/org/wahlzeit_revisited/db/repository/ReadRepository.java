package org.wahlzeit_revisited.db.repository;


import org.wahlzeit_revisited.db.DatabaseConnection;
import org.wahlzeit_revisited.db.Persistent;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class ReadRepository<T extends Persistent> extends Repository<T> {

    /*
     * template methods
     */
    protected abstract T doFindById(DatabaseConnection dbc, Long id) throws SQLException;

    protected abstract List<T> doFindAll(DatabaseConnection dbc) throws SQLException;

    /*
     * business methods
     */

    public Optional<T> findById(Long id) throws SQLException {
        assertIsNonNullArgument(id);
        DatabaseConnection dbc = getDatabaseConnection();

        return Optional.of(doFindById(dbc, id));
    }

    public List<T> findAll() throws SQLException {
        DatabaseConnection dbc = getDatabaseConnection();
        return doFindAll(dbc);
    }

}
