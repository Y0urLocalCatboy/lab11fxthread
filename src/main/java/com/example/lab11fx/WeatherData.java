package com.example.lab11fx;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

    private  float temperature;
    private  float humidity;
    private  float pressure;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    private List<Observer> observers = new ArrayList<>();





    @Override
    public void registerObserver(Observer o) {
        if (! observers.contains(o ))observers.add(o);

    }

    @Override
    public void removeObserver(Observer o) {

        if(observers.contains(o)) observers.remove(o);

    }

    @Override
    public void notifyObserver() {
        for(Observer o : observers)
            o.update(temperature,humidity,pressure);
    }

    public void measurementChanged() throws URISyntaxException, IOException, InterruptedException {

        String apiKey = "e8050a47998aea5522fc77aa6945e0fe";
        String city = "Wroclaw,pl";
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request =
                HttpRequest.newBuilder(
                                new URI("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" +apiKey + "&units=metric"))
                        .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.toString());
        //System.out.println(response.body());

        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
        //System.out.println(jsonObject);

        JsonObject main = jsonObject.getAsJsonObject("main");

        temperature = (float) (main.get("temp").getAsDouble());
        pressure = main.get("pressure").getAsInt();
        humidity = main.get("humidity").getAsInt();
        notifyObserver();
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", observers=" + observers +
                '}';
    }
}