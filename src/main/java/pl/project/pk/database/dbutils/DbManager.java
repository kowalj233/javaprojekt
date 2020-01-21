package pl.project.pk.database.dbutils;


import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.project.pk.database.models.Employee;
import pl.project.pk.database.models.Benefit;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_HD = "jdbc:h2:./databaseDB";
    private static final String USER = "admin";
    private static final String PASS = "admin";

    private static ConnectionSource connectionSource;

    public static void initDatabase(){
        createConnectionDB();
//        dropTable();
        createTable();
        closeConnectionDB();
    }

    private static void createConnectionDB(){
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD, USER, PASS);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null){
            createConnectionDB();
        }

        return connectionSource;
    }

    public static void closeConnectionDB(){
        if(connectionSource != null){
            try {
                connectionSource.close();
            } catch (IOException e){
                LOGGER.warn(e.getMessage());
            }
        }
    }


    private static void createTable(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, Employee.class);
            TableUtils.createTableIfNotExists(connectionSource, Benefit.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private static void dropTable(){
        try {
            TableUtils.dropTable(connectionSource, Employee.class, true);
            TableUtils.dropTable(connectionSource, Benefit.class, true);
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
    }

}
