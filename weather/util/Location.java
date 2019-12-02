package battleship.weather.util;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Last Updated: 11/27/2019
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
    //Instance variables
    private final int latitude;
    private final int longitude;
    private final String name;

    public Location (int _latitude, int _longitude, String _name) {
        this.latitude = _latitude;
        this.longitude = _longitude;
        this.name = _name;
    }

    /** This static method returns a Location object.  To program a new location, enumerate it above then add its case below.
     *  @param _enumLocation An enumerated location.
     *  @return A Location object with hard-coded information about the enumerated location.  If the parameter is not an enumerated location, returns the Location object for Null Island.
     */
    public static Location getLocation (int _enumLocation) {
        switch (_enumLocation) {
            case Location.MIDWAY:
                return new Location(28, -177, "Midway Atoll");
            case Location.NORTH_SEA :
                return new Location(56, 3, "The North Sea");
            case Location.FALKLANDS:
                return new Location(-51, -57, "The Falkland Islands");
            case Location.TONKIN:
                return new Location(19, 106, "The Gulf of Tonkin");
            default:
                return new Location(0, 0, "Null Island");
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
            if (location.name.equals("Null Island")) {
                break;
            }
            locations.add(location);
            enumLocation++;
        }
        return locations;
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