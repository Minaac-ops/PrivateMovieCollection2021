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
     * Gets all the movies from the database.
     * @return Returns a list of all the movies from the database.
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



                Movie movie = new Movie(id, name, year, path, duration, rating, lastView);
                allMovies.add(movie);
            }
        } return allMovies;
    }

    /**
     * Lets you add a new movie to the database.
     * @param name The name of the movie that you want to add.
     * @param year The year of the movie that you want to add.
     * @param duration The duration of the movie that you want to add in minutes.
     * @param rating The rating of the movie that you want to add. After you watch it, you can change/add this.
     * @return The new movie that you just added to the database.
     * @throws SQLException
     */
    public Movie addMovie(String name, int year, int duration, String rating) throws SQLException {
        String sql = "INSERT INTO Movie (Name, Year, Duration, Rating) VALUES (?,?,?,?);";
        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, name);
            st.setInt(2, year);
            st.setInt(3, duration);
            st.setString(4, rating);

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            String path = null;
            String lastView = null;

            if (rs.next()) {
                id = rs.getInt(1);
            }
            Movie movie = new Movie(id, name, year, path, duration, rating, lastView);
            return movie;
        }
    }

    /**
     * Method for editing the rating of a specific movie in the database.
     * @param id of the movie that you want to rate.
     * @param rating the new rating of the movie.
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
     * Method for deleting a movie from the database.
     * @param movieToDelete The movie to delete
     */
    public void deleteMovie(Movie movieToDelete) throws SQLException {
        try (Connection con = connectionPool.checkOut()){
            PreparedStatement st1 = con.prepareStatement("DELETE FROM CatMovie WHERE MovieId = ?;");
            st1.setInt(1, movieToDelete.getID());

            PreparedStatement st2 = con.prepareStatement("DELETE FROM Movie WHERE MovieId = ?;");
            st2.setInt(1, movieToDelete.getID());

            st1.executeUpdate();
            st2.executeUpdate();
        }
    }


    /**
     * Gets a List of all movies that have a rating below 5.0.
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
            ResultSet rs = statement.executeQuery("SELECT * FROM Movie WHERE Lastview <= '2019-01-20';");

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


