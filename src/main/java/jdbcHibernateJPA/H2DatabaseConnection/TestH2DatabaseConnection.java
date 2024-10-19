package jdbcHibernateJPA.H2DatabaseConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdbcHibernateJPA.DatabaseConnections;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class TestH2DatabaseConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestH2DatabaseConnection.class);

    public static void main(String[] args) {
        DataSource dataSource = DatabaseConnections.getH2DataSource();

        try(Connection connection = dataSource.getConnection()) {
            LOGGER.info("Connected to database");
//      creating a table
            String sql = "create table user_roles (ID int primary key, role varchar(55))";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            LOGGER.info("Table created");
//            inserting rows in that table
            sql = "insert into user_roles values (1, 'admin')";
            int rows = statement.executeUpdate(sql);
            if (rows > 0) {
                LOGGER.info("Inserted new Row");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
