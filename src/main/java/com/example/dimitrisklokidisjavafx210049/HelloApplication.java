package com.example.dimitrisklokidisjavafx210049;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 450);
        stage.setTitle("word hunter!");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);

        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}