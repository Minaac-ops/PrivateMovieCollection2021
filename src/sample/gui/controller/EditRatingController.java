package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
}
