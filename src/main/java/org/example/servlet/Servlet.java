package org.example.servlet;

import org.example.service.DBService;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/files")
public class Servlet extends HttpServlet {
    private final DBService dbService = new DBService();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String login = (String) request.getSession().getAttribute("login");
        if (dbService.getUser(login) == null) {
            response.sendRedirect("/");
            return;
        }

        String path = request.getParameter("path");
        if (path == null || path.equals("D:\\filemanager")) {
            path = "D:\\filemanager\\"+ login;
        }
        if(!path.startsWith("D:\\filemanager\\" + login)){
            path = "D:\\filemanager\\" + login;
        }

        File file = new File(path);
        File[] files = file.listFiles(File::isFile);
        File[] directories = file.listFiles(File::isDirectory);

        request.setAttribute("files", files);
        request.setAttribute("directories", directories);
        request.setAttribute("path", path);

        if (path.equals("D:\\filemanager\\" + login)) {
            request.setAttribute("previousPath", path);
        }
        else{
            request.setAttribute("previousPath", (new File(path)).getParent());
        }
        request.getRequestDispatcher("servlet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.getSession().removeAttribute("login");
        resp.sendRedirect("/");
    }
}