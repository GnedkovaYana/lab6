package org.example.service;

import org.example.model.UserProfile;

import java.sql.*;
public class DBService {
    private final Connection connection;

    public DBService() {
        connection = getPostgressConnection();
    }
    public UserProfile getUser(String login) {
        try {
            return new UsersDAO(connection).get(login);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addUser(UserProfile user) {
        try {
            new UsersDAO(connection).insertUser(user.getLogin(), user.getPassword(), user.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getPostgressConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/users_DB";
            return DriverManager.getConnection(url, "postgres", "241115263602");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return  null;
    }
}