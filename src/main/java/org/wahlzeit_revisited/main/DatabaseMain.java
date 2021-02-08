package org.wahlzeit_revisited.main;

import java.util.Optional;

public class DatabaseMain extends ModelMain {

    private static final String ROOT_DIR = "web";
    private static final String DB_HOST = Optional.ofNullable(System.getenv("WAHLZEIT_DB_HOST")).orElse("localhost");

    public static void initDatabase() throws Exception {
        new DatabaseMain().startUp(ROOT_DIR, DB_HOST);
    }
}
