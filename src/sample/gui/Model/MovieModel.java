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

    public MovieModel() throws IOException, SQLException {
        allMovies = FXCollections.observableArrayList();
        logicfacade = new PMCManager();
        allMovies.addAll(logicfacade.getAllMovies());
    }

    public ObservableList<Movie> getAllMovies() {
        return allMovies;
    }


    public ObservableList<Movie> search(List<Movie> searchfilter, String query) {
        return logicfacade.search((ObservableList<Movie>)searchfilter, query);
    }

    public void addMovie(String name, int year, int duration, float rating, String path){
        Movie movie = logicfacade.addMovie(name, year, duration, rating, path);
        allMovies.add(movie);
    }
}
