package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/26/2019
 * BattleShipFleet holds access to all battle ships of a single player.
 * This class contains generic procedures that
 * require finding a specific ship.
 */

import java.util.ArrayList;

public class BattleShipFleet {

    /**Creates one of every ship type by incrementing through
     * the range of enumerated values and moves the ship to
     * a random position.
     */
    public BattleShipFleet () {
        for (int shipType = 0; shipType < 5; shipType++) {
            BattleShipShip curShip = new BattleShipShip(shipType, (int)(Math.random() * 2), this.fleetOfShips);
            curShip.moveShip(BattleShipShip.RANDOM, BattleShipShip.RANDOM);
            this.fleetOfShips.add(shipType, curShip);
        }
    }

    // Serves as an reference to all ships of a fleet.
    private final ArrayList<BattleShipShip> fleetOfShips = new ArrayList();

    // Moves ship to a row/column based on current position plus increment.
    public void moveShipIncrementally (int _rowInc, int _columnInc, int _type) {
        BattleShipShip battleShip = this.fleetOfShips.get(_type);
        battleShip.moveShipIncrementally(_rowInc, _columnInc);
    }

    // Moves ship to a specific row/column.
    public void moveShip (int _row, int _column, String _type) {
        BattleShipShip battleShip = this.fleetOfShips.get(BattleShipShip.convertShipIdToType(_type));
        battleShip.moveShip(_row, _column);
    }

    // Moves all ships to a new location.
    public void randomizeShips () {
        this.getFleetOfShips().forEach(ship -> {
            ship.setShipOrientation(BattleShipShip.generateRandomOrientation());
            ship.moveShip(BattleShipShip.RANDOM, BattleShipShip.RANDOM);
        });
    }

    // Throws all sector update events with the ship coordinates.
    public final void throwAllPositionUpdateEvents () {
        this.getFleetOfShips().forEach(ship -> {
            ship.getAllSectorUpdateEvents().forEach(event -> {
                BattleShipGame.getEventBus().throwEvent(event);
            });
        });
    }

    // Method for receiving fire from another player.
    public boolean receiveFire (Coordinate _coordinate) {
        int fireRow = _coordinate.getRow();
        int fireColumn = _coordinate.getColumn();
        for (BattleShipShip  ship : this.fleetOfShips) {
            for(BattleShipShipPiece piece : ship.getShipPieces()){
                if(fireRow == piece.getRowIndex()) {
                    if (fireColumn == piece.getColumnIndex()) {
                        piece.setHit(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

//*****************     GETTERS     *******************

    public ArrayList<BattleShipShip> getFleetOfShips () {
        return this.fleetOfShips;
    }

}