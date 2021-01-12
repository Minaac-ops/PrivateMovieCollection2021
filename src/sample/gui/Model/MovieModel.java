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

    private ObservableList<Movie> allMovies;
    private Logicfacade logicfacade;
    private ObservableList<Movie> badMovies;

    public MovieModel() throws IOException, SQLException {
        allMovies = FXCollections.observableArrayList();
        logicfacade = new PMCManager();
        allMovies.addAll(logicfacade.getAllMovies());
        badMovies = FXCollections.observableArrayList();
        badMovies.addAll(logicfacade.getBadMovies());
    }

    public ObservableList<Movie> getAllMovies() {
        return allMovies;
    }


    public ObservableList<Movie> search(List<Movie> searchfilter, String query) {
        return logicfacade.search((ObservableList<Movie>)searchfilter, query);
    }

    public void addMovie(String name, int year, String path, int duration, String rating, String lastView) throws SQLException {
        Movie movie = logicfacade.addMovie(name, year, path, duration, rating, lastView);
        allMovies.add(movie);
    }

    public void editRating(int id, String rating) throws SQLException, IOException {
        logicfacade.editRating(id, rating);
    }

    public void deleteMovie(Movie movieToDelete) {
        logicfacade.deleteMovie(movieToDelete);
        allMovies.remove(movieToDelete);
    }

    public ObservableList<Movie> getBadMovies() {
        return badMovies;
    }
}
