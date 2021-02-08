package org.wahlzeit_revisited.db.repository;

import org.wahlzeit_revisited.db.DatabaseConnection;
import org.wahlzeit_revisited.db.Persistent;
import org.wahlzeit_revisited.db.SessionManager;

public abstract class Repository<T extends Persistent> {

    protected DatabaseConnection getDatabaseConnection() {
        return SessionManager.getDatabaseConnection();
    }

    protected void assertNonPersistedObject(T toPersist) {
        if (toPersist.getId() != null) {
            String formatted = String.format("Object '%s' already has an id", toPersist.toString());
            throw new IllegalStateException(formatted);
        }
    }

    protected void assertPersistedObject(T toPersist) {
        if (toPersist.getId() == null) {
            String formatted = String.format("Object '%s' has no id", toPersist.toString());
            throw new IllegalStateException(formatted);
        }
    }

    protected void assertIsNonNullArgument(Object arg) {
        assertIsNonNullArgument(arg, "anonymous");
    }

    protected void assertIsNonNullArgument(Object arg, String label) {
        if (arg == null) {
            throw new IllegalArgumentException(label + " should not be null");
        }
    }
}
