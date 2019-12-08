package battleship.models.testclasses;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 12/08/2019
 * BattleShipBoard will contain various methods
 * and attributes that generically define a Battle Ship Board.
 */

import battleship.models.BattleShipBoard;
import battleship.models.Coordinate;

public class BattleShipBoardTestClass {
    public static void main(String[] args) {
        System.out.println("Range of enumerator correct? : " + BattleShipBoardTestClass.testEnumerator(100));
        System.out.println("Boundary Check ? : " + BattleShipBoardTestClass.testBoardBoundaryCheck(100));
        System.out.println("Random Coordinate Check ? : " + BattleShipBoardTestClass.testGenerateRandomCoordinate(100));
        System.out.println("Coordinate Boundary check ? : " + BattleShipBoardTestClass.testCoordinateBoundarycheck(100));
    }

    /* Enumerator will be used for generating a range of numbers
     * that is equal to it's length. We will test it's range here.
     * We expect our range to equal to the BOARDSIZE.
     */
    public static boolean testEnumerator (int _iterations) {
        int upperRange = BattleShipBoard.BOARDSIZE;
        int randomNumber = (int)(Math.random() * upperRange);
        int min = randomNumber;
        int max = randomNumber;
        int counter = 0;
        for (int x = 0; x < _iterations; x++) {
            if (randomNumber < min)  {
                min = randomNumber;
            }
            if (randomNumber > max) {
                max = randomNumber;
            }
            randomNumber = (int)(Math.random() * upperRange);
        }
        while (min <= max) {
            ++counter;
            ++min;
        }
        return BattleShipBoard.BOARDSIZE == counter;
    }

    /* Our board boundary check should return true for any value generated
     * from 0 to the enumerated BOARDSIZE.
     */
    public static boolean testBoardBoundaryCheck (int _iterations) {
        if (_iterations < 0) {
            return true;
        }
        else {
            int upperRange = BattleShipBoard.BOARDSIZE;
            int randomNumber = (int)(Math.random() * upperRange);
            return  BattleShipBoard.boardBoundaryCheck(randomNumber) &&
                    BattleShipBoardTestClass.testBoardBoundaryCheck(--_iterations);
        }
    }

    /* Generated coordinate should have both row and column within the
     * boundaries of the board.
     */
    public static boolean testGenerateRandomCoordinate (int _iterations) {
        if (_iterations < 0) {
            return true;
        }
        else {
            Coordinate randomCoordinate = BattleShipBoard.generateRandomCoordinate();
            int rowCoordiante = randomCoordinate.getRow();
            int columnCoordinate = randomCoordinate.getColumn();
            return BattleShipBoard.boardBoundaryCheck(rowCoordiante) &&
                    BattleShipBoard.boardBoundaryCheck(columnCoordinate) &&
                    BattleShipBoardTestClass.testGenerateRandomCoordinate(--_iterations);
        }
    }

    /* Makes sure that the coordinate Boundary check is returning correctly.
     * This test utilizes the generate random coordinate test as well.
     * We must have a working boundary check and a random coordinate generator
     * which are tested by the testGenerateRandomCoordinate.
     * This returns the truth value of the three conjuncted together.
     */
    public static boolean testCoordinateBoundarycheck (int _iterations) {
        if (_iterations < 0) {
            return true;
        }
        else {
            return BattleShipBoardTestClass.testGenerateRandomCoordinate(_iterations) &&
                    BattleShipBoard.boardBoundaryCheck(BattleShipBoard.generateRandomCoordinate()) &&
                    BattleShipBoardTestClass.testCoordinateBoundarycheck(--_iterations);
        }
    }
}