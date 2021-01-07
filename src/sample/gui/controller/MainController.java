package sample.gui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;
import sample.be.Category;
import sample.be.Movie;
import sample.gui.Model.CategoryModel;
import sample.gui.Model.MovieModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final MovieModel movieModel;
    private ObservableList<Movie> observableListMovie;

    private CategoryModel categoryModel;
    private ObservableList<Category> categoryObservableList;

    @FXML
    private TableView<Category> lstCat;
    @FXML
    private TableColumn<Category, String> catNameColumn;
    @FXML
    private TableView<Movie> lstCatMov;
    @FXML
    private TableColumn<Movie, String> nameSongColumn;
    @FXML
    private TableColumn<Movie, Integer> durationcolumn;
    @FXML
    TableColumn<Movie, Float> ratingcolumn;

    public MainController() throws IOException, SQLException {
        categoryModel = new CategoryModel();
        movieModel = new MovieModel();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryObservableList = categoryModel.getCategories();
        observableListMovie = movieModel.getAllMovies();

        lstCatMov.setItems(observableListMovie);
        durationcolumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        nameSongColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingcolumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        lstCat.setItems(categoryObservableList);
        catNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    private void handleGetCatMovies(MouseEvent event) {
        lstCatMov.getItems().clear();
        List<Movie> catMovList = lstCat.getSelectionModel().getSelectedItem().getMovieList();
        for (int x = catMovList.size() -1; x >=0; x--) {
            catMovList.get(x).setId(catMovList.size() - x);
            lstCatMov.getItems().add(catMovList.get(10));
        }
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
        Parent MainParent = FXMLLoader.load(getClass().getResource("/sample/gui/View/addCategory.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage addCategoryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        addCategoryStage.setScene(MainScene);
        addCategoryStage.show();

    }


}
