package org.step.lectio.maven.project;

import org.step.lectio.maven.project.repository.dao.UserDAO;

public class CustomApplicationContext implements ApplicationContext<UserDAO> {

    @Override
    public UserDAO getBean(String name) {
        return new UserDAO();
    }
}
