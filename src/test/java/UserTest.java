import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.codecrafters.educa.models.user.*;

class UserTest {
    private User user;

    @BeforeEach
    public void setUser() {
        user = new User(1, "lastname", "firstname", "test@testing.com", "teacher", "username", "password");
    }

    // UserID Test
    @Test
    void getId() { assertTrue(user.getId() > 0); }
    // Last name test
    @Test
    void getLastName() { assertEquals("lastname", user.getLastname()); }
    // First name test
    @Test
    void getFirstName() { assertEquals("firstname", user.getFirstname()); }
    // Email Test
    @Test
    void getEmail() { assertEquals("test@testing.com", user.getEmail()); }
    // UserName Tests
    @Test
    void getUsername() { assertEquals("username", user.getUsername()); }
    @Test
    void setUsername() throws EmptyUsernameException {
        user.setUsername("user1");
        assertEquals("user1", user.getUsername());
    }
    @Test
    void setUsernameEmptyString() {
        assertThrows(EmptyUsernameException.class, () -> user.setUsername(""));
    }
    // Password Tests
    @Test
    void getPassword() { assertEquals("password", user.getPassword()); }
    @Test
    void setPassword() throws EmptyPasswordException {
        user.setPassword("password1");
        assertEquals("password1", user.getPassword());
    }
    @Test
    void setPasswordEmptyString() { assertThrows(EmptyPasswordException.class, () -> user.setPassword("")); }

    // Role Test
    @Test
    void getRole() { assertEquals("teacher", user.getRole()); }
}
