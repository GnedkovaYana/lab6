package org.example.service;

import  org.example.executor.Executor;
import org.example.model.UserProfile;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection){
        this.executor = new Executor(connection);
    }

    public UserProfile get(String login) throws SQLException{
        return executor.execQuery("select * from users where login=?", result -> {
            result.next();
            return new UserProfile(result.getString("login"), result.getString("password"), result.getString("email"));
        }, login);
    }

    public void insertUser(String login, String password, String email) throws  SQLException{
        executor.execUpdate("insert into users (login, password, email) values (?, ?, ?)", login, password, email);
    }
}