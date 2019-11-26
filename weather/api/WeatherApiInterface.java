package battleship.weather.api;
/**
 * This interface defines the methods that need to be implemented for any
 * valid weather API translator class.
 *
 * @author Andrew Braswell Last Updated: 11/26/2019
 */
import battleship.weather.util.Location;

public interface WeatherApiInterface {
    /**
     * Fetches weather information from the API
     * @param _location A location object.
     */
    public void fetchWeatherByLocation (Location _location);

    public int loadWindDirection ();

    public double loadWindSpeed ();

    public double loadTemperature ();
}
