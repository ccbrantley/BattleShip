package battleship.weather.util;
/**
 * This helper class enumerates locations.
 * It contains static methods which provide information on a given location.
 *
 * @author Andrew Braswell Last Updated: 11/13/2019
 */

public class Location {
    public static final int MIDWAY = 0;
    public static final int NORTH_SEA = 1;
    public static final int FALKLANDS = 2;
    public static final int TONKIN = 3;

    public static int getLatitude (int _location) {
        switch (_location) {
            case MIDWAY: return 28;
            case NORTH_SEA : return 56;
            case FALKLANDS: return -51;
            case TONKIN: return 19;
            default: {
                System.out.println("Error: Invalid location.");
                return 0;
            }
        }
    }

    public static int getLongitude (int _location) {
        switch (_location) {
            case MIDWAY: return -177;
            case NORTH_SEA: return 3;
            case FALKLANDS: return -57;
            case TONKIN: return 106;
            default: return 0;
        }
    }

    public static String getName (int _location) {
        switch (_location) {
            case MIDWAY: return "Midway Atoll";
            case NORTH_SEA: return "The North Sea";
            case FALKLANDS: return "Las Islas Malvinas";
            case TONKIN: return "The Gulf of Tonkin";
            default: return "Null Island";
        }
    }
}
