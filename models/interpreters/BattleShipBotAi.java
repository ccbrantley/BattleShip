package battleship.models.interpreters;

/*@Author Area51BlockParty
* Jacob Schumacher, Christopher Brantley
* Last updated 11/28/19
* This class is protocol for battle ship AI.
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

    //Enumeration -> Bot difficulty.
    public static final int NULL = -1;
    public static final int EASY = 0;
    public static final int NORMAL = 1;
    public static final int HARD = 2;

    public BattleShipBotAi (BattleShipPlayer _AI, int _difficulty) {
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
<<<<<<< Updated upstream

    public void takeShot () {
        xPos = (int)Math.random()*10+1;
        yPos = (int)Math.random()*10+1;
            if(this.shotsFired[xPos][yPos] == null){
                currentTarget = new Coordinate(xPos,yPos);
                this.player.setCurrentTarget(currentTarget);
                BattleShipGame.getEventBus().throwEvent(new FireAwayEvent(opponent));
                this.shotsFired[xPos][yPos] = new Coordinate(xPos, yPos);
            }
            else{
                takeShot();
            }
=======
    /*
    * @param _event: An event, either StartGameEvent, or ShipHitEvent
    * Catch event listens for an event, it it's a startGameEvent, it will begin
    * the AI cycle.
    * If it's a ShipHitEvent, it checks if an enemy ship just got hit and 
    * saves the nearby coordinates.
    */
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
    /*
    * This method begins the Ai loop so it will repeat these actions.
    */
    private void startAi() {
        this.AiLoop.pause();
    }
    /*
    * createPossibleShotArray populates the possible shots arrayList with all
    * possible coorinates.
    */
    private void createPossibleShotArray () {
        for (int x = 0; x < BattleShipBoard.BOARDSIZE; ++x) {
            for (int y = 0; y < BattleShipBoard.BOARDSIZE; ++y) {
                this.possibleShots.add(new Coordinate(x, y));
            }
        }
    }
    /*
    * @param _shots: an arraylist
    * getRandomPossibleShot randomly returns a coordinate from the arrayList 
    * passed in.
    */
    private Coordinate getRandomPossibleShot (ArrayList<Coordinate> _shots) {
        int randIndex = (int)(Math.random() * (_shots.size() - 1));
        Coordinate randCoordinate = _shots.get(randIndex);
        _shots.remove(randIndex);
        return randCoordinate;
    }
    
    /*
    * makePredictedShot will check to see if there are any coordinates left in
    * the predictedShots arrayList. If there is it will randomly fire at one of 
    * them. Otherwise it will return false to indicate an empty arrayList.
    */
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
    /*
    * synchShotArrays will make sure that there is no overlap between
    * predictedShots and PossibleShots.
    */
    private void synchShotArrays () {
        this.possibleShots.forEach(possibleShot -> {
            this.predictedShots.forEach(predictShot -> {
                if (possibleShot == predictShot) {
                    this.possibleShots.remove(possibleShot);
                }
            });
        });
    }
    /*
    * @param _list: The arraylist we're searchign through
    * @param _coordinate: The coordinate we're looking for.
    * coordinateInList checks to see if a given coordinate is in an arraylist 
    * of coordinates.
    */
    private boolean coordinateInList (ArrayList<Coordinate> _list, Coordinate _coordinate) {
        for (Coordinate curCoordinate : _list) {
            if (curCoordinate.equals(_coordinate)) {
                return true;
            }
        }
        return false;
    }
    
    /*
    * takeRandomShot will take a random shot from the possibleShots arrayList
    * so long as possible shots is not empty
    */
    private void takeRandomShot () {
        if(!this.possibleShots.isEmpty()) {
        Coordinate randCoordinate = this.getRandomPossibleShot(this.possibleShots);
        this.takeShot(randCoordinate);
        }
    }
    
    /*
    * @param _coordinate: the coordinate to be fired at
    * takeShot will fire at the enemy's board at the selected coordinate
    */
    private void takeShot (Coordinate _coordinate) {
        this.lastShot = _coordinate;
        this.player.setCurrentTarget(_coordinate);
        BattleShipGame.getEventBus().throwEvent(new FireAwayEvent(this.opponent));
        this.alreadyShot.add(_coordinate);
    }
    
    /*
    * takeTurn will see if it is the Ai's turn, if it is, it will take a shot.
    */
    private void takeTurn () {
        if (this.player.isTurn()) {
            if(!this.makePredictedShot()) {
                this.takeRandomShot();
            }
        }
>>>>>>> Stashed changes
    }

}
