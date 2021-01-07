package sample.bll;

import sample.be.Category;
import sample.be.Movie;

import java.sql.SQLException;
import java.util.List;

public interface logicfacade {

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
     * @param filelink The link to the mp4 file.
     * @return A new movie.
     */
    Movie addMovie(String name, int year, int duration, float rating, String filelink);

    /**
     * Deletes a movie.
     * @param movie The movie to delete.
     */
    void deleteMovie(Movie movie);

    /**
     * Lets you edit or add a rating.
     * @param movie The movie that you want to add a new rating.
     * @return The movie with the new rating.
     */
    Movie editRating(Movie movie);

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
     * @param query
     * @return
     */
    List<Movie> searchMovies(String query);

    /**
     * Gets a list of all songs in a spesific category orderes by id.
     * @param id
     * @return
     */
    List<Movie> getAllCatMovie(int id);


}
