package battleship.models.interpreters;

/*@Author Area51BlockParty
* Jacob Schumacher, Christopher Brantley
* Last updated 11/28/19
* This class is protocol for battle ship AI.
*/

import battleship.models.BattleShipBoard;
import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.models.Coordinate;
import battleship.tools.Listener;
import battleship.tools.events.FireAwayEvent;
import battleship.tools.events.GameUpdateUserMessage;

public class BattleShipBotAi implements Listener {

    private int difficulty = BattleShipBotAi.NULL;
    private BattleShipPlayer player;
    Coordinate[][] shotsFired;
    Coordinate lastHit;
    int xPos;
    int yPos;
    int opponent;
    Coordinate currentTarget;
    GameUpdateUserMessage messageEvent;

    //Enumeration -> Bot difficulty.
    public static final int NULL = -1;
    public static final int EASY = 0;
    public static final int NORMAL = 1;
    public static final int HARD = 2;

    public BattleShipBotAi (BattleShipPlayer _AI, int _difficulty) {
        this.player = _AI;
        this.difficulty = _difficulty;
        this.shotsFired = new Coordinate[10][10];
        if(_AI.getPlayerTeam() == BattleShipPlayer.AWAY){
            this.opponent = BattleShipPlayer.LOCAL;
        }
        else{
            this.opponent = BattleShipPlayer.AWAY;
        }
    }

    private void takeShot () {
        Coordinate randCoordinate = BattleShipBoard.generateRandomCoordinate();
        int xPos = randCoordinate.getRow();
        int yPos = randCoordinate.getColumn();
            if(this.shotsFired[xPos][yPos] == null){
                this.currentTarget = randCoordinate;
                this.player.setCurrentTarget(randCoordinate);
                BattleShipGame.getEventBus().throwEvent(new FireAwayEvent(this.opponent));
                this.shotsFired[xPos][yPos] = randCoordinate;
            }
            else{
                takeShot();
            }
    }

    @Override
    public void catchEvent(Object _event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}