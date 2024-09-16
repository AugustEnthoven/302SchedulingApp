package org.codecrafters.educa.models.user;

public class User {
    private final int id;
    private final String email;
    private final String role;
    private String username;
    private String password;

    public User(int id, String email, String role, String username, String password){
        this.id = id;
        this.email = email;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() {return role; }
    public String getPassword() { return password; }
    public String getUsername() { return username; }

    public void setPassword(String password) throws EmptyPasswordException {
        if (password.isEmpty()) {
            throw new EmptyPasswordException("Password cannot be empty");
        }
        this.password = password;
    }
    public void setUsername(String username) throws EmptyUsernameException {
        if (username.isEmpty()) {
            throw new EmptyUsernameException("Username cannot be empty");
        }
        this.username = username;
    }
}
