package sample.gui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.bll.util.UserError;
import sample.gui.Model.MovieModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddMovieController implements Initializable {

    private final String ERROR_HEADER = "Houston, we have a problem!";

    @FXML
    public TextField nameMovie;
    @FXML
    public TextField movieYear;
    @FXML
    public TextField movieDuration;
    @FXML
    public TextField movieRating;


    private MovieModel movieModel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            movieModel = new MovieModel();
        } catch (IOException e) {
            UserError.displayError(ERROR_HEADER,e.getMessage());
        } catch (SQLException throwables) {
            UserError.displayError(ERROR_HEADER,throwables.getMessage());
        }

    }

    /**
     * Method to handle the action that happens when you click the Add movie button.
     * @param event
     */
    @FXML
    public void handleAddMovbtn(ActionEvent event) {
        try {
            String name = nameMovie.getText().trim();

            int year = Integer.parseInt(movieYear.getText().trim());
            int duration = Integer.parseInt(movieDuration.getText().trim());
            String rating = movieRating.getText().trim();

            movieModel.addMovie(name, year, duration, rating);

            if (name != null && name.length() > 0 && name.length() < 100) {
                cancelNewMovie(event);
            }

        } catch (SQLException ex) {
            UserError.displayError(ERROR_HEADER,ex.getMessage());
        }
    }

    /**
     * Method to handle the action that happens when you click the Cancel button.
     * @param event
     */
    @FXML
    public void cancelNewMovie(ActionEvent event) {
        try {
            Parent Mainparent = FXMLLoader.load(getClass().getResource("/sample/gui/View/Main.fxml"));
            Scene Mainscene = new Scene(Mainparent);
            Stage MainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            MainStage.setScene(Mainscene);
            MainStage.show();
        }catch (IOException ex) {
            UserError.displayError(ERROR_HEADER,ex.getMessage());
        }
    }
}

