package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/29/2019
 * Event to throw when ship hit.
 */

public class ShipHitEvent {

    private int row;
    private int column;
    // Player who's ship got hit.
    private int player;

    public ShipHitEvent (int _row, int _column, int _player) {
        this.row = _row;
        this.column = _column;
        this.player = _player;
    }
//*****************     GETTERS     *******************

    public int getRow () {
        return this.row;
    }

    public int getColumn () {
        return this.column;
    }

    public int getPlayer () {
        return this.player;
    }

//*****************     SETTERS     *******************

    public void setRow (int _row) {
        this.row = _row;
    }

    public void setColumn (int _column) {
        this.column = _column;
    }

    public void setPlayer (int _player) {
        this.player = _player;
    }

}