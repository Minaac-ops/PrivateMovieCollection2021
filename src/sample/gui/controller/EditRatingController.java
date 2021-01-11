package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.be.Movie;
import sample.gui.Model.MovieModel;

import java.io.IOException;
import java.sql.SQLException;

public class EditRatingController {

    private MovieModel movieModel;

    @FXML
    public TextField ratingField;

    public EditRatingController() throws IOException, SQLException {
        movieModel = new MovieModel();
    }

    @FXML
    public void handleSaveRating(ActionEvent event)
    {
    }

    @FXML
    public void cancelEditRating(ActionEvent event) throws IOException {
        Parent Mainparent = FXMLLoader.load(getClass().getResource("/sample/gui/View/Main.fxml"));
        Scene Mainscene = new Scene(Mainparent);
        Stage MainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        MainStage.setScene(Mainscene);
        MainStage.show();
    }

}