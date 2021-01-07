package sample.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.be.Movie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    }


