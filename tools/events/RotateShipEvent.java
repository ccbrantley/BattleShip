package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 * Rotate Ship Event.
 */

public class RotateShipEvent {
    public RotateShipEvent(String _type) {
        this.type = _type;
    }

    private String type;

//*****************     GETTERS     *******************

    public String getType() {
        return type;
    }

//*****************     SETTERS     *******************

    public void setType(String type) {
        this.type = type;
    }

}
