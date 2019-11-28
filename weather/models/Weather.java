package battleship.weather.models;

/* @author Area 51 Block Party:
 * Andrew Braswell, Christopher Brantley
 * Last Updated: 11/27/2019
 * This class handles all functionality related to weather.
 */

import java.util.ArrayList;
import battleship.weather.api.WeatherApiTranslator;
import battleship.weather.util.Location;

public class Weather {

    protected final static WeatherApiTranslator API = new WeatherApiTranslator();
    //The Location of the weather.
    protected Location location;
    //The meteorological wind direction in degrees.  Measured clockwise from north, the direction from which the wind is blowing.
    protected int windDirection;
    //The windspeed in miles per hour.
    protected double windSpeed;
    //The temperature in Fahrenheit.
    protected double temperature;

    //Enumerator
    public static final int ERROR = -1;

    /** This method fetches weather information for a given Location using the API.
     *  @param _location A Location object.
     *  @return A Weather object.
     */
    public static Weather loadWeatherByLocation (Location _location) {
        Weather weather = new Weather();
        weather.location = _location;
        Weather.API.fetchWeatherByLocation(_location);
        weather.windDirection = Weather.API.loadWindDirection();
        weather.temperature = Weather.API.loadTemperature();
        weather.windSpeed = Weather.API.loadWindSpeed();
        if (weather.windDirection == Weather.ERROR) {
            return null;
        }
        return weather;
    }

    /** This method fetches weather information for every enumerated Location using the API.
     *  @return An ArrayList of Weather objects.
     */
    public static ArrayList<Weather> loadWeathersForAllLocations () {
        ArrayList<Weather> weathers = new ArrayList();
        Location.getAllLocations().forEach( (location) -> {
            weathers.add(Weather.loadWeatherByLocation(location));
        });
        return weathers;
    }

//*****************     GETTERS     *******************

    public Location getLocation () {
        return this.location;
    }

    public String getLocationName () {
        return this.location.getName();
    }

    public int getWindDirection () {
        return this.windDirection;
    }

    public double getWindSpeed () {
        return this.windSpeed;
    }

    public double getTemperature () {
        return this.temperature;
    }

    public double getXWindSpeed () {
        return this.windSpeed * Math.sin(this.windDirection);
    }

    public double getYWindSpeed () {
        return this.windSpeed * Math.cos(this.windDirection);
    }

}