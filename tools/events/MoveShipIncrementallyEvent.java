package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/28/2019
 * Moves a ship by value increments.
 */

public class MoveShipIncrementallyEvent extends MoveShipEvent {

    public MoveShipIncrementallyEvent(int _row, int _column, String _shipType) {
        super(_row, _column, _shipType);
    }

}