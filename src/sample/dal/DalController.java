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
        return catrepo.createCategory(name);
    }

    /**
     * Deletes a category from the database,
     *
     * @param categoryToDelete The category to delete.
     */
    @Override
    public void deleteCategory(Category categoryToDelete) {
    catrepo.deleteCategory(categoryToDelete);
    }

    /**
     * Gets a list of all categories from the database.
     *
     * @return The list of all Categories.
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
     * @param  year the year the movie was was released.
     * @param rating   The rating of the movie
     * @param duration The duration of the movie in minutes.
     * @return a movie object.
     */
    @Override
    public Movie addMovie(String name, int year, int duration, String rating) throws SQLException {
        return movierepo.addMovie(name, year, duration, rating);
    }

    /**
     * Deletes a movie from the database.
     *
     * @param movieToDelete The movie to delete.
     */
    @Override
    public void deleteMovie(Movie movieToDelete) {
        movierepo.deleteMovie(movieToDelete);
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
     * @param id The movie with the updated or added rating.
     */
    @Override
    public void editRating(int id, String rating) throws SQLException, IOException {
        movierepo.editMovieRating(id, rating);
    }

    /**
     * Gets a list of the movies in the chosen category
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Movie> getCatMovies(int id) throws SQLException {
        return catmovierepo.getAllCatmovies(id);
    }

    /**
     * Gets a list of the bad movies
     * @return
     * @throws SQLException
     */
    @Override
    public List<Movie> getBadMovies() throws SQLException {
        return movierepo.getBadMovies();
    }

    @Override
    public List<Movie> getOldMovies() throws SQLException {
        return movierepo.getOldMovies();
    }

}
