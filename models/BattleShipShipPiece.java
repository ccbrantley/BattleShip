package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 */

import battleship.tools.events.*;
import battleship.tools.EventBus;

public class BattleShipShipPiece {
    public BattleShipShipPiece (int _pieceIndex, int _orientation, String _shipId) {
        this.piece = _pieceIndex;
        this.rotation = (_orientation == 0) ? 0 : 90;
        this.id = _shipId;
        this.rowIndex = 0;
        this.columnIndex = _pieceIndex;
    }

    private boolean hit = false;
    private int rowIndex;
    private int columnIndex;
    private int piece;
    private int rotation;
    private String id;
    private EventBus eventBus = BattleShipGame.eventBus;

//*****************     GETTERS     *******************

    public boolean isHit() {
        return hit;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getPiece() {
        return piece;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(this.rowIndex, this.columnIndex);
    }

    public int getRotation() {
        return rotation;
    }

    public String getId() {
        return id;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

//*****************     SETTERS     *******************

    public void setIndexes(int _rowIndex, int _columnIndex) {
        this.setRowIndex(_rowIndex);
        this.setColumnIndex(_columnIndex);
    }

    public void setHit(boolean _hit) {
        this.hit = _hit;
    }

    private void setRowIndex(int _rowIndex) {
        this.rowIndex = _rowIndex;
    }

    private void setColumnIndex(int _columnIndex) {
        this.columnIndex = _columnIndex;
    }

}
