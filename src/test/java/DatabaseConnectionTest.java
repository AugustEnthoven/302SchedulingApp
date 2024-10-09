import org.codecrafters.educa.db.DatabaseConnection;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseConnectionTest {
    @Test
    void testConnection() {
        Connection connection = DatabaseConnection.getInstance();
        assertTrue(connection != null);
    }
}