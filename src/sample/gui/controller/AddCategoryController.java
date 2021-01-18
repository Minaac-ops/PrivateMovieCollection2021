package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.bll.util.UserError;
import sample.gui.Model.CategoryModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCategoryController implements Initializable {

    private final String ERROR_HEADER = "Houston, we have a problem!";
    private CategoryModel categoryModel;

    public AddCategoryController(){
        try {
            categoryModel = new CategoryModel();
        }catch (IOException | SQLException ex) {
            UserError.displayError(ERROR_HEADER,ex.getMessage());
        }
    }

    @FXML
    public TextField txtName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            categoryModel = new CategoryModel();
        } catch (IOException e) {
            UserError.displayError(ERROR_HEADER,e.getMessage());
        } catch (SQLException throwables) {
            UserError.displayError(ERROR_HEADER,throwables.getMessage());
        }

    }

    /**
     * Method to handle the action that happens when you click the Add genre button.
     * @param actionEvent
     */
    @FXML
    public void handleAddCatbtn(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        try {
            if (name.length() > 0) {
                categoryModel.addCategory(name);
            } String message = "Choose a Name";
            UserError.displayError(ERROR_HEADER,message);
        } catch (SQLException throwables) {
            UserError.displayError(ERROR_HEADER,throwables.getMessage());
        }
        if (name != null && name.length() > 0 && name.length() < 100) {
            cancelNewCategory(actionEvent);
        }
    }

    /**
     * Method to handle the action that happens when you click the Cancel button.
     * @param event
     */
    @FXML
    public void cancelNewCategory(ActionEvent event) {
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
