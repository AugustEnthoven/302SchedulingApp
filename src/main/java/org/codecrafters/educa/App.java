package org.codecrafters.educa;

import com.password4j.Password;
import org.codecrafters.educa.db.DatabaseConnection;
import org.codecrafters.educa.models.authentication.Authentication;
import org.codecrafters.educa.models.user.User;
import org.codecrafters.educa.models.user.UserDAO;

import java.sql.Connection;

public class App {
    private static UserDAO userdao;
    private static Authentication authenticated;

    public static UserDAO getUserDAO(){ return userdao; }
    public static Authentication getAuthenticated() { return authenticated; }
    public static void main(String[] args) {
        // Initialisations
        Connection connection = DatabaseConnection.getInstance();
        authenticated = new Authentication();

        // Query database for users table
        userdao = new UserDAO();
        userdao.createUsersTable();
        userdao.addUser("N/A", "N/A", "username", Password.hash("N/A").withArgon2().getResult());

    }
}