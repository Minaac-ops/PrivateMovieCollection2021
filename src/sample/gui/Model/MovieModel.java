package sample.gui.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Category;
import sample.be.Movie;
import sample.bll.Logicfacade;
import sample.bll.PMCManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MovieModel {

    private Logicfacade logicfacade;

    private ObservableList<Movie> allMovies;
    private ObservableList<Movie> badMovies;
    private ObservableList<Movie> oldMovies;

    public MovieModel() throws IOException, SQLException {
        logicfacade = new PMCManager();

        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(logicfacade.getAllMovies());

        badMovies = FXCollections.observableArrayList();
        badMovies.addAll(logicfacade.getBadMovies());

        oldMovies = FXCollections.observableArrayList();
        oldMovies.addAll(logicfacade.getOldMovies());
    }

    /**
     * Gets a list of all the movies
     * @return
     */
    public ObservableList<Movie> getAllMovies() {
        return allMovies;
    }


    /**
     * Gets the list of movies that matches the search that is made
     * @param searchfilter
     * @param query
     * @return
     */
    public ObservableList<Movie> search(List<Movie> searchfilter, String query) {
        return logicfacade.search((ObservableList<Movie>)searchfilter, query);
    }

    /**
     * Method for adding a movie to the database
     * @param name
     * @param year
     * @param duration
     * @param rating
     * @throws SQLException
     */
    public void addMovie(String name, int year, int duration, String rating) throws SQLException {
        Movie movie = logicfacade.addMovie(name, year, duration, rating);
        allMovies.add(movie);
    }

    /**
     * Method for editing rating in the database
     * @param id
     * @param rating
     * @throws SQLException
     * @throws IOException
     */
    public void editRating(int id, String rating) throws SQLException, IOException {
        logicfacade.editRating(id, rating);
    }

    /**
     * Method for deleting a movie in the database
     * @param movieToDelete
     */
    public void deleteMovie(Movie movieToDelete) throws SQLException {
        logicfacade.deleteMovie(movieToDelete);
        allMovies.remove(movieToDelete);
    }

    /**
     * Method to get a list of bad movies
     * @return
     */
    public ObservableList<Movie> getBadMovies() {
        return badMovies;
    }

    public ObservableList<Movie> getOldMovies() {
        return oldMovies;
    }

    public void addGenre(Movie movie, Category category) throws SQLException {
        logicfacade.addGenre(movie, category);
    }
}
