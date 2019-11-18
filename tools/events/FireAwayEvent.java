package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/17/2019
 * Event to throw when firing at a player.
 */
public class FireAwayEvent {

    /**
     * @param _destination LOCAL or OPPONENT from BattleShipPlayer
     */
    public FireAwayEvent ( int _destination) {
        this.destination = _destination;
    }

    private int destination;

//*****************     GETTERS     *******************

    public int getDestination() {
        return destination;
    }

//*****************     SETTERS     *******************

    public void setDestination(int destination) {
        this.destination = destination;
    }

}
