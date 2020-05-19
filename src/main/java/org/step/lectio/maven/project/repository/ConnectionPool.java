package org.step.lectio.maven.project.repository;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection();

    void releaseConnection(Connection connection);

    void init();
}
