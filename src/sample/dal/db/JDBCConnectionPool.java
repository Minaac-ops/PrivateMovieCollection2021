package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.dal.ObjectPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCConnectionPool extends ObjectPool<Connection> {

    private static JDBCConnectionPool INSTANCE;
    private final MyDBConnector connectionProvider;


    public synchronized static JDBCConnectionPool getInstance() throws IOException, SQLServerException {
        if (INSTANCE == null)
            INSTANCE = new JDBCConnectionPool();
        return INSTANCE;
    }

    private JDBCConnectionPool() throws IOException {
        connectionProvider = new MyDBConnector();
    }

    protected Connection create() throws SQLException {
        try {
            return connectionProvider.getConnection();
        } catch (SQLServerException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean validate(Connection con)
    {
        try {
            return (!con.isClosed());
        } catch (SQLException e)
        {
            e.printStackTrace();
            return (false);
        }
    }

    public void expire(Connection con)
    {
        try {
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
