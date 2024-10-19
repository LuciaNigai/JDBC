package jdbcHibernateJPA.oracle_database_connection;

import java.sql.Connection;
import javax.sql.DataSource;

import jdbcHibernateJPA.DatabaseConnections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConnection.class);

    public static void main(String[] args) {
        DataSource ds = DatabaseConnections.getOracleDataSource();
        try (Connection conn = ds.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
