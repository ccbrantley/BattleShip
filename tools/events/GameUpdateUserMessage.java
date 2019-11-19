package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/17/2019
 * Event to throw when informing
 * user about game mechanics.
 */

public class GameUpdateUserMessage {
    public GameUpdateUserMessage(String _message) {
        this.message = "[Game Update]: " + _message;
    }
    private final String message;

//*****************     GETTERS     *******************

    public String getMessage() {
        return message;
    }

}
