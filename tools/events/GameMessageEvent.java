package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/28/2019
 * Event to throw when informing
 * user about game mechanics.
 */

public class GameMessageEvent {

    private final String message;

    public GameMessageEvent (String _message) {
        this.message = "[Game Update]: " + _message;
    }

//*****************     GETTERS     *******************

    public String getMessage () {
        return this.message;
    }

}