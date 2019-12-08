package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 12/03/2019
 * BattleShipFleet holds access to all battle ships of a single player.
 * This class contains generic procedures that
 * require finding a specific ship.
 */

import java.util.ArrayList;

public class BattleShipFleet {
    //Enumerators -> shot probability.
    public static int SHOTMISS = 0;
    public static int SHOTCHANGED = 1;
    public static int SHOTUNCHANGED =2;

    // Serves as an reference to all ships of a fleet.
    private final ArrayList<BattleShipShip> fleetOfShips = new ArrayList();
    private int liveShipCount;

    /**Creates one of every ship type by incrementing through
     * the range of enumerated values and moves the ship to
     * a random position.
     */
    public BattleShipFleet () {
        for (int shipType = 0; shipType < BattleShipShip.NUMBEROFSHIPTYPES; shipType++) {
            BattleShipShip curShip = new BattleShipShip(shipType, BattleShipShip.generateRandomOrientation(), this.fleetOfShips);
            curShip.moveShip(BattleShipShip.RANDOM, BattleShipShip.RANDOM);
            this.fleetOfShips.add(shipType, curShip);
            this.liveShipCount++;
        }
    }

    // Moves ship to a row/column based on current position plus increment.
    public void moveShipIncrementally (int _rowInc, int _columnInc, int _type) {
        if (_type == BattleShipShip.ERROR){
            return;
        }
        BattleShipShip battleShip = this.fleetOfShips.get(_type);
        battleShip.moveShipIncrementally(_rowInc, _columnInc);
    }

    // Moves ship to a specific row/column.
    public void moveShip (int _row, int _column, int _type) {
        if (_type == BattleShipShip.ERROR){
            return;
        }
        BattleShipShip battleShip = this.fleetOfShips.get(_type);
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
                        if (!piece.isHit()) {
                            piece.setHit(true);
                            if (ship.isSunk()) {
                                --this.liveShipCount;
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* Takes a random number from 0 - 1 and compares it to probability
     * of alteration. As wind speed increases the range of probable
     * alteration increases. We return whether the shot is missed, changed,
     * or unchanged for proper protocol handling.
     */
    public int calculateShotProbability (Coordinate _shot) {
        double upperLimit = 50;
        double windSpeed = BattleShipGame.getWeatherSelection().getWindSpeed();
        double missProbability = Math.random()  * 1;
        double xWindSpeed = BattleShipGame.getWeatherSelection().getXWindSpeed();
        double yWindSpeed = BattleShipGame.getWeatherSelection().getYWindSpeed();
        double alterProbability = (windSpeed / (windSpeed + upperLimit));
        if (missProbability < alterProbability) {
            int alteredColumn = _shot.getColumn() + 1;
            int alteredRow = _shot.getRow() + 1;
            if (xWindSpeed < 0) {
                alteredColumn -= 2;
            }
            if (yWindSpeed < 0) {
                alteredRow -= 2;
            }
            _shot.setColumn(alteredColumn);
            _shot.setRow(alteredRow);
            if (!BattleShipBoard.boardBoundaryCheck(_shot)) {
                return BattleShipFleet.SHOTMISS;
            }
            return BattleShipFleet.SHOTCHANGED;
        }
        return BattleShipFleet.SHOTUNCHANGED;
    }

//*****************     GETTERS     *******************

    public ArrayList<BattleShipShip> getFleetOfShips () {
        return this.fleetOfShips;
    }

    public int getLiveShipCount () {
        return this.liveShipCount;
    }

}