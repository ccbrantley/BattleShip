package battleship.weather.test;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Last Updated: 12/09/2019
 * This is the unit test for the Location class.
 */

import battleship.weather.util.Location;


public class LocationTest {
    /**
     * Main static method for running this class independently of the rest of the system.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        runInvalidCoordinateTests();
    }

    /**
     * Tests that the makeLocation() method returns a default location when passed invalid coordinates.
     */
    private static void runInvalidCoordinateTests () {
        final String rdl = " returns default location.";
        //These locations are made with invalid latitude values for testing.
        Location bigLat = Location.makeLocation(9999, 0, "big lat test");
        Location smallLat = Location.makeLocation(-9999, 0, "small lat test");
        Location lat180 = Location.makeLocation(180, 0, "180 lat test");
        Location latNeg180 = Location.makeLocation(-180, 0, "-180 lat test");

        //These locations are made with invalid longitude values for testing.
        Location bigLon = Location.makeLocation(0, 9999, "big lon test");
        Location smallLon = Location.makeLocation(0, -9999, "small lon test");
        Location lon180 = Location.makeLocation(0, 180, "180 lon test");
        Location lonNeg180 = Location.makeLocation(0, -180, "-180 lon test");

        //Print the latitude tests.
        printTestResultMessage(isDefaultLocation(bigLat), "Very large latitude" + rdl);
        printTestResultMessage(isDefaultLocation(smallLat), "Very small latitude" + rdl);
        printTestResultMessage(isDefaultLocation(lat180), " 180 latitude" + rdl);
        printTestResultMessage(isDefaultLocation(latNeg180), " -180 latitude" + rdl);

        //Print the longitude tests.
        printTestResultMessage(isDefaultLocation(bigLon), "Very large longitude" + rdl);
        printTestResultMessage(isDefaultLocation(smallLon), "Very small longitude" + rdl);
        printTestResultMessage(isDefaultLocation(lon180), " 180 longitude" + rdl);
        printTestResultMessage(isDefaultLocation(lonNeg180), " -180 longitude" + rdl);
    }

    /**
     * Helper method for checking that a location is the default (null island error) location.
     * @param _location
     * @return
     */
    private static boolean isDefaultLocation (Location _location) {
        if (_location.getLatitude() == 0 && _location.getLongitude() == 0 && _location.getName().equals(Location.DEFAULT_NAME))
            return true;
        return false;
    }

    /**
     * Prints the message for a test case.
     * @param _pass True if the test passed, false if it failed.
     * @param _testDescription Description of the test
     */
    private static void printTestResultMessage (boolean _pass, String _testDescription) {
        System.out.println( (_pass? "PASS: " : "FAIL: ") + _testDescription);
    }
}
