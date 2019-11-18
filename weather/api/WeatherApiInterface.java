package battleship.weather.api;
/**
 * This interface defines the methods that need to be implemented for any
 * valid weather API translator class.
 *
 * @author Andrew Braswell Last Updated: 11/11/2019
 */
public interface WeatherApiInterface {
    /**
     * Fetches weather information from the API
     * @param _location An enumerated location.
     */
    public void fetchWeatherByLocation (int _location);

    public int loadWindDirection ();

    public double loadWindSpeed ();

    public double loadTemperature ();
}
