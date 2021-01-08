package sample.gui.Model;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Movie;
import sample.bll.PMCManager;
import sample.bll.logicfacade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

    public ObservableList<Movie> search(List<Movie> searchfilter, String query) {
        return logicfacade.search((ObservableList<Movie>)searchfilter, query);
    }
}
