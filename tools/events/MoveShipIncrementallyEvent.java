package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 * Moves a ship by value increments.
 */

public class MoveShipIncrementallyEvent {

    private int row;
    private int column;

    public MoveShipIncrementallyEvent(int _row, int _column) {
        this.row = _row;
        this.column = _column;
    }

//*****************     GETTERS     *******************

    public int getRow () {
        return this.row;
    }

    public int getColumn () {
        return this.column;
    }

//*****************     SETTERS     *******************

    public void setRow (int _row) {
        this.row = _row;
    }

    public void setColumn (int _column) {
        this.column = _column;
    }

}