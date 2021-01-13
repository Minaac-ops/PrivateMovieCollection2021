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
     * @return A new movie.
     */
    @Override
    public Movie addMovie(String name, int year, int duration, String rating) throws SQLException {
        return dalController.addMovie(name, year, duration, rating);
    }

    /**
     * Deletes a movie.
     *
     * @param movieToDelete The movie to delete.
     */
    @Override
    public void deleteMovie(Movie movieToDelete) throws SQLException{
        if (movieToDelete != null) {
            dalController.deleteMovie(movieToDelete);
        }
    }

    /**
     * Lets you edit or add a rating.
     *
     * @param id string rating The movie that you want to add a new rating.
     */
    @Override
    public void editRating(int id, String rating) throws SQLException, IOException {
        dalController.editRating(id, rating);

    }

    /**
     * Adds a category.
     *
     * @param name The name of the new category
     * @return The new category.
     */
    @Override
    public Category addCategory(String name) throws SQLException {
        return dalController.addCategory(name);
    }

    /**
     * Deletes a category.
     *
     * @param categoryToDelete
     */
    @Override
    public void deleteCategory(Category categoryToDelete) throws SQLException {
    if (categoryToDelete != null){
        dalController.deleteCategory(categoryToDelete);
    }
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

    @Override
    public List<Movie> getBadMovies() throws SQLException {
        return dalController.getBadMovies();
    }

    @Override
    public List<Movie> getOldMovies() throws SQLException {
        return dalController.getOldMovies();
    }

    @Override
    public void addGenre(Movie movie, Category category) throws SQLException {
        dalController.addGenre(movie, category);
    }
}
