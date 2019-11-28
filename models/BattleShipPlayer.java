package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/28/2019
 * BattleShipPlayer serves to hold the objects that are representative of a
 * battleship player. BattleShipPlayer holds a fleet of ships respective of
 * the player type. The variations of these BattleShipFleets will be for home
 * and away team. The main difference of these variations will be the code required
 * to update the GUI.
 */

import battleship.models.interpreters.BattleShipBotAi;
import battleship.models.interpreters.BattleShipFleetLocalInterpreter;

public class BattleShipPlayer {

    private final int playerType;
    private final int playerTeam;
    private int difficulty = BattleShipPlayer.NULL;
    private BattleShipFleet battleShipFleet;
    private Coordinate currentTarget = new Coordinate(0, 0);
    private boolean turn = true;

    // Enumerators -> type.
    public static final int HUMAN = 0;
    public static final int BOT = 1;
    // Enumerators -> Location.
    public static final int LOCAL = 0;
    public static final int AWAY = 1;
    // Enumerators -> Difficulty.
    public static final int NULL = -1;
    public static final int EASY = 0;
    public static final int NORMAL = 1;
    public static final int HARD = 2;

    /** This constructor is for bot players only.
     *  This constructor creates a bot AI.
     * @param _playerType -> bot or human.
     * @param _playerTeam -> local or away.
     * @param _difficulty -> AI difficulty.
     */
    public BattleShipPlayer (int _playerType, int _playerTeam, int _difficulty) {
        this(_playerType, _playerTeam);
        this.difficulty = _difficulty;
        BattleShipGame.getEventBus().addListener(new BattleShipBotAi(this.BattleShipPlayer, this.difficulty));
    }

    /** This constructor is for human players only.
     *  Interpreter throws the event to update local view.
     * @param _playerType -> bot or human.
     * @param _playerTeam -> local or away.
     */
    public BattleShipPlayer (int _playerType, int _playerTeam) {
        this.playerType = _playerType;
        this.playerTeam = _playerTeam;
        this.battleShipFleet = new BattleShipFleet();
        if (playerTeam == BattleShipPlayer.LOCAL) {
            BattleShipGame.getEventBus().addListener(new BattleShipFleetLocalInterpreter(this));
            this.battleShipFleet.throwAllPositionUpdateEvents();
        }
    }

//*****************     GETTERS     *******************

    public int getPlayerType () {
        return this.playerType;
    }

    public int getPlayerTeam () {
        return this.playerTeam;
    }

    public int getDifficulty () {
        return this.difficulty;
    }

    public BattleShipFleet getBattleShipFleet () {
        return this.battleShipFleet;
    }

    public Coordinate getCurrentTarget () {
        return this.currentTarget;
    }

    public boolean isTurn () {
        return this.turn;
    }

//*****************     SETTERS     *******************

    public void setDifficulty (int _difficulty) {
        this.difficulty = _difficulty;
    }

    public void setBattleShipFleet (BattleShipFleet _battleShipFleet) {
        this.battleShipFleet = _battleShipFleet;
    }

    public void setCurrentTarget (Coordinate _currentTarget) {
        this.currentTarget = _currentTarget;
    }

    public void setTurn (boolean _turn) {
        this.turn = _turn;
    }

}