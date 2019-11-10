package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 */

public class UpdateSectorEvent {
    public UpdateSectorEvent(int _row, int _column, int _rotation, String _newId) {
        this.row = _row;
        this.column = _column;
        this.rotation = _rotation;
        this.newId = _newId;
    }
    private int row;
    private int column;
    private int rotation;
    private String newId;

//*****************     GETTERS     *******************

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getNewId() {
        return newId;
    }

    public int getRotation() {
        return rotation;
    }

//*****************     GETTERS     *******************

    public void setRow(int _row) {
        this.row = _row;
    }

    public void setColumn(int _column) {
        this.column = _column;
    }

    public void setNewId(String _newId) {
        this.newId = _newId;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

}
