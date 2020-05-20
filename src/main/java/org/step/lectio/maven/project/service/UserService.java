package org.step.lectio.maven.project.service;

import org.step.lectio.maven.project.model.User;

import java.util.List;

public interface UserService {

    boolean save(User user);

    List<User> findAll();

    User login(String username, String password);
}
