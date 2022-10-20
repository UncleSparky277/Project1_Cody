package dev.cruz.smoketests;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTests {
        public ConnectionTests() {
        }

        @Test
        void connection_available() throws SQLException {
            Connection connection = ConnectionFactory.getConnection();
            System.out.println(connection);
            Assertions.assertNotNull(connection);
        }
    }


