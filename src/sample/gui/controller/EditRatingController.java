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
import sample.bll.util.UserError;
import sample.gui.Model.MovieModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditRatingController implements Initializable {

    private final String ERROR_HEADER = "Houston, we have a problem!";
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


    public EditRatingController(){
        try {
            movieModel = new MovieModel();
        } catch ( IOException | SQLException ex) {
            UserError.displayError(ERROR_HEADER,ex.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieObservableList = movieModel.getAllMovies();
        lstCatMov.setItems(movieObservableList);
        movieColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    /**
     * Method to handle the action that happens when you click to save the new rating.
     *
     * @param event
     */
    @FXML
    public void handleSaveRating(ActionEvent event) {
        try {
            int id = lstCatMov.getSelectionModel().getSelectedItem().getID();
            String rating = ratingField.getText().trim();
            if (rating != null) {
                movieModel.editRating(id, rating);
                editLabel.setText("You have changed rating on " + lstCatMov.getSelectionModel().getSelectedItem().getName() + "!");
            } else if(rating == null) {
                String message = "You have to rate a Movie";
                UserError.displayError(ERROR_HEADER,message);
            }
        } catch (IOException | SQLException ex) {
            UserError.displayError(ERROR_HEADER,ex.getMessage());
        }
    }

        /**
         * Method to handle the action that happens when you click the Cancel button.
         * @param event
         */
        @FXML
        public void cancelEditRating (ActionEvent event) {
            try {
                Parent Mainparent = FXMLLoader.load(getClass().getResource("/sample/gui/View/Main.fxml"));
                Scene Mainscene = new Scene(Mainparent);
                Stage MainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                MainStage.setScene(Mainscene);
                MainStage.show();
            } catch (IOException ex) {
                UserError.displayError(ERROR_HEADER,ex.getMessage());
            }
        }

    /**
     * Method to handle the action that happens when you click the OK button.
     * @param event
     */
        @FXML
        public void okEditRating (ActionEvent event) {
            try {
                Parent Mainparent = FXMLLoader.load(getClass().getResource("/sample/gui/View/Main.fxml"));
                Scene Mainscene = new Scene(Mainparent);
                Stage MainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                MainStage.setScene(Mainscene);
                MainStage.show();
            } catch (IOException ex) {
                UserError.displayError(ERROR_HEADER,ex.getMessage());
            }
        }
}
