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

    public List<Movie> getAllCatmovie(int catMovId) throws SQLException {
        List<Movie> allCatmovies = new ArrayList<>();
        try (Connection con = connectionPool.checkOut()) {
            String query = "SELECT * FROM CatMovie INNER JOIN Movie ON CatMovie.MovieId = Movie.MovieId WHERE CatMovie.CategoryId = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, catMovId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("MovieId"), rs.getString("Name"), rs.getInt("Year"), rs.getString("Path"), rs.getInt("Duration"), rs.getFloat("Rating"), rs.getInt("LastView"));
                movie.setCatMovId(rs.getInt("id"));
                allCatmovies.add(movie);

            }
        }
        return allCatmovies;
    }
}
