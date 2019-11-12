package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
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
    public BattleShipFleet() {
        for (int shipType = 0; shipType < 5; shipType++) {
            BattleShipShip curShip = new BattleShipShip(shipType, (int)(Math.random() * 2));
            curShip.moveShip(BattleShipShip.RANDOM, BattleShipShip.RANDOM);
            this.fleetOfShips.add(shipType, curShip);
        }
    }

    // fleetOfShips serves as an reference to all ships of a fleet.
    // Note: Find way to make this nonstatic.
    private static final ArrayList<BattleShipShip> fleetOfShips = new ArrayList();

    /**Moves ship to a row/column based on current position plus increment.
     * @param _rowInc
     * @param _columnInc
     * @param _type ship type
     */
    public void moveShipIncrementally (int _rowInc, int _columnInc, String _type) {
        BattleShipShip battleShip = this.fleetOfShips.get(BattleShipShip.convertShipIdToType(_type));
        battleShip.moveShipIncrementally(_rowInc, _columnInc);
    }

    /** Moves ship to a specific row/column.
     * @param _row
     * @param _column
     * @param _type ship type
     */
    public void moveShip (int _row, int _column, String _type) {
        BattleShipShip battleShip = this.fleetOfShips.get(BattleShipShip.convertShipIdToType(_type));
        battleShip.moveShip(_row, _column);
    }

    /** Moves all ships to a new location.
     */
    public void randomizeShips() {
        this.getFleetOfShips().forEach(ship -> {
            ship.setShipOrientation(BattleShipShip.generateRandomOrientation());
            ship.moveShip(BattleShipShip.RANDOM, BattleShipShip.RANDOM);
        });
    }

//*****************     GETTERS     *******************

    public static ArrayList<BattleShipShip> getFleetOfShips() {
        return BattleShipFleet.fleetOfShips;
    }

//*****************     SETTERS     *******************

}
