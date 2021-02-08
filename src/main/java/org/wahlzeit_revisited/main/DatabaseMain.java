package org.wahlzeit_revisited.main;

import org.wahlzeit.services.SysLog;

import java.sql.SQLException;
import java.util.Optional;

public class DatabaseMain extends ModelMain {

    private static final String ROOT_DIR = "web";
    private static final String DB_HOST = Optional.ofNullable(System.getenv("WAHLZEIT_DB_HOST")).orElse("localhost");

    public void startUp() throws Exception {
        startUp(ROOT_DIR, DB_HOST);
    }

    public void shutDown() {
        try {
            saveAll();
            SysLog.logSysInfo("Shutting down database");
        } catch (SQLException sqlException) {
            SysLog.logThrowable(sqlException);
        }
    }
}
