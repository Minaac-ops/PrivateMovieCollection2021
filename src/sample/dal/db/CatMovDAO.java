package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.be.Category;
import sample.be.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatMovDAO {

    private final JDBCConnectionPool connectionPool;

    public CatMovDAO() throws IOException, SQLServerException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     * Method that lets you see the movies in a specific category.
     * @param catMovId the ID of the movies in a category.
     * @return Returns the list of movies that are in the chosen category.
     * @throws SQLException
     */
    public List<Movie> getAllCatmovies(int catMovId) throws SQLException {
        List<Movie> newMovieList = new ArrayList<>();
        try (Connection con = connectionPool.checkOut()) {
            String query = "SELECT * FROM CatMovie INNER JOIN Movie ON CatMovie.MovieId = Movie.MovieId WHERE CatMovie.CategoryId = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, catMovId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("CatMovId"), rs.getString("Name"), rs.getInt("Year"), rs.getString("Filelink"), rs.getInt("Duration"), rs.getString("Rating"), rs.getString("LastView"));
                movie.setCatMovId(rs.getInt("CatMovId"));
                newMovieList.add(movie);
            }
        }
        return newMovieList;
    }

    /**
     * Lets you add a category to a movie.
     * @param movie The movie that you want to add a category to.
     * @param category The category that you want on the movie.
     * @throws SQLException
     */
    public void addGenre(Movie movie, Category category) throws SQLException {
        String sql2 = "INSERT INTO CatMovie(CategoryId, MovieId) VALUES(?,?);";
        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)){
            st.setInt(1, category.getID());
            st.setInt(2, movie.getID());

            st.executeUpdate();
        }
    }
}