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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.be.Category;
import sample.be.Movie;
import sample.gui.Model.CategoryModel;
import sample.gui.Model.MovieModel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public final MovieModel movieModel;
    private ObservableList<Movie> movieObservableList;

    public final CategoryModel categoryModel;
    private ObservableList<Category> observableListCategory;



    @FXML
    public TableView<Movie> lstAllMovies;
    @FXML
    public TableColumn<Movie, String> movieColumn;
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
    @FXML
    public Label addGenreLabel;

    public MainController() throws IOException, SQLException {
        categoryModel = new CategoryModel();
        movieModel = new MovieModel();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableListCategory = categoryModel.getCategories();
        movieObservableList = movieModel.getAllMovies();

        durationcolumn.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        nameSongColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ratingcolumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));

        lstCat.setItems(observableListCategory);
        catNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        lstAllMovies.setItems(movieObservableList);
        movieColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        doubleClickListener();
    }

    private void doubleClickListener() {
        lstCatMov.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Movie clickedMovie = lstCatMov.getSelectionModel().getSelectedItem();
                try {
                    File f = new File(clickedMovie.getPath());
                    if (f.isFile() && !f.isDirectory()) {
                        Desktop.getDesktop().open(f);
                    } else {
                        System.out.println("File not found: " + clickedMovie.getPath());
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });

        lstAllMovies.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Movie clickedMovie = lstAllMovies.getSelectionModel().getSelectedItem();
                try {
                    File f = new File(clickedMovie.getPath());
                    if (f.isFile() && !f.isDirectory()) {
                        Desktop.getDesktop().open(f);
                    } else {
                        System.out.println("File not found: " + clickedMovie.getPath());
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Method to handle the action that happens when you click the category and the movies show up
     * @param event
     */
    @FXML
    public void handleGetCatMovies(MouseEvent event) {
        lstCatMov.getItems().clear();
        List<Movie> catMovList = lstCat.getSelectionModel().getSelectedItem().getMovieList();
        for (int x = catMovList.size() - 1; x >= 0; x--) {
            lstCatMov.getItems().add(catMovList.get(x));
        }
    }

    /**
     * Method to handle the action that happens when you click the Add movie button
     * @param event
     * @throws IOException
     */
    @FXML
    public void clickAddMovie(ActionEvent event) throws IOException {
        Parent MainParent = FXMLLoader.load(getClass().getResource("/sample/gui/View/AddMovie.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage addMovieStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        addMovieStage.setScene(MainScene);
        addMovieStage.show();
    }

    /**
     * Method to handle the action that happens when you click the Add category button
     * @param event
     * @throws IOException
     */
    @FXML
    public void clickAddCategory(ActionEvent event) throws IOException {
        Parent MainParent = FXMLLoader.load(getClass().getResource("/sample/gui/View/addCatergory.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage addCategoryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        addCategoryStage.setScene(MainScene);
        addCategoryStage.show();
    }

    /**
     * Method to handle the action that happens when you click the Edit Rating button
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleEditRating(ActionEvent event) throws IOException {
        Parent MainParent = FXMLLoader.load(getClass().getResource("/sample/gui/View/editRating.fxml"));
        Scene Mainscene = new Scene(MainParent);
        Stage editRatingStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        editRatingStage.setScene(Mainscene);
        editRatingStage.show();
    }

    /**
     * Method to handle the action that happens when you click the search button
     * @param event
     */
    @FXML
    public void handleSearchMovie(ActionEvent event) {
        if (searchMovieTxt.getText() == null || searchMovieTxt.getText().length() <= 0 ) {
            lstAllMovies.setItems(movieModel.getAllMovies());
        }
        else {
            ObservableList<Movie> foundMovie = movieModel.search(movieModel.getAllMovies(), searchMovieTxt.getText());
            lstAllMovies.setItems(foundMovie);
        }
    }

    /**
     * Method to handle the action that happens when you click the delete category button
     * @param event
     */
    @FXML
    public void handleDeleteCat(ActionEvent event) {
        Category categoryToDelete = lstCat.getSelectionModel().getSelectedItem();
        try {
            if (categoryToDelete != null) {
                categoryModel.deleteCategory(categoryToDelete);
            }
        } catch (SQLException throwables) {
            displayError(throwables);
            throwables.printStackTrace();
        }
    }

    /**
     * Method to handle the action that happens when you click the delete movie button
     * @param event
     */
    @FXML
    public void handleDeleteMovie(ActionEvent event) {
        Movie movieToDelete = lstAllMovies.getSelectionModel().getSelectedItem();
        if (movieToDelete != null)
        try {
        movieModel.deleteMovie(movieToDelete);
        lstCatMov.getItems().remove(movieToDelete);
        }
        catch (SQLException throwables) {
            displayError(throwables);
            throwables.printStackTrace();
        }
        else System.out.println("You have to Choose a movie to delete!");
    }

    public void displayError(Throwable throwable)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Here's Johnny");
        alert.setHeaderText("Houston, we have a problem! " + "You have not chosen a Genre");
        alert.showAndWait();
    }

    @FXML
    public void handleAddGenre(ActionEvent event) throws SQLException {
        Category category = lstCat.getSelectionModel().getSelectedItem();
        Movie movie = lstAllMovies.getSelectionModel().getSelectedItem();
        if (movie != null && category != null) {
            movieModel.addGenre(movie, category);
            addGenreLabel.setText("'It's what you do right now that makes a difference.' - Struecker");
            lstCatMov.getItems().add(movie);
        }
    }
}

