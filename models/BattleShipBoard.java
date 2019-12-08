package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 12/03/2019
 * BattleShipBoard will contain various methods
 * and attributes that generically define a Battle Ship Board.
 */

public class BattleShipBoard {

    public static final int BOARDSIZE = 10;

    public BattleShipBoard () {
    }

    // Generates a random coordinate within the boundaries of the board.
    public static Coordinate generateRandomCoordinate() {
        int randomRow = (int)(Math.random() * BattleShipBoard.BOARDSIZE);
        int randomColumn = (int)(Math.random() * BattleShipBoard.BOARDSIZE);
        return new Coordinate(randomRow, randomColumn);
    }

    /**Takes a value and checks if it is within the range of the board.
     * @param _index
     * @return Boolean value of whether the value lies within the board size.
     */
    public static Boolean boardBoundaryCheck (int _index) {
        return !(_index < 0 | _index > BattleShipBoard.BOARDSIZE-1 );
    }

    public static Boolean boardBoundaryCheck (Coordinate _coordinate) {
        int row = _coordinate.getRow();
        if (!BattleShipBoard.boardBoundaryCheck(row)) {
            return false;
        }
        int column = _coordinate.getColumn();
        if (!BattleShipBoard.boardBoundaryCheck(column)) {
            return false;
        }
        return true;
    }

}