package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 12/03/2019
 * Event to move a ship by value to a index.
 */

public class MoveShipEvent {

    private int row;
    private int column;
    private int shipType;

    public MoveShipEvent (int _row, int _column, int _shipType){
        this.row = _row;
        this.column = _column;
        this.shipType = _shipType;
    }

//*****************     GETTERS     *******************

    public int getRow () {
        return this.row;
    }

    public int getColumn () {
        return this.column;
    }

    public int getShipType () {
        return this.shipType;
    }

//*****************     SETTERS     *******************

    public void setRow (int _row) {
        this.row = _row;
    }

    public void setColumn (int _column) {
        this.column = _column;
    }

    public void setShipType (int _shipType) {
        this.shipType = _shipType;
    }

}