package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.be.Category;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private MyDBConnector databaseconnector;

    private final JDBCConnectionPool connectionPool;


    public CategoryDAO() throws IOException, SQLServerException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     * Gets a list of all categories from the database.
     *
     * @return The list of all Movies.
     */
    public List<Category> getCategories() throws SQLException {
        List<Category> allCategories = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Category;");
            while (rs.next()) {
                int id = rs.getInt("CatId");
                String name = rs.getString("name");

                Category category = new Category(id, name);
                allCategories.add(category);
            }
        } return allCategories;
    }
}
