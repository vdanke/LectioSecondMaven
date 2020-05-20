package org.step.lectio.maven.project.controller;

import org.step.lectio.maven.project.model.User;
import org.step.lectio.maven.project.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

/*
URI - /main
 */
public class MainController extends HttpServlet {

    private final UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().write("This is first response");
        List<User> all = userServiceImpl.findAll();
        String username = "";

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("userInSession");

        Cookie[] cookies = req.getCookies();

        Optional<Cookie> usernameInCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equalsIgnoreCase("usernameInCookie"))
                .findAny();

        if (usernameInCookie.isPresent()) {
            username = usernameInCookie.get().getValue();
        } else {
            username = userInSession.getUsername();
        }

        req.setAttribute("users", all);
        req.setAttribute("username", username);

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

        boolean isSaved = userServiceImpl.save(user);

        System.out.println(isSaved);

        if (isSaved) {
            resp.sendRedirect("/index.jsp");
        } else {
            throw new IllegalArgumentException("User is not created");
        }
    }
}
