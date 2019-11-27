package battleship.weather.api;

/* @author Area 51 Block Party:
* Andrew Braswell
* Last Updated: 11/26/2019
* This translator class acts as a central connector for all Weather API connections.
* Do not call any implemented Weather API classes directly.
* Roue all requests through this central class.
*/

import battleship.weather.util.Location;

public class WeatherApiTranslator implements WeatherApiInterface {

    protected static final WeatherApiInterface API = new OpenWeatherApi();

    @Override
    public void fetchWeatherByLocation (Location _location) {
        WeatherApiTranslator.API.fetchWeatherByLocation(_location);
    }

    @Override
    public int loadWindDirection () {
        return WeatherApiTranslator.API.loadWindDirection();
    }

    @Override
    public double loadWindSpeed () {
        return WeatherApiTranslator.API.loadWindSpeed();
    }

    @Override
    public double loadTemperature () {
        return WeatherApiTranslator.API.loadTemperature();
    }

}
