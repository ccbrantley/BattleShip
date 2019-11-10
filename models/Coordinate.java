package battleship.models;

public class Coordinate {
    public Coordinate(int _row, int _column){
        this.row = _row;
        this.column = _column;
    }
    private int row;
    private int column;
//*****************     GETTERS     *******************

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

//*****************     SETTERS     *******************

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
