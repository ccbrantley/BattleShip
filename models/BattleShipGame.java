package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 12/03/2019
 * BattleShipGame is used to hold the various things
 * that compose a battleship game.
 */

import battleship.models.interpreters.BattleShipBotAi;
import battleship.models.interpreters.BattleShipGameInterpreter;
import battleship.tools.EventBus;
import battleship.weather.models.Weather;
import battleship.weather.util.Location;
import java.util.ArrayList;

public class BattleShipGame {

    //Enumerators for Game Type.
    public static final int PVBGAME = 1;
    public static final int BVBGAME = 2;

    //Enumerators for Key Presses.
    public static final String UP = "W";
    public static final String LEFT = "A";
    public static final String  RIGHT = "D";
    public static final String DOWN = "S";

    // Player 1 must be LOCAL, variation of either Player or Bot.
    private BattleShipPlayer player1;
    // Player 2 must be AWAY, variation of either Player or Bot.
    private BattleShipPlayer player2;
    private int gameType = BattleShipGame.BVBGAME;
    // Throw events to this EventBus.
    private static EventBus eventBus = new EventBus();

    // Weather API
    private static ArrayList<Weather> weathers = Weather.loadWeathersForAllLocations();
    // User Weather Location
    private static int weatherSelection = Location.FALKLANDS;

    public BattleShipGame () {
    }

    /** initializeGame used to create BattleShipGame
     *  involving player versus bot, or bot versus bot.
     */
    public void initializeGame () {
        switch (this.gameType) {
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

    public int getGameType () {
        return this.gameType;
    }

    public static EventBus getEventBus () {
        return eventBus;
    }

    public static Weather getWeatherSelection() {
        return BattleShipGame.weathers.get(BattleShipGame.weatherSelection);
    }

//*****************     SETTERS     *******************

    public void setGameType (int _gameType) {
        this.gameType = _gameType;
    }

}