package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.be.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO
{

    private final JDBCConnectionPool connectionPool;

    public MovieDAO() throws IOException, SQLServerException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public List<Movie> getMovies() throws SQLException {
        List<Movie> allMovies = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Movie");
            while (rs.next()) {
                int id =  rs.getInt("movieId");
                String name = rs.getString("Name");
                Float rating = rs.getFloat("Rating");
                String path = rs.getString("Filelink");
                int lastView = rs.getInt("Lastview");
                int year = rs.getInt("Year");
                int duration = rs.getInt("Duration");

                Movie movie = new Movie (id, name, year, path, duration, rating, lastView);
                allMovies.add(movie);
            }

        } return allMovies;

        }

        public  Movie addMovie (String name, int year, String path,int duration, float rating, int lastView) {
            String sql = "INSERT INTO Movie (Name, Year, Duration, Rating, Path, LastView) VALUES (?,?,?,?,?,?);";
            try (Connection con = connectionPool.checkOut();
                 PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                st.setString(1, name);
                st.setInt(2, year);
                st.setInt(3, duration);
                st.setFloat(4, rating);
                st.setString(5, path);
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                int id = 0;

                if (rs.next()) {
                    id = rs.getInt(0);
                }
                Movie movie = new Movie(id, name, year, path, duration, rating, lastView);
                return movie;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }

    }


