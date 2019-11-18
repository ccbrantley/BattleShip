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

import battleship.tools.BattleShipBotInterpreter;
import battleship.tools.BattleShipHumanInterpreter;
import battleship.tools.EventBus;

public class BattleShipPlayer {
    /**
     * _playerType will either instantiate BattleShipPlayer with
     * _playerType 0 for the HUMAN player
     * _playerType 1 for the BOT player
     * @param _playerType
     * @param _playerTeam
     */
    public BattleShipPlayer(int _playerType, int _playerTeam){
        this.playerType = _playerType;
        this.playerTeam = _playerTeam;
        if (_playerType == BattleShipPlayer.HUMAN) {
            BattleShipGame.getEventBus().addListener(new BattleShipHumanInterpreter(this));
        }
        else {
            BattleShipGame.getEventBus().addListener(new BattleShipBotInterpreter(this));
        }
        this.battleShipFleet = new BattleShipFleet();
    }

    private final int playerType;
    private final int playerTeam;
    private BattleShipFleet battleShipFleet;
    private final EventBus eventBus = BattleShipGame.eventBus;
    private Coordinate currentTarget = new Coordinate(0,0);
    private boolean turn = true;

    //Enumerators
    // Type
    public static final int HUMAN = 0;
    public static final int BOT = 1;
    // Location
    public static final int LOCAL = 0;
    public static final int AWAY = 1;

//*****************     GETTERS     *******************

    public int getPlayerType() {
        return playerType;
    }

    public int getPlayerTeam() {
        return playerTeam;
    }

    public boolean isTurn() {
        return turn;
    }

    public BattleShipFleet getBattleShipFleet() {
        return battleShipFleet;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Coordinate getCurrentTarget() {
        return currentTarget;
    }

//*****************     SETTERS     *******************

    public void setBattleShipFleet(BattleShipFleet _battleShipFleet) {
        this.battleShipFleet = _battleShipFleet;
    }

    public void setTurn(boolean _turn) {
        this.turn = _turn;
    }

    public void setCurrentTarget(Coordinate _currentTarget) {
        this.currentTarget = _currentTarget;
    }

}
