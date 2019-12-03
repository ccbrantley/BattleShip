package battleship.weather.models;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Last Updated: 12/03/2019
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
    //Whether or not the weather object has successfully loaded the API.
    protected boolean hasData;

    //Enumerator -> error value.
    public static final int ERROR = -1;

    /**
     * Creates a blank Weather object with no weather data.
     */
    public Weather () {
        this.location = null;
        this.noData();
    }

    /** This method fetches weather information for a given Location using the API.
     *  @param _location A Location object.
     *  @return A Weather object.
     */
    public static Weather loadWeatherByLocation (Location _location) {
        Weather weather = new Weather();
        weather.location = _location;
        Weather.API.fetchWeatherByLocation(_location);
        if (Weather.API.loadWindDirection() != Weather.ERROR) {
            weather.windDirection = Weather.API.loadWindDirection();
            weather.temperature = Weather.API.loadTemperature();
            weather.windSpeed = Weather.API.loadWindSpeed();
            weather.hasData = true;
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

    /**
     * Sets the Weather object to have no weather data.
     */
    private void noData () {
        this.temperature = 0;
        this.windSpeed = 0;
        this.windDirection = 0;
        this.hasData = false;
    }

//*****************     GETTERS     *******************

    public Location getLocation () {
        return this.location;
    }

    public String getLocationName () {
        if (this.location == null)
            return "No Location";
        return this.location.getName() + (this.hasData? "" : " (No Data)");
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

    public boolean hasData () {
        return this.hasData;
    }

}
