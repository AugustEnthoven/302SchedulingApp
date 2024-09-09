package org.codecrafters.educa;

import org.codecrafters.educa.db.DatabaseConnection;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getInstance();
    }
}