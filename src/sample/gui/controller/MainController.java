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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.be.Category;
import sample.be.Movie;
import sample.gui.Model.CategoryModel;
import sample.gui.Model.MovieModel;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public final MovieModel movieModel;
    private ObservableList<Movie> observableListMovie;
    private ObservableList<Movie> observableListCatMov;

    public final CategoryModel categoryModel;
    private ObservableList<Category> observableListCategory;

    @FXML
    public TableView<Category> lstCat;
    @FXML
    public TableColumn<Category, String> catNameColumn;
    @FXML
    public TableView<Movie> lstCatMov;
    @FXML
    public TableColumn<Movie, String> nameSongColumn;
    @FXML
    public TableColumn<Movie, Integer> durationcolumn;
    @FXML
    public TableColumn<Movie, Float> ratingcolumn;
    @FXML
    public TextField searchMovieTxt;

    public MainController() throws IOException, SQLException {
        categoryModel = new CategoryModel();
        movieModel = new MovieModel();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableListCategory = categoryModel.getCategories();
        observableListMovie = movieModel.getAllMovies();

        lstCatMov.setItems(observableListMovie);
        durationcolumn.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        nameSongColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ratingcolumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));

        lstCat.setItems(observableListCategory);
        catNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    public void handleGetCatMovies(MouseEvent event) {
        List<Movie> CatMovList = lstCat.getSelectionModel().getSelectedItem().getMovieList();
        lstCatMov.setItems((ObservableList<Movie>) CatMovList);
    }

    @FXML
    public void clickAddMovie(ActionEvent event) throws IOException {
        Parent MainParent = FXMLLoader.load(getClass().getResource("/sample/gui/View/AddMovie.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage addMovieStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        addMovieStage.setScene(MainScene);
        addMovieStage.show();
    }
    @FXML
    public void clickAddCategory(ActionEvent event) throws IOException {
        Parent MainParent = FXMLLoader.load(getClass().getResource("/sample/gui/View/addCatergory.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage addCategoryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        addCategoryStage.setScene(MainScene);
        addCategoryStage.show();
    }

    @FXML
    public void handleSearchMovie(ActionEvent event) {
        if (searchMovieTxt.getText() == null || searchMovieTxt.getText().length() <= 0 ) {
            lstCatMov.setItems(movieModel.getAllMovies());
        }
        else {
            ObservableList<Movie> foundMovie = movieModel.search(movieModel.getAllMovies(),searchMovieTxt.getText());
            lstCatMov.setItems(foundMovie);
        }
    }
}
