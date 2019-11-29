package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/27/2019
 * BattleShipShip serves to define a ship object. A ship object will contain
 * attributes such as the type, id, orientation, length, and the ship pieces.
 * Procedures required to move ship are located here.
 */

import battleship.tools.events.*;
import java.util.ArrayList;

public class BattleShipShip {

    private int shipType;
    private int shipOrientation;
    private int shipLength;
    private String shipId;
    private ArrayList<BattleShipShipPiece> shipPieces = new ArrayList();
    private ArrayList<BattleShipShip> battleShipFleet;

    //Enumerators -> Ship Type.
    private static final int ERROR = -1;
    public static final int CARRIER = 0;
    public static final int BATTLESHIP = 1;
    public static final int CRUISER = 2;
    public static final int SUBMARINE = 3;
    public static final int DESTROYER = 4;
    //Enumerators -> Orientation.
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    //Enumerators -> Movement Direction.
    public static final int RANDOM = -1;

    public BattleShipShip (int _shipType, int _orientation, ArrayList<BattleShipShip> _battleShipFleet) {
        this.shipType = _shipType;
        this.shipOrientation = _orientation;
        this.battleShipFleet = _battleShipFleet;
        this.shipId = BattleShipShip.convertShipTypeToId(_shipType);
        this.shipLength = this.determineShipLength(_shipType);
        for(int x = 0; x < this.shipLength; x++) {
            shipPieces.add(new BattleShipShipPiece((x+1), _orientation, this.shipId));
        }
    }

    /**Determine ship's enumerated value from its Id.
     * @param _id
     * @return ship's enumerated value.
     */
    public static int convertShipIdToType (String _id) {
        switch (_id) {
            case "carrier":
                return BattleShipShip.CARRIER;
            case "battleship":
                return BattleShipShip.BATTLESHIP;
            case "cruiser":
                return BattleShipShip.CRUISER;
            case "submarine":
                return BattleShipShip.SUBMARINE;
            case "destroyer":
                return BattleShipShip.DESTROYER;
            default:
                return -1;
        }
    }

    /**Determines ship's string Id from its enumerated value.
     * @param _type
     * @return ship's string Id.
     */
    public static String convertShipTypeToId (int _type) {
        switch (_type) {
            case BattleShipShip.CARRIER:
                return "carrier";
            case BattleShipShip.BATTLESHIP:
                return "battleship";
            case BattleShipShip.CRUISER:
                return "cruiser";
            case BattleShipShip.SUBMARINE:
                return "submarine";
            case BattleShipShip.DESTROYER:
                return "destroyer";
            default:
                return "error";
        }
    }

    /**Takes a value and checks if it is within the range of the board.
     * @param _index
     * @return Boolean value of whether the value lies within the board size.
     */
    public Boolean gridBoundaryCheck (int _index) {
        return !(_index < 0 | _index > BattleShipBoard.BOARDSIZE-1 );
    }

    /**Takes a coordinate and returns a list of coordinates of where the ship
     * would occupy if moved to that coordinate.
     * @param _row
     * @param _column
     * @return An ArrayList of predictive coordinates based on starting sector.
     */
    public ArrayList<Coordinate> generatePossibleCoordinates (int _row, int _column) {
        int rowRange = (this.shipOrientation == BattleShipShip.HORIZONTAL) ? 1 : this.shipLength;
        int columnRange = (this.shipOrientation == BattleShipShip.HORIZONTAL) ? this.shipLength : 1;
        int row = this.normalizeRange(_row, rowRange);
        int column = this.normalizeRange(_column, columnRange);
        int rowCounter = 0;
        int columnCounter = 0;
        ArrayList<Coordinate> possibleMoves = new ArrayList();
        for(BattleShipShipPiece piece : this.shipPieces) {
            possibleMoves.add(new Coordinate((row + rowCounter),(column + columnCounter)));
            if (this.shipOrientation == BattleShipShip.HORIZONTAL) {
                columnCounter++;
            }
            else {
                rowCounter++;
            }
        }
        return possibleMoves;
    }

    /**Moves a ship to a row and column. A ships leftmost/topmost
     * piece is placed at the row and column and then succeeded
     * by the remaining pieces.
     * @param _row
     * @param _column
     * @return Boolean value of whether the ship has or has not been moved.
     */
    public boolean moveShip (int _row, int _column) {
        if(_row == BattleShipShip.RANDOM && (_row == _column)) {
            Coordinate randomCoordinate = this.generateRandomUniqueCoordinate();
            _row = randomCoordinate.getRow();
            _column = randomCoordinate.getColumn();
        }
        else {
                if(!(this.isUniquePosition(this.generatePossibleCoordinates(_row, _column)))){
                    return false;
                }
        }
        int rowRange = (this.shipOrientation == BattleShipShip.HORIZONTAL) ? 1 : this.shipLength;
        int columnRange = (this.shipOrientation == BattleShipShip.HORIZONTAL) ? this.shipLength : 1;
        int row = this.normalizeRange(_row, rowRange);
        int column = this.normalizeRange(_column, columnRange);
        int rowCounter = 0;
        int columnCounter = 0;
        for(BattleShipShipPiece piece : this.shipPieces) {
            piece.setIndexes((row + rowCounter), (column + columnCounter));
            int shipRotation = (this.getShipOrientation() == BattleShipShip.HORIZONTAL) ? 0 : 90;
            if (this.shipOrientation == BattleShipShip.HORIZONTAL) {
                columnCounter++;
            }
            else {
                rowCounter++;
            }
        }
        return true;
    }

    // Moves ship by its current position and some increment.
    public void moveShipIncrementally (int _rowInc, int _columnInc) {
        BattleShipShipPiece firstPiece = this.shipPieces.get(0);
        this.moveShip(_rowInc + firstPiece.getRowIndex(),  _columnInc + firstPiece.getColumnIndex());
    }

