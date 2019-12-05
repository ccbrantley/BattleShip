package battleship.models.interpreters;

/*@Author Area51BlockParty
* Jacob Schumacher
* Last updated 12/3/19
* This class is protocol for battle ship AI.
*/

import battleship.models.BattleShipBoard;
import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.models.Coordinate;
import battleship.tools.Listener;
import battleship.tools.events.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class BattleShipBotAi implements Listener {

    //Enumeration -> Bot difficulty.
    public static final int NULL = -1;
    public static final int EASY = 0;
    public static final int NORMAL = 1;
    public static final int HARD = 2;

    private int difficulty = BattleShipBotAi.NULL;
    private final BattleShipPlayer player;
    private ArrayList<Coordinate> possibleShots = new ArrayList();
    private ArrayList<Coordinate> predictedShots = new ArrayList();
    private ArrayList<Coordinate> alreadyShot = new ArrayList();
    private Coordinate lastShot;
    private final int opponent;
    private final int botTimer = (int)(Math.random() * 500 + 500);

    Timeline AiLoop = new Timeline(new KeyFrame(Duration.millis(botTimer), event -> {
        this.takeTurn();
    }));

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

    }

    @Override
    public void catchEvent (Object _event) {

        if (_event instanceof StartGameEvent) {
            this.AiLoop.setCycleCount(Timeline.INDEFINITE);
            this.AiLoop.play();
        }

        if (_event instanceof ShipHitEvent) {
            ShipHitEvent event = (ShipHitEvent)_event;
            if (this.player.getPlayerTeam() != event.getPlayer()) {
                int row = event.getRow();
                int column = event.getColumn();
                Coordinate curCoordinate;
                List<Integer> plusMinus = Arrays.asList(1, -1);
                for (int multiplicity : plusMinus) {
                    curCoordinate = new Coordinate(row + multiplicity * 1, column + 0);
                    if (BattleShipBoard.boardBoundaryCheck(curCoordinate)) {
                        if (!this.coordinateInList(this.alreadyShot, curCoordinate)) {
                            this.predictedShots.add(curCoordinate);
                        }
                    }
                    curCoordinate = new Coordinate(row + 0, column - multiplicity * 1);
                    if (BattleShipBoard.boardBoundaryCheck(curCoordinate)) {
                        if (!this.coordinateInList(this.alreadyShot, curCoordinate)) {
                            this.predictedShots.add(curCoordinate);
                        }
                    }
                }
            }
        }

    }

    private void startAi() {
        this.AiLoop.pause();
    }

    private void createPossibleShotArray () {
        for (int x = 0; x < BattleShipBoard.BOARDSIZE; ++x) {
            for (int y = 0; y < BattleShipBoard.BOARDSIZE; ++y) {
                this.possibleShots.add(new Coordinate(x, y));
            }
        }
    }

    private Coordinate getRandomPossibleShot (ArrayList<Coordinate> _shots) {
        int randIndex = (int)(Math.random() * (_shots.size() - 1));
        Coordinate randCoordinate = _shots.get(randIndex);
        _shots.remove(randIndex);
        return randCoordinate;
    }

    private boolean makePredictedShot () {
        if (!this.predictedShots.isEmpty()) {
            Coordinate predictedCoordinate = this.getRandomPossibleShot(this.predictedShots);
            this.takeShot(predictedCoordinate);
            this.synchShotArrays();
            return true;
        }
        else {
            return false;
        }
    }

    private void synchShotArrays () {
        this.possibleShots.forEach(possibleShot -> {
            this.predictedShots.forEach(predictShot -> {
                if (possibleShot == predictShot) {
                    this.possibleShots.remove(possibleShot);
                }
            });
        });
    }

    private boolean coordinateInList (ArrayList<Coordinate> _list, Coordinate _coordinate) {
        for (Coordinate curCoordinate : _list) {
            if (curCoordinate.equals(_coordinate)) {
                return true;
            }
        }
        return false;
    }

    private void takeRandomShot () {
        if(!this.possibleShots.isEmpty()) {
        Coordinate randCoordinate = this.getRandomPossibleShot(this.possibleShots);
        this.takeShot(randCoordinate);
        }
    }

    private void takeShot (Coordinate _coordinate) {
        this.lastShot = _coordinate;
        this.player.setCurrentTarget(_coordinate);
        BattleShipGame.getEventBus().throwEvent(new FireAwayEvent(this.opponent));
        this.alreadyShot.add(_coordinate);
    }

    private void takeTurn () {
        if (this.player.isTurn()) {
            if(!this.makePredictedShot()) {
                this.takeRandomShot();
            }
        }
    }

}