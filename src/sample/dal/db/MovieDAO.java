package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.be.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO
{

    private MyDBConnector dbConnector;
    private final JDBCConnectionPool connectionPool;

    public MovieDAO() throws IOException, SQLServerException {
        connectionPool = JDBCConnectionPool.getInstance();
        dbConnector = new MyDBConnector();
    }

    public List<Movie> getMovies() throws SQLException {
        List<Movie> allMovies = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Movie");

            while (rs.next()) {
                int id =  rs.getInt("movieId");
                String name = rs.getString("Name");
                String rating = rs.getString("Rating");
                String path = rs.getString("Filelink");
                int lastView = rs.getInt("Lastview");
                int year = rs.getInt("Year");
                int duration = rs.getInt("Duration");

                Movie movie = new Movie (id, name, year, path, duration, rating, lastView);
                allMovies.add(movie);
            }
        } return allMovies;
    }

    /**
     * Adds a new movie to the database.
     * @param name
     * @param year
     * @param path
     * @param duration
     * @param rating
     * @param lastView
     * @return the new movie.
     */
    public Movie addMovie(String name, int year, String path, int duration, String rating, int lastView) throws SQLException {
        String sql = "INSERT INTO Movie (Name, Year, Duration, Rating, Filelink, LastView) VALUES (?,?,?,?,?,?);";
        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, name);
            st.setInt(2, year);
            st.setInt(3, duration);
            st.setString(4, rating);
            st.setString(5, path);
            st.setInt(6, lastView);

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }
            Movie movie = new Movie(id, name, year, path, duration, rating, lastView);
            return movie;
        }
    }

    public void editMovieRating(Movie movieToUpdate) throws SQLException {
        try (Connection con = dbConnector.getConnection()) {
            String sql = "UPDATE Movie SET Rating=? WHERE MovieId=?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, movieToUpdate.getRating());
        }
    }

}


