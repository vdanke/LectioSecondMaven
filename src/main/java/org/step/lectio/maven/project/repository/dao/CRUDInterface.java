package org.step.lectio.maven.project.repository.dao;

import org.step.lectio.maven.project.model.User;

import java.sql.SQLException;
import java.util.List;

public interface CRUDInterface {

    boolean save(User user) throws SQLException;

    List<User> findAll() throws SQLException;

    User login(String username, String password) throws SQLException;
}
