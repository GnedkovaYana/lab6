package org.example.servlet;

import org.example.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;


@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    private final DBService dbService = new DBService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = (String)request.getSession().getAttribute("login");
        if (dbService.getUser(login) == null) {
            response.sendRedirect("/");
            return;
        }


        String filePath = request.getParameter("path");

        if (filePath == null) {
            return;
        }

        File file = new File(filePath);

        if (!file.exists()) {
            return;
        }

        response.setContentLength((int) file.length());

        response.setHeader("Content-Disposition", "attachment; filename=\""
                + file.getName() + "\"");


        FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[4096];

        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }
}