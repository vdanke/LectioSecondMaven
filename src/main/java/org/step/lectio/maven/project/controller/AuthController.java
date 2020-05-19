package org.step.lectio.maven.project.controller;

import org.step.lectio.maven.project.model.User;
import org.step.lectio.maven.project.repository.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User userAfterLogin = userDAO.login(username, password);

        HttpSession session = req.getSession();

        session.setAttribute("userInSession", userAfterLogin);

        resp.sendRedirect("/index.jsp");
    }
}
