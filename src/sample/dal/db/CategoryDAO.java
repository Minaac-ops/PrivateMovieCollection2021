package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.be.Category;

import java.io.IOException;
import java.sql.*;
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
    public Category addCategory (String name) throws SQLServerException {
        try (Connection con = databaseconnector.getConnection()) {
            String sql = "INSERT INTO Category(Name)VALUES (?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
