package sample.gui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.gui.Model.MovieModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddMovieController implements Initializable {

    @FXML
    public TextField nameMovie;
    @FXML
    public TextField movieYear;
    @FXML
    public TextField movieDuration;
    @FXML
    public TextField movieRating;
    @FXML
    public TextField moviePath;


    private MovieModel movieModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            movieModel = new MovieModel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void handleAddMovbtn(ActionEvent event) {
        String name = nameMovie.getText().trim();
        int year = Integer.parseInt(movieYear.getText().trim());
        int duration = Integer.parseInt(movieDuration.getText().trim());
        float rating = Integer.parseInt(movieRating.getText().trim());
        String path = moviePath.getText().trim();

        movieModel.addMovie(name, year, duration, rating, path);
    }
}