    // Rotates the ship by setting orientation and moving the ship.
    public void rotateShip () {
        int oldRotation = this.shipOrientation;
        int newRotation = (this.shipOrientation == BattleShipShip.HORIZONTAL) ? BattleShipShip.VERTICAL : BattleShipShip.HORIZONTAL;
        this.shipOrientation = newRotation;
        int averageDistance = this.getShipLength()/2;
        boolean ableToMove = false;
        if(this.shipOrientation == BattleShipShip.VERTICAL) {
            ableToMove = this.moveShip(this.shipPieces.get(0).getRowIndex() - averageDistance, this.shipPieces.get(0).getColumnIndex() + averageDistance);
        } else {
            ableToMove = this.moveShip(this.shipPieces.get(this.shipPieces.size()-1).getRowIndex() - averageDistance, this.shipPieces.get(this.shipPieces.size()-1).getColumnIndex() - averageDistance);
        }
        if(!ableToMove) {
            this.shipOrientation = oldRotation;
        }
    }

    /** @return All Coordinates of a BattleShipShip
     */
    public ArrayList<Coordinate> getBattleShipCoordinates () {
        ArrayList<Coordinate> coordinates = new ArrayList();
        this.shipPieces.forEach(piece -> {
            coordinates.add(piece.getCoordinate());
        });
        return coordinates;
    }

    /** @return Generates Random and Unique Coordinates.
     */
    public Coordinate generateRandomUniqueCoordinate () {
        int randomRow = (int)(Math.random() * 10);
        int randomColumn = (int)(Math.random() * 10);
        while (!(this.isUniquePosition(this.generatePossibleCoordinates(randomRow, randomColumn)))) {
            randomRow = (int)(Math.random() * 10);
            randomColumn = (int)(Math.random() * 10);
        }
        return new Coordinate(randomRow, randomColumn);
    }

    /** @return Generates random orientation.
     */
    public static int generateRandomOrientation () {
        return (int)(Math.random() * 2);
    }

   /** @return All sector update events for ship pieces.
     */
    public ArrayList<UpdateSectorEvent> getAllSectorUpdateEvents () {
        ArrayList<UpdateSectorEvent> events = new ArrayList();
        this.shipPieces.forEach(piece -> {
            events.add(new UpdateSectorEvent(piece.getRowIndex(), piece.getColumnIndex(), this.getShipRotation(), piece.getFullId()));
        });
        return events;
    }

    /** Determines whether an array list of coordinates is free of ships.
     * @param _coordinate
     * @return Whether or not an array list of coordinates is free of ships.
     */
    private boolean isUniquePosition (ArrayList<Coordinate> _coordinate) {
        int coordinateRow;
        int coordinateColumn;
        int pieceRow;
        int pieceColumn;
        for (BattleShipShip ship : this.battleShipFleet) {
            if(ship == this) {
                continue;
            }
            for (BattleShipShipPiece piece : ship.getShipPieces()) {
                pieceRow = piece.getRowIndex();
                pieceColumn = piece.getColumnIndex();
                for (Coordinate coordinate : _coordinate) {
                    coordinateRow = coordinate.getRow();
                    coordinateColumn = coordinate.getColumn();
                    if (pieceRow == coordinateRow) {
                        if (pieceColumn == coordinateColumn){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**Determines a ships length from its enumerated value.
     * @param _type
     * @return Ships length.
     */
    private int determineShipLength (int _type) {
        switch (_type) {
            case BattleShipShip.CARRIER:
                return 5;
            case BattleShipShip.BATTLESHIP:
                return 4;
            case BattleShipShip.CRUISER:
                return 3;
            case BattleShipShip.SUBMARINE:
                return 3;
            case BattleShipShip.DESTROYER:
                return 2;
            default:
                break;
        }
        return -1;
    }

    /**Sums two numbers and normalizes their value with respect to board size.
     * @param _index
     * @param _totalRange
     * @return A value within board boundary.
     */
    private int normalizeRange (int _index, int _totalRange) {
        int sum = _index + _totalRange - 1;
        if(!this.gridBoundaryCheck(_index)) {
            if (_index < 0) {
                return (BattleShipBoard.BOARDSIZE-1) - (_totalRange-1);
            }
            else {
                return 0;
            }
        }
        if (!this.gridBoundaryCheck(sum)) {
            if (sum < 0) {
                return (BattleShipBoard.BOARDSIZE-1) - (_totalRange-1);
            }
            else {
                return 0;
            }
        }
        /*
        if (!(this.gridBoundaryCheck(_index+_totalRange-1))) {
            _index = (BattleShipBoard.BOARDSIZE-1) - (_totalRange-1);
        }
        if (!(this.gridBoundaryCheck(_index))) {
            _index =  0;
        } */
        return _index;
    }

//*****************     GETTERS     *******************

    public int getShipType() {
        return this.shipType;
    }

    public int getShipRotation() {
        return (this.shipOrientation == BattleShipShip.HORIZONTAL) ? 0 : 90;
    }

    public int getShipOrientation() {
        return this.shipOrientation;
    }

    public int getShipLength() {
        return this.shipLength;
    }

    public String getShipId() {
        return this.shipId;
    }

    public ArrayList<BattleShipShipPiece> getShipPieces() {
        return this.shipPieces;
    }

//*****************     SETTERS     *******************

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public void setShipOrientation(int shipOrientation) {
        this.shipOrientation = shipOrientation;
    }

    public void setShipLength(int shipLength) {
        this.shipLength = shipLength;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public void setShipPieces(ArrayList<BattleShipShipPiece> shipPieces) {
        this.shipPieces = shipPieces;
    }

}