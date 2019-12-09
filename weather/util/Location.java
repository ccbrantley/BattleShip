package battleship.weather.util;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Last Updated: 12/03/2019
 * This class enumerates locations.
 * It can be instantiated to create an object that contains information about a location.
 */

import java.util.ArrayList;

public class Location {

    //Enumerated locations.  To add a new location, enumerate it here and then add its information to the getLocation() method below.
    //The values must start at 0 and increment by one each time, or else the getAllLocations() method will not work correctly.
    public static final int MIDWAY = 0;
    public static final int NORTH_SEA = 1;
    public static final int FALKLANDS = 2;
    public static final int TONKIN = 3;

    //The default name used when invalid location paramters are used.
    public static final String DEFAULT_NAME = "Null Island";

    //Instance variables
    private final int latitude;
    private final int longitude;
    private final String name;

    /**
     * This constructs a location with the default values.  This is only called when makeLocation() is called with invalid parameters.
     */
    private Location () {
        this.latitude = 0;
        this.longitude = 0;
        this.name = DEFAULT_NAME;
    }

    /**
     * This constructs a location with the given values.  This is only called through the makeLocation() static method.
     */
    private Location (int _latitude, int _longitude, String _name) {
        this.latitude = _latitude;
        this.longitude = _longitude;
        this.name = _name;
    }

    /**
     * Constructs and returns a Location object.  If the coordinate parameters are invalid, it returns the default Location object.
     */
    public static Location makeLocation (int _latitude, int _longitude, String _name) {
        if (isValidCoordinate(_latitude) && isValidCoordinate(_longitude))
            return new Location(_latitude, _longitude, _name);
        else
        {
            return new Location();
        }
    }

    /** This static method returns a Location object.  To program a new location, enumerate it above then add its case below.
     *  @param _enumLocation An enumerated location.
     *  @return A Location object with hard-coded information about the enumerated location.  If the parameter is not an enumerated location, returns the Location object for Null Island.
     */
    public static Location getLocation (int _enumLocation) {
        switch (_enumLocation) {
            case Location.MIDWAY:
                return makeLocation(28, -177, "Midway Atoll");
            case Location.NORTH_SEA :
                return makeLocation(56, 3, "The North Sea");
            case Location.FALKLANDS:
                return makeLocation(-51, -57, "The Falkland Islands");
            case Location.TONKIN:
                return makeLocation(19, 106, "The Gulf of Tonkin");
            default:
                return new Location();
        }
    }

    /**
     * This static method allows easy access to information on all enumerated locations.
     * @return An ArrayList containing Location objects for all enumerated locations.
     */
    public static ArrayList<Location> getAllLocations () {
        ArrayList<Location> locations = new ArrayList<>();
        int enumLocation = 0;
        Location location;
        while (true) {
            location = getLocation(enumLocation);
            if (location.name.equals(DEFAULT_NAME)) {
                locations.add(location);
                break;
            }
            locations.add(location);
            enumLocation++;
        }
        return locations;
    }

    /**
     * This helper method checks whether or not a given coordinate is valid.
     * @param _ltude Latitude or Longitude
     * @return True if valid, false if invalid.
     */
    private static boolean isValidCoordinate (int _ltude) {
        return (_ltude > -180 && _ltude < 180);
    }

//*****************     GETTERS     *******************

    public int getLatitude () {
        return this.latitude;
    }

    public int getLongitude () {
        return this.longitude;
    }

    public String getName () {
        return this.name;
    }

}
