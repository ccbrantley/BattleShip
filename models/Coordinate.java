package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/27/2019
 * Coordinate represents a single (x,y) pair.
 */

public class Coordinate {

    private int row;
    private int column;

    public Coordinate (int _row, int _column) {
        this.row = _row;
        this.column = _column;
    }

    @Override
    public boolean equals(Object _other) {
        if(_other instanceof Coordinate){
            Coordinate other = (Coordinate)_other;
            if (other.getRow() == this.getRow()) {
                if (other.getColumn() == this.getColumn()) {
                    return true;
                }
            }
        }
        return false;
    }

//*****************     GETTERS     *******************

    public int getRow () {
        return this.row;
    }

    public int getColumn () {
        return this.column;
    }

//*****************     SETTERS     *******************

    public void setRow (int row) {
        this.row = row;
    }

    public void setColumn (int column) {
        this.column = column;
    }

}