package org.step.lectio.maven.project.repository.dao;

import org.step.lectio.maven.project.model.User;
import org.step.lectio.maven.project.repository.ConnectionPool;
import org.step.lectio.maven.project.repository.ConnectionPoolImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final String FIND_ALL = "SELECT * FROM USERS";
    private static final String SAVE_USER = "INSERT INTO USERS(id, username, password) values (?, ?, ?)";
    private static final String LOGIN_USER = "SELECT * FROM USERS WHERE username = ? AND password = ?";

    private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    public List<User> findAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<User> userList = new ArrayList<>();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(FIND_ALL);

        while (resultSet.next()) {
            userList.add(
                    new User(resultSet.getLong("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password")
                    )
            );
        }
        connectionPool.releaseConnection(connection);
        return userList;
    }

    public boolean save(User user) throws SQLException {
        Connection connection = connectionPool.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER);

        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());

        int executeUpdate = preparedStatement.executeUpdate();

        connectionPool.releaseConnection(connection);

        return executeUpdate != -1;
    }

    public User login(String username, String password) {
        Connection connection = connectionPool.getConnection();
        User user = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
