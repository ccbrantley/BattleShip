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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        ArrayList<Weather> weathers = Weather.loadWeathersForAllLocations();
        weathers.add(new Weather());
        for (Weather location : weathers) {
            System.out.println("The temperature in " + location.getLocationName() + " is " + location.getTemperature() + " F.");
            System.out.println("Windspeed: " + location.getWindSpeed() + ".  Wind direction: " + location.getWindDirection());
            System.out.println("The wind is blowing " + decimalFormat.format(location.getXWindSpeed()) + " mph from the west and " + decimalFormat.format(location.getYWindSpeed()) + " mph from the north. \n");
        }
    }

}