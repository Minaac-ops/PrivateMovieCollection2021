package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.stage.Stage;
import sample.be.Category;
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

    /**
     * Gets all the movies
     * @return Returns all the movies
     * @throws SQLException
     */
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
                String lastView = rs.getString("Lastview");
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
    public Movie addMovie(String name, int year, String path, int duration, String rating, String lastView) throws SQLException {
        String sql = "INSERT INTO Movie (Name, Year, Duration, Rating, Filelink, LastView) VALUES (?,?,?,?,?,?);";
        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, name);
            st.setInt(2, year);
            st.setInt(3, duration);
            st.setString(4, rating);
            st.setString(5, path);
            st.setString(6, lastView);

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            Category category = new Category(rs.getInt("CatId"), rs.getString("name"));

            Movie movie = new Movie(id, name, year, path, duration, rating, lastView);
            return movie;
        }
    }

    /**
     * Method for editing the rating of a specific movie
     * @param id of the movie
     * @param rating the new rating of the movie
     * @throws SQLException
     * @throws IOException
     */
    public void editMovieRating(int id, String rating) throws SQLException, IOException {
        try (Connection con = connectionPool.checkOut()) {
            String query = "UPDATE Movie SET Rating=? WHERE MovieId=?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, rating);
            st.setInt(2, id);
            st.execute();
        }
    }

    /**
     * Method for deleting a movie in the database
     * @param movieToDelete The movie to delete
     */
    public void deleteMovie(Movie movieToDelete) {
        try (Connection con = connectionPool.checkOut()){
            PreparedStatement st1 = con.prepareStatement("DELETE FROM CatMovie WHERE MovieId = ?;");
            st1.setInt(1, movieToDelete.getID());

            PreparedStatement st2 = con.prepareStatement("DELETE FROM Movie WHERE MovieId = ?;");
            st2.setInt(1, movieToDelete.getID());

            st1.executeUpdate();
            st2.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Gets a List of all movies that have a rating below 5.0
     * @return  the list of badMovies
     * @throws SQLException
     */

    public List<Movie> getBadMovies() throws SQLException {
        List<Movie> badMovies = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Movie WHERE Rating <= 5.0");

            while (rs.next()) {
                int id = rs.getInt("movieId");
                String name = rs.getString("Name");
                String rating = rs.getString("Rating");
                String path = rs.getString("Filelink");
                String lastView = rs.getString("Lastview");
                int year = rs.getInt("Year");
                int duration = rs.getInt("Duration");

                Movie movie = new Movie (id, name, year, path, duration, rating, lastView);
                badMovies.add(movie);
            }
        } return  badMovies;
    }

    /**
     * Gets a list of all movies that you haven't watched since 1990-01-01.
     * @return The list of old movies..
     */
    public List<Movie> getOldMovies() throws SQLException {
        List<Movie> oldMovies = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Movie WHERE Lastview <= '2000-01-01';");

            while (rs.next()) {
                int id = rs.getInt("movieId");
                String name = rs.getString("Name");
                String rating = rs.getString("Rating");
                String path = rs.getString("Filelink");
                String lastView = rs.getString("Lastview");
                int year = rs.getInt("Year");
                int duration = rs.getInt("Duration");

                Movie movie = new Movie(id, name, year, path, duration, rating, lastView);
                oldMovies.add(movie);
            }
        } return oldMovies;
    }
}


