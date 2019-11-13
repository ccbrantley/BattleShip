package battleship.weather.weatherapp;
/**
 * This class is for demonstrating the use of a weather api.
 *
 * @author Andrew Braswell Last Updated: 11/13/2019
 */
import battleship.weather.models.Weather;
import battleship.weather.util.Location;


public class WeatherApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Weather x = Weather.loadWeatherByLocation(Location.MIDWAY);

        System.out.println("The temperature at " + x.getLocationName() + " is " + x.getTemperature());
    }
}
