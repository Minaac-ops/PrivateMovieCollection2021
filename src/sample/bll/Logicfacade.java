package sample.bll;

import javafx.collections.ObservableList;
import sample.be.Category;
import sample.be.Movie;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Logicfacade {

    /**
     * Gets a list of all songs.
     * @return a list of songs.
     */
    List<Movie> getAllMovies() throws SQLException;

    /**
     * Adds a new movie.
     * @param name The name of the movie.
     * @param year The releaseyear.
     * @param duration The duration in minutes.
     * @param rating The rating on the movie.
     * @param path The link to the mp4 file.
     * @return A new movie.
     */
    Movie addMovie(String name, int year, String path, int duration, String rating, String lastView) throws SQLException;

    /**
     * Deletes a movie.
     * @param movie The movie to delete.
     */
    void deleteMovie(Movie movie);

    /**
     * Lets you edit or add a rating.
     * @param id, rating The movie that you want to add a new rating.
     */
    void editRating(int id, String rating) throws SQLException, IOException;

    /**
     * Adds a category.
     * @param name The name of the new category
     * @return The new category.
     */
    Category addCategory(String name);

    /**
     * Deletes a category.
     * @param category
     */
    void deleteCategory(Category category);

    /**
     * Gets a list of all categories
     * @return A list of all categories.
     */
    List<Category> getAllCategories() throws SQLException;

    /**
     * Searches through the list of all movie titles and categories.
     * @param searchfilter, query
     * @return
     */
    ObservableList<Movie> search(ObservableList<Movie> searchfilter, String query);

    /**
     * Gets a list of all movies in a specific category ordered by id.
     * @param id
     * @return
     */
    List<Movie> getAllCatMovies(int id) throws SQLException;


    /**
     * Gets a list of all bad movies
     * @return
     */
    List<Movie> getBadMovies() throws SQLException;


}
