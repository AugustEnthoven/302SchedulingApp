package org.codecrafters.educa.db;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseConnectionTest {
    @Test
    void testConnection() {
        Connection connection = DatabaseConnection.getInstance();
        assertEquals(true, connection != null);
    }
}