package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 */

import battleship.tools.EventBus;
import battleship.tools.events.*;

public class BattleShipPlayer {
    public BattleShipPlayer(int _playerType){
        this.playerType = _playerType;
        this.battleShipFleet = new BattleShipFleet();
    }
    private final int playerType;
    private BattleShipFleet battleShipFleet;
    private final EventBus eventBus = BattleShipGame.eventBus;

    //Enumerators
    public static final int HUMAN = 0;
    public static final int BOT = 1;

    public void randomizeShips() {
        BattleShipFleet.getFleetOfShips().forEach(ship -> {
            ship.moveShip(BattleShipShip.RANDOM, BattleShipShip.RANDOM);
            ship.setShipOrientation(BattleShipShip.generateRandomOrientation());
        });
    }
//*****************     GETTERS     *******************

    public int getPlayerType() {
        return playerType;
    }

    public BattleShipFleet getBattleShipFleet() {
        return battleShipFleet;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

//*****************     SETTERS     *******************

    public void setBattleShipFleet(BattleShipFleet battleShipFleet) {
        this.battleShipFleet = battleShipFleet;
    }

}
