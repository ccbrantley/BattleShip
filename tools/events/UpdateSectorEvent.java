package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/28/2019
 */

public class UpdateSectorEvent {

    private int row;
    private int column;
    private int rotation;
    private String newId;

    public UpdateSectorEvent (int _row, int _column, int _rotation, String _newId) {
        this.row = _row;
        this.column = _column;
        this.rotation = _rotation;
        this.newId = _newId;
    }

//*****************     GETTERS     *******************

    public int getRow () {
        return this.row;
    }

    public int getColumn () {
        return this.column;
    }

    public String getNewId () {
        return this.newId;
    }

    public int getRotation () {
        return this.rotation;
    }

//*****************     GETTERS     *******************

    public void setRow (int _row) {
        this.row = _row;
    }

    public void setColumn (int _column) {
        this.column = _column;
    }

    public void setNewId (String _newId) {
        this.newId = _newId;
    }

    public void setRotation (int _rotation) {
        this.rotation = _rotation;
    }

}