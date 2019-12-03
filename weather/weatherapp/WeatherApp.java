package battleship.weather.weatherapp;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Last Updated: 11/27/2019
 * This class is for demonstrating the use of a weather api.
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import battleship.weather.models.Weather;

public class WeatherApp {

    public static void main(String[] args) {
        DecimalFormat f = new DecimalFormat("#.##");
        ArrayList<Weather> weathers = Weather.loadWeathersForAllLocations();
        weathers.add(new Weather());
        for (Weather w : weathers) {
            System.out.println("The temperature in " + w.getLocationName() + " is " + w.getTemperature() + " F.");
            System.out.println("Windspeed: " + w.getWindSpeed() + ".  Wind direction: " + w.getWindDirection());
            System.out.println("The wind is blowing " + f.format(w.getXWindSpeed()) + " mph from the west and " + f.format(w.getYWindSpeed()) + " mph from the north. \n");
        }
    }

}