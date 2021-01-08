package sample.gui.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Movie;
import sample.bll.PMCManager;

import java.io.IOException;
import java.sql.SQLException;

public class MovieModel {

    private ObservableList<Movie> allMovies;
    private sample.bll.logicfacade logicfacade;

    public MovieModel() throws IOException, SQLException {
        allMovies = FXCollections.observableArrayList();
        logicfacade = new PMCManager();
        allMovies.addAll(logicfacade.getAllMovies());
    }

    public ObservableList<Movie> getAllMovies() {
        return allMovies;
    }
}
