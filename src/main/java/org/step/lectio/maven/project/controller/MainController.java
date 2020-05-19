package org.step.lectio.maven.project.controller;

import org.step.lectio.maven.project.model.User;
import org.step.lectio.maven.project.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
URI - /main
 */
public class MainController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().write("This is first response");
        List<User> all = userService.findAll();

        req.setAttribute("users", all);

//        resp.sendRedirect("/main.jsp");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username + " " + password);

        long id = new Random().nextLong();

        User user = new User(id, username, password);

        boolean isSaved = userService.save(user);

        System.out.println(isSaved);

        if (isSaved) {
            resp.sendRedirect("/index.jsp");
        } else {
            throw new IllegalArgumentException("User is not created");
        }
    }
}
