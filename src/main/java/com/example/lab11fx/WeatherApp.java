package com.example.lab11fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class WeatherApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherAppController.class.getResource("weather.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WeatherApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
        //WeatherAppController.weatherUpdate();
    }
}