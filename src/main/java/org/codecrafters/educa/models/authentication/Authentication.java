package org.codecrafters.educa.models.authentication;

import com.password4j.Password;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.codecrafters.educa.App;
import org.codecrafters.educa.models.user.*;

public class Authentication {
    private static final BooleanProperty isAuthenticated = new SimpleBooleanProperty(false);
    private static final ObjectProperty<User> user = new SimpleObjectProperty<>(null);

    public Authentication(){
        isAuthenticated.set(false);
        user.set(null);
    }
    public boolean isAuthenticated() { return isAuthenticated.get(); }
    public User getUser() { return user.get(); }
    public ObjectProperty<User> userProperty() { return user; }
    public void setUser(User currUser) {
        user.set(currUser);
    }

    public boolean login(String username, String password) throws UnauthorisedUserException {
        UserDAO DAO = App.getUserDAO();

        User emailSearch = DAO.findByEmail(username);
        User usernameSearch = DAO.findByUsername(username);

        if (usernameSearch != null || emailSearch != null) {
            User current = usernameSearch != null ? usernameSearch : emailSearch;
            if (Password.check(password, current.getPassword()).withArgon2()) {
                if(usernameSearch != null) {
                    System.out.println("using username: " + usernameSearch.getUsername());
                } else {
                    System.out.println("using email: " + emailSearch.getUsername());
                }

                isAuthenticated.set(true);
                user.set(usernameSearch != null ? usernameSearch : emailSearch);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void logout() {
        isAuthenticated.set(false);
        user.set(null);
    }

    public void register(String lastname, String firstname, String email, String role, String username, String password) throws ExistingUserException {
        try {
            UserDAO DAO = App.getUserDAO();

            // User lookup
            User emailSearch = DAO.findByEmail(email);
            User usernameSearch = DAO.findByUsername(username);

            // Find existence of email in the database
            if (emailSearch != null) {
                throw new ExistingUserException("That email is already registered.");
            }
            // Find existence of username in the database
            if (usernameSearch != null) {
                throw new ExistingUserException("That username is not available.");
            }

            // Add new user
            DAO.addUser(lastname, firstname, email, role, username, Password.hash(password).withArgon2().getResult());
        } catch (ExistingUserException exception) {
            throw new ExistingUserException(exception.getMessage());
        }
    }
}
