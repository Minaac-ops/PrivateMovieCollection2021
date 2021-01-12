package sample.gui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.be.Movie;
import sample.gui.Model.MovieModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WarningController implements Initializable {

    public final MovieModel movieModel;
    private ObservableList<Movie> badMovies;

    @FXML
    public TableView<Movie> lstBadMovies;
    @FXML
    public TableColumn<Movie,String> badNameColumn;

    public WarningController() throws IOException, SQLException {
        movieModel = new MovieModel();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        badMovies = movieModel.getBadMovies();
        lstBadMovies.setItems(badMovies);
        badNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }


    /**
     * Method to handle the action that happens when you click the Ok button and deletes the bad movies
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleOk(ActionEvent event) throws IOException {
        Parent MainParent = FXMLLoader.load(getClass().getResource("/sample/gui/View/Main.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage MainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        MainStage.setScene(MainScene);
        MainStage.show();
    }

    /**
     * Method to handle the action that happens when you click the cancel button to keep bad movies
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleCancel(ActionEvent event) throws IOException {
        Parent MainParent = FXMLLoader.load(getClass().getResource("/sample/gui/View/Main.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage MainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        MainStage.setScene(MainScene);
        MainStage.show();
    }
}



