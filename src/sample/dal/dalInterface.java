package sample.dal;

import sample.be.Category;
import sample.be.Movie;

import java.sql.SQLException;
import java.util.List;

public interface dalInterface {

    /**
     * Adds a category to the database.
     * @param name The name of the category.
     * @return The new category.
     */
    Category addCategory(String name);

    /**
     * Deletes a category from the database,
     * @param category The category to delete.
     */
    void deleteCategory(String category);

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
     * Adds a movie in the database.
     *
     * @param name The name of the movie
     * @param rating The rating of the movie
     * @param filelink The name of the mp4 file.
     * @param duration The duration of the movie in minutes.
     * @return a movie object.
     */
    Movie addMovie(String name, double rating, int filelink, String duration);

    /**
     * Deletes a movie from the database.
     * @param movie The movie to delete.
     */
    void deleteMovie(Movie movie);

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
     * @param movie The movie with the updated or added rating.
     */
    void editRating(Movie movie);

    /**
     * Gets a list of all movies in choosen category from the database.
     * @return
     */
    List<Movie> getCatMovies(int catMovId) throws SQLException;
}
