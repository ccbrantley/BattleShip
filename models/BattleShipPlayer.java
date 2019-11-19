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

import battleship.models.interpreters.BattleShipBotAi;
import battleship.models.interpreters.BattleShipFleetLocalInterpreter;
import battleship.tools.EventBus;

public class BattleShipPlayer {
    /** This constructor is for human players only.
     *  It throws the event to update local view.
     * @param _playerType -> bot or human.
     * @param _playerTeam -> local or away.
     */
    public BattleShipPlayer(int _playerType, int _playerTeam) {
        this.playerType = _playerType;
        this.playerTeam = _playerTeam;
        this.battleShipFleet = new BattleShipFleet();
        if (playerTeam == BattleShipPlayer.LOCAL) {
            BattleShipGame.getEventBus().addListener(new BattleShipFleetLocalInterpreter(this));
            this.battleShipFleet.throwAllPositionUpdateEvents();
        }
    }

    /** This constructor is for bot players only.
     *  This constructor creates a bot AI.
     * @param _playerType -> bot or human.
     * @param _playerTeam -> local or away.
     * @param _difficulty -> AI difficulty.
     */
    public BattleShipPlayer(int _playerType, int _playerTeam, int _difficulty) {
        this(_playerType, _playerTeam);
        this.difficulty = _difficulty;
        BattleShipGame.getEventBus().addListener(new BattleShipBotAi(_difficulty));
    }

    private final int playerType;
    private final int playerTeam;
    private int difficulty = BattleShipPlayer.NULL;
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
    // Difficulty
    public static final int NULL = -1;
    public static final int EASY = 0;
    public static final int NORMAL = 1;
    public static final int HARD = 2;

//*****************     GETTERS     *******************

    public int getPlayerType() {
        return playerType;
    }

    public int getPlayerTeam() {
        return playerTeam;
    }

    public int getDifficulty() {
        return difficulty;
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

    public boolean isTurn() {
        return turn;
    }

//*****************     SETTERS     *******************

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setBattleShipFleet(BattleShipFleet battleShipFleet) {
        this.battleShipFleet = battleShipFleet;
    }

    public void setCurrentTarget(Coordinate currentTarget) {
        this.currentTarget = currentTarget;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

}
