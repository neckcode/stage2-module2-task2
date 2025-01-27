package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = null;
        if (req.getSession() != null) {
            user = req.getSession().getAttribute("user");
        }
        if (user == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/user/hello.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("login");
        Optional<String> loggedUser = Optional.empty();
        if (user != null) {
            loggedUser = Users.getInstance().getUsers().stream().filter(user::equals).findFirst();
        }

        String password = req.getParameter("password");

        if (loggedUser.isPresent() && password != null && password.length() != 0) {
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/user/hello.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
