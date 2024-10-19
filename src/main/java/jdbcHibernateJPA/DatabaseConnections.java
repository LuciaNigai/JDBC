package jdbcHibernateJPA;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatabaseConnections {
    public static  final String h2JDBCUrl = "jdbc:h2:tcp://localhost/~/test";
    public  static  final String h2Username = "sa";
    public static final String h2Password = "";

    public static  final String oracleJDBCUrl = "jdbc:oracle:thin:@//localhost:1521/my_pdb";
    public  static  final String oracleUsername = "LUCY";
    public static final String oraclePassword = "my_password";



    public static DataSource getOracleDataSource(){
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(DatabaseConnections.oracleJDBCUrl);
        ds.setUsername(DatabaseConnections.oracleUsername);
        ds.setPassword(DatabaseConnections.oraclePassword);
        return ds;
    }

    public static DataSource getH2DataSource(){
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(DatabaseConnections.h2JDBCUrl);
        ds.setUsername(DatabaseConnections.h2Username);
        ds.setPassword(DatabaseConnections.h2Password);
        return ds;
    }
}
