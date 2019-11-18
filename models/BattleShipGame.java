package battleship.models;

import battleship.tools.BattleShipGameInterpreter;
import battleship.tools.EventBus;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 * BattleShipGame is used to reference the generic things
 * that compose a battleship game.
 */

public class BattleShipGame {

    /**
     * BattleShipGame constructor used to create BattleShipGame
     * involving player versus player, player versus bot, or bot versus bot.
     * @param _type: Enumerators PVPGAME, PVBGAME, or BVBGAME.
     */
    public BattleShipGame(int _type){
        switch (_type) {
            case BattleShipGame.PVPGAME:
                this.player1 = new BattleShipPlayer(BattleShipPlayer.HUMAN, BattleShipPlayer.LOCAL);
                break;
            case BattleShipGame.PVBGAME:
                this.player1 = new BattleShipPlayer(BattleShipPlayer.HUMAN, BattleShipPlayer.LOCAL);
                this.player2 = new BattleShipPlayer(BattleShipPlayer.BOT, BattleShipPlayer.AWAY);
                break;
            case BattleShipGame.BVBGAME:
                this.player1 = new BattleShipPlayer(BattleShipPlayer.BOT, BattleShipPlayer.LOCAL);
                this.player2 = new BattleShipPlayer(BattleShipPlayer.BOT, BattleShipPlayer.AWAY);
                break;
        }
        this.player1.getBattleShipFleet().throwAllPositionUpdateEvents();
        BattleShipGame.getEventBus().addListener(new BattleShipGameInterpreter(this));
    }

    // Player 1 must be OPPONENT, variation of either Player or Bot.
    private BattleShipPlayer player1;
    // Player 2 must be OPPONENT, variation of either Player or Bot.
    private BattleShipPlayer player2;
    // Throw events to this EventBus.
    public static EventBus eventBus = new EventBus();
    //Enumerators for Game Type.
    public static final int PVPGAME = 0;
    public static final int PVBGAME = 1;
    public static final int BVBGAME = 2;

//*****************     GETTERS     *******************

    public BattleShipPlayer getPlayer1() {
        return player1;
    }

    public BattleShipPlayer getPlayer2() {
        return player2;
    }

    public static EventBus getEventBus() {
        return eventBus;
    }

    public static int getPVPGAME() {
        return PVPGAME;
    }

    public static int getPVBGAME() {
        return PVBGAME;
    }

    public static int getBVBGAME() {
        return BVBGAME;
    }

//*****************     SETTERS     *******************

    public void setPlayer1(BattleShipPlayer player1) {
        this.player1 = player1;
    }

    public void setPlayer2(BattleShipPlayer player2) {
        this.player2 = player2;
    }
}
