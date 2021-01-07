package sample.gui.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Category;
import sample.bll.PMCManager;
import sample.bll.logicfacade;

import java.io.IOException;
import java.sql.SQLException;

public class CategoryModel {

    private ObservableList<Category> categories;
    private logicfacade logicfacade;

    public CategoryModel() throws IOException, SQLException {
        categories = FXCollections.observableArrayList();
        logicfacade = new PMCManager();
        categories.addAll(logicfacade.getAllCategories());
    }

    public ObservableList<Category> getCategories()
    {
        return categories;
    }

    public void addCategory (String name){

    }

}
