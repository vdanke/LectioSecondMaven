package org.step.lectio.maven.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {

    private static volatile ConnectionPoolImpl instance = null;

    private BlockingQueue<Connection> connectionBlockingQueue;
    private Connection connection;

    private final int poolSize = 5;

    private ConnectionPoolImpl() {
    }

    public static ConnectionPool getInstance() {
        ConnectionPoolImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPoolImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPoolImpl();
                    localInstance.init();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Connection getConnection() {
        Connection newConnection;

        if (connectionBlockingQueue.isEmpty() || connectionBlockingQueue.size() < poolSize) {
            for (int i = 0; i < poolSize - connectionBlockingQueue.size(); i++) {
                connectionBlockingQueue.add(connection);
            }
        }

        try {
            newConnection = connectionBlockingQueue.take();
        } catch (InterruptedException e) {
            newConnection = connectionBlockingQueue.poll();
        }
        return newConnection;
    }

    @Override
    public void releaseConnection(Connection connection) {
        try {
            connectionBlockingQueue.put(connection);
        } catch (InterruptedException e) {
            if (connectionBlockingQueue.size() >= poolSize) {
                connectionBlockingQueue.remove();
            }
            connectionBlockingQueue.offer(connection);
        }
    }

    @Override
    public void init() {
        DatabaseResourceManager databaseResourceManager = new DatabaseResourceManager();

        String url = databaseResourceManager.getValue(DatabaseProperties.DB_URL);
        String username = databaseResourceManager.getValue(DatabaseProperties.DB_USERNAME);
        String password = databaseResourceManager.getValue(DatabaseProperties.DB_PASSWORD);
        String driver = databaseResourceManager.getValue(DatabaseProperties.DB_DRIVER);

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            connectionBlockingQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                connectionBlockingQueue.add(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
