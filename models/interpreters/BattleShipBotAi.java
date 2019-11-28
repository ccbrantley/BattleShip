package battleship.models.interpreters;
/*@Author Area51BlockParty
* Jacob Schumacher, Christopher Brantley
* Last updated 11/25/19
* This class is 
*/
import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.models.BattleShipShip;
import battleship.models.Coordinate;
import battleship.tools.events.FireAwayEvent;
import battleship.tools.events.GameUpdateUserMessage;
import java.util.ArrayList;

public class BattleShipBotAi {

    private int difficulty = BattleShipBotAi.NULL;
    private BattleShipPlayer player;
    Coordinate[][] shotsFired;
    Coordinate lastHit;
    int xPos;
    int yPos;
    int opponent;
    Coordinate currentTarget;
    GameUpdateUserMessage messageEvent;

    //Enumeration
    public static final int NULL = -1;
    public static final int EASY = 0;
    public static final int NORMAL = 1;
    public static final int HARD = 2;

    public BattleShipBotAi(BattleShipPlayer _AI,int _difficulty) {
        this.player = _AI;
        this.difficulty = _difficulty;
        Coordinate[][] shotsFired = new Coordinate[10][10];
        if(_AI.getPlayerTeam() == BattleShipPlayer.AWAY){
            opponent = BattleShipPlayer.LOCAL;
        }
        else{
            opponent = BattleShipPlayer.AWAY;
        }
    }

    public void takeShot(){
        xPos = (int)Math.random()*10+1;
        yPos = (int)Math.random()*10+1;
            if(shotsFired[xPos][yPos] == null){
                currentTarget = new Coordinate(xPos,yPos);
                this.player.setCurrentTarget(currentTarget);
                BattleShipGame.getEventBus().throwEvent(new FireAwayEvent(opponent));
                shotsFired[xPos][yPos] = new Coordinate(xPos, yPos);
            }
            else{
                takeShot();
            }
    }
    
    
}

