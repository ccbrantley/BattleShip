package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 */

import battleship.tools.events.*;
import java.util.ArrayList;

public class BattleShipShip {
    public BattleShipShip(int _shipType, int _orientation) {
        this.shipType = _shipType;
        this.shipOrientation = _orientation;
        this.shipId = this.convertShipTypeToId(_shipType);
        this.shipLength = this.determineShipLength(_shipType);
        for(int x = 0; x < this.shipLength; x++) {
            shipPieces.add(new BattleShipShipPiece((x+1), _orientation, this.shipId));
        }
    }
    private int shipType;
    private int shipOrientation;
    private int shipLength;
    private String shipId;
    private ArrayList<BattleShipShipPiece> shipPieces = new ArrayList();
    //Enumerations
    //Ship Type
    private static final int ERROR = -1;
    public static final int CARRIER = 0;
    public static final int BATTLESHIP = 1;
    public static final int CRUISER = 2;
    public static final int SUBMARINE = 3;
    public static final int DESTROYER = 4;
    //Orientation
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    //Movement Direction
    public static final int RANDOM = -1;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

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

    private String convertShipTypeToId (int _type) {
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

    private int normalizeRange (int _index, int _totalRange) {
        if (!(this.gridBoundaryCheck(_index+_totalRange-1))) {
            _index = (BattleShipBoard.BOARDSIZE-1) - (_totalRange-1);
        }
        if (!(this.gridBoundaryCheck(_index))) {
            _index =  0;
        }
        return _index;
    }

    public Boolean gridBoundaryCheck (int _index) {
        return !(_index < 0 | _index > BattleShipBoard.BOARDSIZE-1 );
    }

    public ArrayList<Coordinate> generatePossibleCoordinates(int _row, int _column) {
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

    // This method moves a ship to a position or coordinate, does not work for
    // incremental values or advance by. A ships leftmost/topmost piece is placed
    // at the row and column followed by the rest of the ship.
    public boolean moveShip(int _row, int _column ) {
        if(_row == BattleShipShip.RANDOM &&(_row == _column)) {
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
        ArrayList<UpdateSectorEvent> clearSectorEvents = new ArrayList();
        ArrayList<UpdateSectorEvent> setSectorEvents = new ArrayList();
        for(BattleShipShipPiece piece : this.shipPieces) {
            clearSectorEvents.add(new UpdateSectorEvent(piece.getRowIndex(), piece.getColumnIndex(), 0, "grid"));
            piece.setIndexes((row + rowCounter), (column + columnCounter));
            int shipRotation = (this.getShipOrientation() == BattleShipShip.HORIZONTAL) ? 0 : 90;
            setSectorEvents.add(new UpdateSectorEvent(piece.getRowIndex(), piece.getColumnIndex(), shipRotation, (piece.getId() + String.valueOf(piece.getPiece()))));
            if (this.shipOrientation == BattleShipShip.HORIZONTAL) {
                columnCounter++;
            }
            else {
                rowCounter++;
            }
        }
        clearSectorEvents.forEach(sector -> {
            BattleShipGame.getEventBus().throwEvent(sector);
        });
        setSectorEvents.forEach(sector -> {
            BattleShipGame.getEventBus().throwEvent(sector);
        });
        return true;
    }

    // Procedure used to move a ship by a set amount of spaces
    public void moveShipIncrementally (int _rowInc, int _columnInc) {
        BattleShipShipPiece firstPiece = this.shipPieces.get(0);
        this.moveShip(_rowInc + firstPiece.getRowIndex(),  _columnInc + firstPiece.getColumnIndex());
    }

    public void rotateShip() {
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

    public ArrayList<Coordinate> getBattleShipCoordinates() {
        ArrayList<Coordinate> coordinates = new ArrayList();
        this.shipPieces.forEach(piece -> {
            coordinates.add(piece.getCoordinate());
        });
        return coordinates;
    }

    //This operates off an arraylist of predictive coordinates
    private boolean isUniquePosition(ArrayList<Coordinate> _coordinate) {
        int coordinateRow;
        int coordinateColumn;
        int pieceRow;
        int pieceColumn;
        for(BattleShipShip ship : BattleShipFleet.getFleetOfShips()) {
            if(ship == this) {continue;}
            for(BattleShipShipPiece piece : ship.getShipPieces()) {
                pieceRow = piece.getRowIndex();
                pieceColumn = piece.getColumnIndex();
                for(Coordinate coordinate : _coordinate) {
                    coordinateRow = coordinate.getRow();
                    coordinateColumn = coordinate.getColumn();
                    if(pieceRow == coordinateRow) {
                        if(pieceColumn == coordinateColumn){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    // Generates Random and Unique Coordinates
    public Coordinate generateRandomUniqueCoordinate() {
        int randomRow = (int)(Math.random() * 10);
        int randomColumn = (int)(Math.random() * 10);
        while (!(this.isUniquePosition(this.generatePossibleCoordinates(randomRow, randomColumn)))) {
            randomRow = (int)(Math.random() * 10);
            randomColumn = (int)(Math.random() * 10);
        }
        return new Coordinate(randomRow, randomColumn);
    }

    public static int generateRandomOrientation() {
        return (int)(Math.random() * 2);
    }

//*****************     GETTERS     *******************

    public int getShipType() {
        return shipType;
    }

    public int getShipOrientation() {
        return shipOrientation;
    }

    public int getShipLength() {
        return shipLength;
    }

    public String getShipId() {
        return shipId;
    }

    public ArrayList<BattleShipShipPiece> getShipPieces() {
        return shipPieces;
    }

    public static int getERROR() {
        return ERROR;
    }

    public static int getCARRIER() {
        return CARRIER;
    }

    public static int getBATTLESHIP() {
        return BATTLESHIP;
    }

    public static int getCRUISER() {
        return CRUISER;
    }

    public static int getSUBMARINE() {
        return SUBMARINE;
    }

    public static int getDESTROYER() {
        return DESTROYER;
    }

    public static int getHORIZONTAL() {
        return HORIZONTAL;
    }

    public static int getVERTICAL() {
        return VERTICAL;
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
