package sample.gui.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
     * @param path
     * @param duration
     * @param rating
     * @param lastView
     * @throws SQLException
     */
    public void addMovie(String name, int year, String path, int duration, String rating, String lastView) throws SQLException {
        Movie movie = logicfacade.addMovie(name, year, path, duration, rating, lastView);
        allMovies.add(movie);
    }

    /**
     * Method to edit rating in the database
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
    public void deleteMovie(Movie movieToDelete) {
        logicfacade.deleteMovie(movieToDelete);
        allMovies.remove(movieToDelete);
    }

    /**
     * Method to get a list of the bad movies
     * @return
     */
    public ObservableList<Movie> getBadMovies() {
        return badMovies;
    }

    public ObservableList<Movie> getOldMovies() {
        return oldMovies;
    }
}
