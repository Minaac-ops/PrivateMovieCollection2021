package sample.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.be.Category;
import sample.be.Movie;
import sample.dal.db.CatMovDAO;
import sample.dal.db.CategoryDAO;
import sample.dal.db.MovieDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DalController implements dalInterface {

    private CategoryDAO catrepo;
    private MovieDAO movierepo;
    private CatMovDAO catmovierepo;

    public DalController() throws IOException, SQLServerException {
        movierepo = new MovieDAO();
        catrepo = new CategoryDAO();
        catmovierepo = new CatMovDAO();
    }

    /**
     * Adds a category to the database.
     *
     * @param name The name of the category.
     * @return The new category.
     */
    @Override
    public Category addCategory(String name) {
        return null;
    }

    /**
     * Deletes a category from the database,
     *
     * @param category The category to delete.
     */
    @Override
    public void deleteCategory(String category) {

    }

    /**
     * Gets a list of all categories from the database.
     *
     * @return The list of all Movies.
     */
    @Override
    public List<Category> getCategories() throws SQLException {
        List<Category> allCategories;
        allCategories = catrepo.getCategories();
        return allCategories;
    }

    /**
     * Gets a category from the database.
     *
     * @param id The id on the chosen category.
     * @return The chosen category with the given id.
     */
    @Override
    public Category getCategory(int id) {
        return null;
    }

    /**
     * Adds a movie in the database.
     *
     * @param name     The name of the movie
     * @param rating   The rating of the movie
     * @param filelink The name of the mp4 file.
     * @param duration The duration of the movie in minutes.
     * @return a movie object.
     */
    @Override
    public Movie addMovie(String name, double rating, int filelink, String duration) {
        return null;
    }

    /**
     * Deletes a movie from the database.
     *
     * @param movie The movie to delete.
     */
    @Override
    public void deleteMovie(Movie movie) {

    }

    /**
     * Gets a list of all the movies from the database.
     *
     * @return A list of movies.
     */
    @Override
    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> allMovies;
        allMovies = movierepo.getMovies();
        return allMovies;
    }

    /**
     * Gets a movie with the given ID.
     *
     * @param id The ID of the chosen movie.
     * @return The movie the chosen ID.
     */
    @Override
    public Movie getMovie(int id) {
        return null;
    }

    /**
     * Lets you edit or add a rating on a movie from the database.
     *
     * @param movie The movie with the updated or added rating.
     */
    @Override
    public void editRating(Movie movie) {

    }

    @Override
    public List<Movie> getCatMovies(int catMovId) throws SQLException {
        return catmovierepo.getAllCatmovie(catMovId);
    }

}
