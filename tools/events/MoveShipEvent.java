package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 * Event to move a ship by value to a index.
 */

public class MoveShipEvent {
    public MoveShipEvent(int _row, int _column, String _type){
        this.row = _row;
        this.column = _column;
        this.type = _type;
    }

    private int row;
    private int column;
    private String type;

//*****************     GETTERS     *******************

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getType() {
        return type;
    }

//*****************     SETTERS     *******************

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setType(String type) {
        this.type = type;
    }

}