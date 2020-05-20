package org.step.lectio.maven.project.controller;

import org.step.lectio.maven.project.model.User;
import org.step.lectio.maven.project.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthController extends HttpServlet {

    private final UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User userAfterLogin = userServiceImpl.login(username, password);
        HttpSession session = req.getSession();

        if (req.getCookies() != null && req.getCookies().length != 0) {
            Cookie cookie = new Cookie("usernameInCookie", userAfterLogin.getUsername());
            cookie.setComment("This cookie is storing username of user after successful login");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600);
            cookie.setSecure(true);
//            cookie.setValue();

            resp.addCookie(cookie);
        } else {
            session.setAttribute("userInSession", userAfterLogin);
        }
//        session.setMaxInactiveInterval(600);
//        session.invalidate();

        resp.sendRedirect("/index.jsp");
    }
}
