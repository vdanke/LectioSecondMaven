package org.step.lectio.maven.project.service;

import org.step.lectio.maven.project.model.User;
import org.step.lectio.maven.project.repository.dao.UserDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public boolean save(User user) {
        try {
            return userDAO.save(user);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Something went wrong");
        }
    }

    public List<User> findAll() {
        try {
            return userDAO.findAll();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Something went wrong");
        }
    }

    public User login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username or password is null");
        }
        return userDAO.login(username, password);
    }
}
