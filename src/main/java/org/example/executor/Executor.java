package org.example.executor;
import java.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
public class Executor {

    private final Connection connection;
    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update, String login, String password, String email) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(update);
        stmt.setString(1, login);
        stmt.setString(2, password);
        stmt.setString(3,email);
        stmt.executeUpdate();
        stmt.close();
    }

    public <T> T execQuery(String query, ResultHandler<T> handler, String login)  throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, login);
        ResultSet result = stmt.executeQuery();
        T value = handler.handle(result);
        result.close();
        return value;
    }
}
