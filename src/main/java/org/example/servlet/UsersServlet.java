package org.example.servlet;

import org.example.model.UserProfile;
import org.example.service.DBService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/registration")
public class UsersServlet extends HttpServlet {
    private final DBService dbService = new DBService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (login.isEmpty() || email.isEmpty() || password.isEmpty()) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Отсутствует значение одного или нескольких полей");
            return;
        }

        UserProfile user = new UserProfile(login, password, email);
        dbService.addUser(user);

        File directory = new File("D:/filemanager/"+login);
        if (!directory.mkdir()) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Пользователь с таким логином уже существует");
            return;
        }

        resp.sendRedirect("/files");
    }
}