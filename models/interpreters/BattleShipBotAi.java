package battleship.models.interpreters;

/*@Author Area51BlockParty
* Jacob Schumacher
* Last updated 11/28/19
* This class is protocol for battle ship AI.
*/

import battleship.models.BattleShipBoard;
import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.models.Coordinate;
import battleship.tools.Listener;
import battleship.tools.events.FireAwayEvent;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class BattleShipBotAi implements Listener {

    private int difficulty = BattleShipBotAi.NULL;
    private BattleShipPlayer player;
    ArrayList<Coordinate> possibleShots = new ArrayList();
    int opponent;
    /*
    Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds((int)(Math.random() * 5) + 5), event -> {
        if (this.player.isTurn() && !this.possibleShots.isEmpty()) {
            this.takeShot();
        }
    }));
    */
    Timeline aMilliWonder = new Timeline(new KeyFrame(Duration.millis(1), event -> {
        if (this.player.isTurn() && !this.possibleShots.isEmpty()) {
            this.takeShot();
        }
    }));



    //Enumeration -> Bot difficulty.
    public static final int NULL = -1;
    public static final int EASY = 0;
    public static final int NORMAL = 1;
    public static final int HARD = 2;

    public BattleShipBotAi (BattleShipPlayer _AI, int _difficulty) {
        this.player = _AI;
        this.difficulty = _difficulty;
        if(this.player.getPlayerTeam() == BattleShipPlayer.AWAY){
            this.opponent = BattleShipPlayer.LOCAL;
        }
        else{
            this.opponent = BattleShipPlayer.AWAY;
        }
        this.createPossibleShotArray();
        this.aMilliWonder.setCycleCount(Timeline.INDEFINITE);
        this.aMilliWonder.play();

    }

    @Override
    public void catchEvent (Object _event) {
    }

    private void createPossibleShotArray () {
        for (int x = 0; x < BattleShipBoard.BOARDSIZE; ++x) {
            for (int y = 0; y < BattleShipBoard.BOARDSIZE; ++y) {
                this.possibleShots.add(new Coordinate(x, y));
            }
        }
    }

    private Coordinate getRandomPossibleShot () {
        int randIndex = (int)(Math.random() * (this.possibleShots.size() - 1));
        Coordinate randCoordinate = this.possibleShots.get(randIndex);
        this.possibleShots.remove(randIndex);
        return randCoordinate;
    }

    private void takeShot () {
        Coordinate randCoordinate = this.getRandomPossibleShot();
        int xPos = randCoordinate.getRow();
        int yPos = randCoordinate.getColumn();
        this.player.setCurrentTarget(randCoordinate);
        BattleShipGame.getEventBus().throwEvent(new FireAwayEvent(this.opponent));
    }

}