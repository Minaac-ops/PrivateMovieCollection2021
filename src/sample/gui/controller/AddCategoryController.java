package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.gui.Model.CategoryModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCategoryController implements Initializable {

    private CategoryModel categoryModel;

    public AddCategoryController() throws IOException, SQLException {
        categoryModel = new CategoryModel();
    }

    
    @FXML
    public TextField txtName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            categoryModel = new CategoryModel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void handleAddCatbtn(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        categoryModel.addCategory(name);

    }
}
