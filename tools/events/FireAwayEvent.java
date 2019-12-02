package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/28/2019
 * Event to throw when firing at a player.
 */

public class FireAwayEvent {

    private int destination;

    /**
     * @param _destination Enumerators LOCAL or OPPONENT from BattleShipPlayer.
     */
    public FireAwayEvent ( int _destination) {
        this.destination = _destination;
    }

//*****************     GETTERS     *******************

    public int getDestination () {
        return this.destination;
    }

//*****************     SETTERS     *******************

    public void setDestination (int _destination) {
        this.destination = _destination;
    }

}