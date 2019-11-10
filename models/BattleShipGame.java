package battleship.models;

import battleship.tools.EventBus;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 */

public class BattleShipGame {
    public BattleShipGame(int _type){
        switch (_type) {
            case BattleShipGame.PVPGAME:
                this.player1 = new BattleShipPlayer(BattleShipPlayer.HUMAN);
                //this.player2 = new BattleShipPlayer(BattleShipPlayer.HUMAN);
                break;
            case BattleShipGame.PVBGAME:
                this.player1 = new BattleShipPlayer(BattleShipPlayer.HUMAN);
                //this.player2 = new BattleShipPlayer(BattleShipPlayer.BOT);
                break;
            case BattleShipGame.BVBGAME:
                this.player1 = new BattleShipPlayer(BattleShipPlayer.BOT);
                //this.player2 = new BattleShipPlayer(BattleShipPlayer.BOT);
                break;
            default:
                break;
        }
    }
    // if player 2 then must be bot
    private BattleShipPlayer player1;
    //private BattleShipPlayer player2;
    public static EventBus eventBus = new EventBus();
    public static final int PVPGAME = 0;
    public static final int PVBGAME = 1;
    public static final int BVBGAME = 2;

//*****************     GETTERS     *******************

    public BattleShipPlayer getPlayer1() {
        return player1;
    }

   /* public BattleShipPlayer getPlayer2() {
        return player2;
    }*/

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

    /*public void setPlayer2(BattleShipPlayer player2) {
        this.player2 = player2;
    }*/

}
