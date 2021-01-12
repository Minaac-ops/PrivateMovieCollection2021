package sample.gui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.be.Movie;
import sample.gui.Model.MovieModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditRatingController implements Initializable {

    private MovieModel movieModel;
    private ObservableList<Movie> movieObservableList;


    @FXML
    private TableView<Movie> lstCatMov;
    @FXML
    private TableColumn<Movie, String> movieColumn;
    @FXML
    public TextField ratingField;
    @FXML
    private Label editLabel;



    public EditRatingController() throws IOException, SQLException {
        movieModel = new MovieModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieObservableList = movieModel.getAllMovies();
        lstCatMov.setItems(movieObservableList);
        movieColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    /**
     * Method to handle the action that happens when you click to save the new rating
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void handleSaveRating(ActionEvent event) throws IOException, SQLException {
        int id = lstCatMov.getSelectionModel().getSelectedItem().getID();
        String rating = ratingField.getText().trim();
        movieModel.editRating(id, rating);
        editLabel.setText("You have changed rating on " + lstCatMov.getSelectionModel().getSelectedItem().getName() + "!");
    }

    /**
     * Method to handle the action that happens when you click the Cancel button
     * @param event
     * @throws IOException
     */
    @FXML
    public void cancelEditRating(ActionEvent event) throws IOException {
        Parent Mainparent = FXMLLoader.load(getClass().getResource("/sample/gui/View/Main.fxml"));
        Scene Mainscene = new Scene(Mainparent);
        Stage MainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        MainStage.setScene(Mainscene);
        MainStage.show();
    }
}
