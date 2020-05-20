package org.step.lectio.maven.project.service;

import org.step.lectio.maven.project.model.User;
import org.step.lectio.maven.project.repository.dao.UserDAO;

import java.lang.reflect.Field;

public class Runner {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        Field[] declaredFields = userService.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);

            boolean isPresent = field.isAnnotationPresent(Autowired.class);

            System.out.println(isPresent);

            if (isPresent) {
                Class<?> type = field.getType();

                UserDAO userDAO = getDao(type);

                System.out.println(userDAO);

                try {
                    field.set(userService, userDAO);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        boolean save = userService.save(new User(50L, "vasya", "dupkin"));

        System.out.println(save);
    }

    private static UserDAO getDao(Class<?> type) {
        boolean isAssignableForm = type.isAssignableFrom(UserDAO.class);

        if (isAssignableForm) {
            return new UserDAO();
        }
        return null;
    }
}
