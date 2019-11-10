package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 */

import java.util.ArrayList;

public class BattleShipFleet {
    public BattleShipFleet() {
        for (int shipType = 0; shipType < 5; shipType++) {
            BattleShipShip curShip = new BattleShipShip(shipType, (int)(Math.random() * 2));
            curShip.moveShip(BattleShipShip.RANDOM, BattleShipShip.RANDOM);
            BattleShipFleet.fleetOfShips.add(shipType, curShip);
        }
    }

    private static final ArrayList<BattleShipShip> fleetOfShips = new ArrayList();

    public void moveShipIncrementally (int _rowInc, int _columnInc, String type) {
        BattleShipShip battleShip = BattleShipFleet.fleetOfShips.get(BattleShipShip.convertShipIdToType(type));
        battleShip.moveShipIncrementally(_rowInc, _columnInc);
    }

    public void moveShip (int _row, int _column, String type) {
        BattleShipShip battleShip = BattleShipFleet.fleetOfShips.get(BattleShipShip.convertShipIdToType(type));
        battleShip.moveShip(_row, _column);
    }

    //Getters
    public static ArrayList<BattleShipShip> getFleetOfShips() {
        return BattleShipFleet.fleetOfShips;
    }

}
