package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
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
     * Method that lets you see the movie in a specific category
     * @param catMovId the ID
     * @return Returns the movies in the specific category
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
}