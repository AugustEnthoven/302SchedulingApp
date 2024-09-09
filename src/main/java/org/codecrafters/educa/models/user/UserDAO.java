package org.codecrafters.educa.models.user;

import org.codecrafters.educa.db.DatabaseConnection;

import java.sql.*;

public class UserDAO {
    private final Connection connection;
    // JDBC Connection
    public UserDAO() {connection = DatabaseConnection.getInstance(); }

    // Create table
    public void createUsersTable(){
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS Users ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "email VARCHAR NOT NULL UNIQUE, "
                            + "role VARCHAR NOT NULL"
                            + "username VARCHAR NOT NULL, "
                            + "password VARCHAR NOT NULL, "
                            + ")"
            );
        } catch (SQLException exception) {
            System.err.println(exception);
        }
    }
    // Add new user to the table
    public void addUser(String email, String role, String username, String password){
        try {
            PreparedStatement addUser = connection.prepareStatement(
              "INSERT INTO Users (email, role, username, password) VALUES (?, ?, ?, ?)"
            );
            addUser.setString(1, email);
            addUser.setString(2, role);
            addUser.setString(3, username);
            addUser.setString(4, password);
            addUser.execute();
        } catch (SQLException exception) {
            System.err.println(exception);
        }
    }

    // Find user by email
    public User findByEmail(String email) {
        try {
            PreparedStatement findUser = connection.prepareStatement(
                    "SELECT * FROM Users WHERE email = ?"
            );
            findUser.setString(1, email);
            ResultSet results = findUser.executeQuery();
            if (results.next()){
                return new User(
                        results.getInt("id"),
                        results.getString("email"),
                        results.getString("role"),
                        results.getString("username"),
                        results.getString("password")
                );
            }
        } catch (SQLException exception){
            System.err.println(exception);
        }
        return null;
    }

    // Find user by username
    public User findByUsername(String username) {
        try {
            PreparedStatement findUser = connection.prepareStatement(
                    "SELECT * FROM Users WHERE username = ?"
            );
            findUser.setString(1, username);
            ResultSet results = findUser.executeQuery();
            if (results.next()){
                return new User(
                        results.getInt("id"),
                        results.getString("email"),
                        results.getString("role"),
                        results.getString("username"),
                        results.getString("password")
                );
            }
        } catch (SQLException exception){
            System.err.println(exception);
        }
        return null;
    }
}
