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
    @FXML
    public TextField lastViewField;


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

    /**
     * Method to handle the action that happens when you click the Add movie button
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void handleAddMovbtn(ActionEvent event) throws IOException, SQLException {
        String name = nameMovie.getText().trim();
        int year = Integer.parseInt(movieYear.getText().trim());
        int duration = Integer.parseInt(movieDuration.getText().trim());
        String rating = movieRating.getText().trim();
        String path = moviePath.getText().trim();
        String lastView = lastViewField.getText().trim();


        movieModel.addMovie(name, year, path, duration, rating, lastView);

        if (name != null && name.length() > 0 && name.length() < 100) {
            cancelNewMovie(event);
        }
    }

    /**
     * Method to handle the action that happens when you click the Cancel button
     * @param event
     * @throws IOException
     */
    @FXML
    public void cancelNewMovie(ActionEvent event) throws IOException {
        Parent Mainparent = FXMLLoader.load(getClass().getResource("/sample/gui/View/Main.fxml"));
        Scene Mainscene = new Scene(Mainparent);
        Stage MainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        MainStage.setScene(Mainscene);
        MainStage.show();
    }
}

