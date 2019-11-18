package battleship.weather.models;
/**
 * This class handles all functionality related to weather.
 * @author Andrew Braswell Last Updated: 11/13/2019
 */

import battleship.weather.api.WeatherApiTranslator;
import battleship.weather.util.Location;

public class Weather {
    protected final static WeatherApiTranslator API = new WeatherApiTranslator();

    protected int location;
    protected int windDirection;
    protected double windSpeed;
    protected double temperature;

    public int getLocation () {
        return this.location;
    }

    public String getLocationName () {
        return Location.getName(this.location);
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

    /**
     * This method fetches weather information using the API.
     * @param _location An enumerated location.
     * @return
     */
    public static Weather loadWeatherByLocation (int _location) {
        Weather weather = new Weather();
        weather.location = _location;

        Weather.API.fetchWeatherByLocation(_location);

        weather.windDirection = Weather.API.loadWindDirection();
        weather.temperature = Weather.API.loadTemperature();
        weather.windSpeed = Weather.API.loadWindSpeed();

        if (weather.windDirection == -1) {
            return null;
        }

        return weather;
    }
}
