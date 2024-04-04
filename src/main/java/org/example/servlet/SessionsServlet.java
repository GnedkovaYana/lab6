package org.example.servlet;

import org.example.model.UserProfile;
import org.example.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class  SessionsServlet extends HttpServlet {
    private final DBService dbService = new DBService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("log.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserProfile user = dbService.getUser(login);
        if(user == null || !user.getPassword().equals(password)){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Неправильный логин или пароль.");
            return;
        }
        req.getSession().setAttribute("login", login);
        resp.sendRedirect("files?path=D:/filemanager/"+login);;
    }
}