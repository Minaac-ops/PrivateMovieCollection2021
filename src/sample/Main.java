package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui/View/Warning.fxml"));
        primaryStage.setTitle("Why so serious ?");
        primaryStage.setScene(new Scene(root, 664, 486));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
