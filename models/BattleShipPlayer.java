package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/08/2019
 * BattleShipPlayer serves to hold the objects that are representative of a
 * battleship player. BattleShipPlayer holds a fleet of ships respective of
 * the player type. The variations of these BattleShipFleets will be for home
 * and away team. The main difference of these variations will be the code required
 * to update the GUI.
 */

import battleship.tools.EventBus;

public class BattleShipPlayer {
    // _playerType will either instantiate BattleShipPlayer with
    // _playerType 0 for the local player
    // _playerType 1 for the opponent player
    public BattleShipPlayer(int _playerType){
        this.playerType = _playerType;
        this.battleShipFleet = new BattleShipFleet();
    }

    private final int playerType;
    private BattleShipFleet battleShipFleet;
    private final EventBus eventBus = BattleShipGame.eventBus;

    //Enumerators
    public static final int LOCAL = 0;
    public static final int OPPONENT = 1;

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
