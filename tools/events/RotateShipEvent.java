package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/28/2019
 * Rotate Ship Event.
 */

public class RotateShipEvent {

    private int shipType;

    public RotateShipEvent (int _shipType) {
        this.shipType = _shipType;
    }

//*****************     GETTERS     *******************

    public int getShipType () {
        return this.shipType;
    }

//*****************     SETTERS     *******************

    public void setShipType (int _shipType) {
        this.shipType = _shipType;
    }

}