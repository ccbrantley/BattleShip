package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/29/2019
 * Event to throw when ship hit.
 */

public class UpdatePinEvent {

    private int row;
    private int column;
    private int player;
    private String color;

    public UpdatePinEvent (int _row, int _column, int _player, String _color) {
        this.row = _row;
        this.column = _column;
        this.player = _player;
        this.color = _color;
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

    public String getColor () {
        return this.color;
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

    public void setColor (String _color) {
        this.color = _color;
    }

}