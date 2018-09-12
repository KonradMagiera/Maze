package gui;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class MazeMain extends Application{
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/controller/layout.fxml"));
        BorderPane pane = loader.load();

        Scene scene = new Scene(pane, 590, 690);
        
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Maze finder");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
