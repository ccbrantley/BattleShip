package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/26/2019
 * BattleShipGame is used to hold the various things
 * that compose a battleship game.
 */

import battleship.models.interpreters.BattleShipBotAi;
import battleship.models.interpreters.BattleShipGameInterpreter;
import battleship.tools.EventBus;

public class BattleShipGame {

    // Player 1 must be LOCAL, variation of either Player or Bot.
    private BattleShipPlayer player1;
    // Player 2 must be AWAY, variation of either Player or Bot.
    private BattleShipPlayer player2;
    private int gameType = BattleShipGame.BVBGAME;
    // Throw events to this EventBus.
    public static EventBus eventBus = new EventBus();

    //Enumerators for Game Type.
    public static final int PVPGAME = 0;
    public static final int PVBGAME = 1;
    public static final int BVBGAME = 2;

    //Enumerators for Key Presses.
    public static final String UP = "W";
    public static final String LEFT = "A";
    public static final String  RIGHT = "D";
    public static final String DOWN = "S";

    public BattleShipGame () {
    }

    /** initializeGame used to create BattleShipGame
     *  involving player versus bot, or bot versus bot.
     */
    public void initializeGame () {
        switch (this.gameType) {
            case BattleShipGame.PVPGAME:
                this.player1 = new BattleShipPlayer(BattleShipPlayer.HUMAN, BattleShipPlayer.LOCAL);
                break;
            case BattleShipGame.PVBGAME:
                this.player1 = new BattleShipPlayer(BattleShipPlayer.HUMAN, BattleShipPlayer.LOCAL);
                this.player2 = new BattleShipPlayer(BattleShipPlayer.BOT, BattleShipPlayer.AWAY, BattleShipBotAi.EASY);
                break;
            case BattleShipGame.BVBGAME:
                this.player1 = new BattleShipPlayer(BattleShipPlayer.BOT, BattleShipPlayer.LOCAL, BattleShipBotAi.EASY);
                this.player2 = new BattleShipPlayer(BattleShipPlayer.BOT, BattleShipPlayer.AWAY, BattleShipBotAi.EASY);
                break;
        }
        BattleShipGame.getEventBus().addListener(new BattleShipGameInterpreter(this));
    }

//*****************     GETTERS     *******************

    public BattleShipPlayer getPlayer1 () {
        return this.player1;
    }

    public BattleShipPlayer getPlayer2 () {
        return this.player2;
    }

    public int getGameType() {
        return this.gameType;
    }

    public static EventBus getEventBus () {
        return eventBus;
    }

//*****************     SETTERS     *******************

    public void setGameType(int _gameType) {
        this.gameType = _gameType;
    }

}