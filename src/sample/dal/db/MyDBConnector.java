package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MyDBConnector {

    private SQLServerDataSource dataSource;

    /**
     * In the PROP_FILE we have saved the login informations to the database.
     */
    private static final String PROP_FILE = "src/sample/dal/database.settings.txt";
    private SQLServerDataSource ds;

    /**
     * The constructor for the class holds the connection to the database.
     * @throws IOException
     */
    public MyDBConnector() throws IOException {
        Properties databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream(new File(PROP_FILE)));

        this.ds = new SQLServerDataSource();
        this.ds.setServerName(databaseProperties.getProperty("server"));
        this.ds.setDatabaseName(databaseProperties.getProperty("database"));
        this.ds.setUser(databaseProperties.getProperty("user"));
        this.ds.setPassword(databaseProperties.getProperty("password"));
    }

    /**
     * We created a getConnection method so we can reuse the connection, so we dont have to create a new connection in
     * all DAO classes.
     * @return
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }

    /**
     * Just a tester to see if the connection is up and running.
     * @param args
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[]args) throws IOException, SQLException {
        MyDBConnector databaseConnector = new MyDBConnector();
        Connection connection = databaseConnector.getConnection();

        System.out.println("Is it open? " + !connection.isClosed());

        connection.close();
    }
}
