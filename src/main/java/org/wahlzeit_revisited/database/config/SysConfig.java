/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit_revisited.database.config;

import jakarta.inject.Singleton;

import java.io.File;

/**
 * A basic set of system configuration data
 */
@Singleton
public class SysConfig extends AbstractConfig {

    /**
     * Database driver definitions
     */

    public static final String DB_DRIVER = "DB_DRIVER";
    public static final String DB_CONNECTION = "DB_CONNECTION";
    public static final String DB_USER = "DB_USER";
    public static final String DB_PASSWORD = "DB_PASSWORD";

    /**
     * base directories
     */
    protected String rootDir;

    /**
     * Config directories
     */
    protected ConfigDir scriptsDir;
    protected ConfigDir staticDir;
    protected ConfigDir templatesDir;

    /**
     * Data directories
     */
    protected Directory photosDir;
    protected Directory backupDir;
    protected Directory tempDir;

    /**
     * Constructor
     */

    public SysConfig(String myRootDir, String dbHostName) {
        // Root directory
        rootDir = myRootDir;

        // Config directories
        scriptsDir = new ConfigDir(rootDir, "config" + File.separator + "scripts");
        staticDir = new ConfigDir(rootDir, "config" + File.separator + "static");
        templatesDir = new ConfigDir(rootDir, "config" + File.separator + "templates");

        // Data directories
        photosDir = new Directory(rootDir, "data" + File.separator + "photos");
        backupDir = new Directory(rootDir, "data" + File.separator + "backup");
        tempDir = new Directory(rootDir, "data" + File.separator + "temp");

        // Database connection
        doSetValue(SysConfig.DB_DRIVER, "org.postgresql.Driver");
        doSetValue(SysConfig.DB_CONNECTION, "jdbc:postgresql://" + dbHostName + ":5432/wahlzeit");
        doSetValue(SysConfig.DB_USER, "wahlzeit");
        doSetValue(SysConfig.DB_PASSWORD, "wahlzeit");
    }

    /**
     * getter
     */

    public String getRootDirAsString() {
        return rootDir;
    }

    public ConfigDir getStaticDir() {
        return staticDir;
    }

    public ConfigDir getScriptsDir() {
        return scriptsDir;
    }

    public ConfigDir getTemplatesDir() {
        return templatesDir;
    }

    public Directory getPhotosDir() {
        return photosDir;
    }

    public Directory getBackupDir() {
        return backupDir;
    }

    public Directory getTempDir() {
        return tempDir;
    }

    public String getDbDriverAsString() {
        return getValue(SysConfig.DB_DRIVER);
    }

    public String getDbConnectionAsString() {
        return getValue(SysConfig.DB_CONNECTION);
    }

    public String getDbUserAsString() {
        return getValue(SysConfig.DB_USER);
    }

    public String getDbPasswordAsString() {
        return getValue(SysConfig.DB_PASSWORD);
    }

}
