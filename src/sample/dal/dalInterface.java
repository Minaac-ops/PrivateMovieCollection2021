package sample.dal;

import sample.be.Category;
import sample.be.Movie;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface dalInterface {

    /**
     * Adds a category to the database.
     * @param name The name of the category.
     * @return The new category.
     */
    Category addCategory(String name) throws SQLException;

    /**
     * Deletes a category from the database,
     * @param categoryToDelete The category to delete.
     */
    void deleteCategory(Category categoryToDelete) throws SQLException;

    /**
     * Gets a list of all categories from the database.
     * @return The list of all Movies.
     */
    List<Category> getCategories() throws SQLException;

    /**
     * Gets a category from the database.
     * @param id The id on the chosen category.
     * @return The chosen category with the given id.
     */
    Category getCategory(int id);

    /**
     *
     * @param name
     * @param year
     * @param duration
     * @param rating
     * @return
     * @throws SQLException
     */
    Movie addMovie(String name, int year, int duration, String rating) throws SQLException;

    /**
     * Deletes a movie from the database.
     * @param movie The movie to delete.
     */
    void deleteMovie(Movie movie) throws SQLException;

    /**
     * Gets a list of all the movies from the database.
     * @return A list of movies.
     */
    List<Movie> getAllMovies() throws SQLException;

    /**
     * Gets a movie with the given ID.
     * @param id The ID of the chosen movie.
     * @return The movie the chosen ID.
     */
    Movie getMovie(int id);

    /**
     * Lets you edit or add a rating on a movie from the database.
     *
     * @param id The movie with the updated or added rating.
     */
    void editRating(int id, String rating) throws SQLException, IOException;

    /**
     * Gets a list of all movies in choosen category from the database.
     * @return
     */
    List<Movie> getCatMovies(int catMovId) throws SQLException;

    /**
     * Gets a List of all bad movies from the database.
     * @return
     */
    List<Movie> getBadMovies () throws SQLException;

    List<Movie> getOldMovies() throws SQLException;
}
