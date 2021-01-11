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

    @FXML
    public void handleAddCatbtn(ActionEvent actionEvent) throws IOException {
        String name = txtName.getText().trim();
        categoryModel.addCategory(name);
        if (name != null && name.length() > 0 && name.length() < 20) {
            cancelNewCategory(actionEvent);
        }
    }

    @FXML
    public void cancelNewCategory(ActionEvent event) throws IOException {
        Parent Mainparent = FXMLLoader.load(getClass().getResource("/sample/gui/View/Main.fxml"));
        Scene Mainscene = new Scene(Mainparent);
        Stage MainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        MainStage.setScene(Mainscene);
        MainStage.show();
    }
}
