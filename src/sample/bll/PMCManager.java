package sample.bll;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.be.Category;
import sample.be.Movie;
import sample.dal.DalController;
import sample.dal.db.CategoryDAO;
import sample.gui.controller.AddCategoryController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PMCManager implements logicfacade {

    private final DalController dalController;

    public PMCManager() throws IOException, SQLServerException {
        dalController = new DalController();
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
     * @param year     The releaseyear.
     * @param duration The duration in minutes.
     * @param rating   The rating on the movie.
     * @param filelink The link to the mp4 file.
     * @return A new movie.
     */
    @Override
    public Movie addMovie(String name, int year, int duration, float rating, String filelink) {
        return null;
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
     * @param movie The movie that you want to add a new rating.
     * @return The movie with the new rating.
     */
    @Override
    public Movie editRating(Movie movie) {
        return null;
    }

    /**
     * Adds a category.
     *
     * @param name The name of the new category
     * @return The new category.
     */
    @Override
    public Category addCategory(String name) {
        return null;
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
    public List<Movie> searchMovies(String query) {
        return null;
    }

    /**
     * Gets a list of all songs in a spesific category orderes by id.
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
