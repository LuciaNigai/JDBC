package jdbcHibernateJPA.jdbcH2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
//    private static final Logger LOGGER = LogManager.getLogger();
    private static final Logger LOGGER =  LoggerFactory.getLogger(JDBC.class);


    public static void main(String[] args) {
        DataSource dataSource = getDataSource();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            LOGGER.info("Connection is valid: {}", connection.isValid(10));
//  CRUD
//            select
            Statement ps1 = connection.createStatement();
            ResultSet rs1 = ps1.executeQuery("select * from users");
            while (rs1.next()) {
                int id = rs1.getInt("id");
                String name = rs1.getString("name");
                LOGGER.info("{} - {}", id, name);
            }


            PreparedStatement ps2 = connection.prepareStatement("Select * from users where name = ? ");
            ps2.setString(1, "Vlad");
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                int id = rs2.getInt("id");
                String name = rs2.getString("name");
                LOGGER.info("{} - {}", id, name);
            }

//            insert
            PreparedStatement insertPS = connection.prepareStatement("insert into users(name) values(?)");
            insertPS.setString(1, "Cristi");
            int insertCount = insertPS.executeUpdate();
            LOGGER.info("Inserted {} rows",insertCount);


//            update
            PreparedStatement updatePS = connection.prepareStatement("update users set name = ? where name = ?");
            updatePS.setString(1, "Oleg");
            updatePS.setString(2, "Cristi");
            int updateCount = updatePS.executeUpdate();
            LOGGER.info("Updated {} rows",updateCount);

//            delete

            PreparedStatement deletePd = connection.prepareStatement("Delete from users where name = ?");
            deletePd.setString(1,"Oleg");
            int count = deletePd.executeUpdate();
            LOGGER.info("Deleted {} rows",count);
            
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }

        }

    }

    private static DataSource getDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:users.sql';");
        return ds;
    }
}
