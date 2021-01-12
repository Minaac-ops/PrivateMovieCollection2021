package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.be.Category;
import sample.be.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    CatMovDAO catMovDAO = new CatMovDAO();

    private final JDBCConnectionPool connectionPool;


    public CategoryDAO() throws IOException, SQLServerException {
        connectionPool = JDBCConnectionPool.getInstance();
        catMovDAO = new CatMovDAO();
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
                List<Movie> allMovies = catMovDAO.getAllCatmovies(id);
                category.setMovieList(allMovies);
                allCategories.add(category);
            }
        } return allCategories;
    }

    /**
     * Adds a category to the list in the database
     * @param name Name of the category
     * @return
     */
    public Category createCategory(String name)
    {
        String sql = "INSERT INTO Category(Name) VALUES(?);";
        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, name);
            st.addBatch();
            st.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Category category = new Category(0, name);
        return category;
    }

    /**
     * Deleting a category from the database
     * @param categoryToDelete The category to delete
     */
    public void deleteCategory(Category categoryToDelete) {
        try (Connection con = connectionPool.checkOut()) {
            String query = "DELETE FROM Category WHERE CatId =?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, categoryToDelete.getID());
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
