package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/28/2019
 * BattleShipBoard will contain various methods
 * and attributes that generically define a Battle Ship Board.
 */

public class BattleShipBoard {

    public static final int BOARDSIZE = 10;

    public BattleShipBoard (BattleShipPlayer _player) {
    }

    public static Coordinate generateRandomCoordinate() {
        int randomRow = (int)(Math.random() * BattleShipBoard.BOARDSIZE);
        int randomColumn = (int)(Math.random() * BattleShipBoard.BOARDSIZE);
        return new Coordinate(randomRow, randomColumn);
    }

}