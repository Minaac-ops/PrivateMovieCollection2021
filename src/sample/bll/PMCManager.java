package sample.bll;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Category;
import sample.be.Movie;
import sample.bll.util.MovieSearcher;
import sample.dal.DalController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PMCManager implements Logicfacade {

    private final DalController dalController;
    private MovieSearcher movieSearcher;

    public PMCManager() throws IOException, SQLServerException {
        dalController = new DalController();
        movieSearcher = new MovieSearcher();
    }

    /**
     * Gets a list of all songs.
     *
     * @return a list of songs.
     */
    @Override
    public List<Movie> getAllMovies() throws SQLException {
        return dalController.getAllMovies();
    }

    /**
     * Adds a new movie.
     *
     * @param name     The name of the movie.
     * @param year     The release year.
     * @param duration The duration in minutes.
     * @param rating   The rating on the movie.
     * @param filelink The link to the mp4 file.
     * @return A new movie.
     */
    @Override
    public Movie addMovie(String name, int year, int duration, float rating, String filelink) {
        return dalController.addMovie(name, year, duration, filelink);
    }

    /**
     * Deletes a movie.
     *
     * @param movie The movie to delete.
     */
    @Override
    public void deleteMovie(Movie movie) {

    }

    /**
     * Lets you edit or add a rating.
     *
     * @param movieToUpdate The movie that you want to add a new rating.
     */
    @Override
    public void editRating(Movie movieToUpdate) throws SQLException {
        dalController.editRating(movieToUpdate);
    }

    /**
     * Adds a category.
     *
     * @param name The name of the new category
     * @return The new category.
     */
    @Override
    public Category addCategory(String name) {
        return dalController.addCategory(name);
    }

    /**
     * Deletes a category.
     *
     * @param category
     */
    @Override
    public void deleteCategory(Category category) {

    }

    /**
     * Gets a list of all categories
     *
     * @return A list of all categories.
     */
    @Override
    public List<Category> getAllCategories() throws SQLException {
        return dalController.getCategories();
    }

    /**
     * Searches through the list of all movie titles and categories.
     *
     * @param query
     * @return
     */
    @Override
    public ObservableList<Movie> search(ObservableList<Movie> searchfilter, String query) {
        ObservableList<Movie> foundMovies = FXCollections.observableArrayList();
        foundMovies.addAll(movieSearcher.search(searchfilter, query));
        return foundMovies;
    }

    /**
     * Gets a list of all Movie in a specific category ordered by id.
     *
     * @param id
     * @return
     */
    @Override
    public List<Movie> getAllCatMovies(int id) throws SQLException {
        List<Movie> catMovieList = getAllCatMovies(id);
        return catMovieList;
    }
}
