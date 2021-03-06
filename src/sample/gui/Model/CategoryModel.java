package sample.gui.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Category;
import sample.be.Movie;
import sample.bll.PMCManager;
import sample.bll.Logicfacade;

import java.io.IOException;
import java.sql.SQLException;

public class CategoryModel {

    private ObservableList<Category> categories;
    private Logicfacade logicfacade;

    public CategoryModel() throws IOException, SQLException {
        categories = FXCollections.observableArrayList();
        logicfacade = new PMCManager();
        categories.addAll(logicfacade.getAllCategories());
    }

    /**
     * Gets a list of the categories
     * @return
     */
    public ObservableList<Category> getCategories()
    {
        return categories;
    }

    /**
     * Method for adding a category to the database
     * @param name
     */
    public void addCategory(String name) throws SQLException{
        Category category = logicfacade.addCategory(name);
        categories.add(category);
    }

    /**
     * Method for deleting a category in the database
     * @param categoryToDelete
     */
    public void deleteCategory(Category categoryToDelete) throws SQLException{
        logicfacade.deleteCategory(categoryToDelete);
        categories.remove(categoryToDelete);
    }
}
