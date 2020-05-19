package org.step.lectio.maven.project.repository;

import java.util.ResourceBundle;

public class DatabaseResourceManager {

    private ResourceBundle database;

    DatabaseResourceManager() {
        this.database = ResourceBundle.getBundle("db");
    }

    public String getValue(String key) {
        return database.getString(key);
    }
}
