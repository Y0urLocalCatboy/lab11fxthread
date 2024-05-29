package com.example.lab11fx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URISyntaxException;

public class WeatherAppController {

    @FXML
    private Label hum;

    @FXML
    private Label press;

    @FXML
    private Label temp;

//    @FXML
//    private Button weatherUpdate;
    public void initialize() {
        startWeatherUpdateThread();
    }
//    @FXML
//    void weatherUpdate(ActionEvent event) throws URISyntaxException, IOException, InterruptedException, URISyntaxException, IOException {
//        WeatherData weatherData= new WeatherData();
//        CurrentWeatherDisplay currentWeatherDisplay1= new CurrentWeatherDisplay();
//        weatherData.registerObserver(currentWeatherDisplay1);
//
//        weatherData.measurementChanged();
//        System.out.println(weatherData);
//        currentWeatherDisplay1.display();
//        String text = "Humidity: " + weatherData.getHumidity();
//        hum.setText(text + "%");
//        text = "Pressure: " + weatherData.getPressure();
//        press.setText(text + "HPa");
//        text = "Temperature: " + weatherData.getTemperature();
//        temp.setText(text + "C");
//
//    }
    private void startWeatherUpdateThread() {
        Thread weatherUpdateThread = new Thread(() -> {
            while (true) {
                WeatherData weatherData= new WeatherData();
                CurrentWeatherDisplay currentWeatherDisplay1= new CurrentWeatherDisplay();
                weatherData.registerObserver(currentWeatherDisplay1);

                try {
                    weatherData.measurementChanged();
                } catch (URISyntaxException | IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Platform.runLater(() -> press.setText("Pressure: " + weatherData.getPressure() + "HPa"));
                Platform.runLater(() -> hum.setText("Humidity: " + weatherData.getHumidity() + "%"));
                Platform.runLater(() -> temp.setText("Temperature: " + weatherData.getTemperature() + "C"));

                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        weatherUpdateThread.start();
    }


}
