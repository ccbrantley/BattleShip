package battleship.controllers;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/12/2019
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
public class FXMLPlayController implements Initializable {

    public FXMLPlayController () {
        this.playControllerLogic = new PlayControllerLogic(this);
    }

    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        this.playerShip.add(this.allCarrierH, 0, 0, 5, 1);
        this.playerShip.add(this.allBattleshipH, 5,5,4,1);
        ArrayList<GridPane> allShips = new ArrayList();
        allShips.add(allCarrierH);
        allShips.add(allCarrierV);
        allShips.add(allBattleshipH);
        allShips.add(allBattleshipV);
        this.playControllerLogic.initializeController(allShips);
    }

    PlayControllerLogic playControllerLogic;
    @FXML
    private GridPane playerLogic;
    @FXML
    private GridPane playerShip;
    @FXML
    private GridPane menu;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane allCarrierH;
    @FXML
    private GridPane allCarrierV;
    @FXML
    private GridPane allBattleshipH;
    @FXML
    private GridPane allBattleshipV;

    @FXML
    public void returnMainMenu(ActionEvent _event) throws IOException {
        this.playControllerLogic.returnMainMenu(_event);
    }

//*****************     SETTERS     *******************

    @FXML
    private void setButtonState(ActionEvent _event) {
        this.playControllerLogic.setButtonState(_event);
    }

//*****************     GETTERS     *******************

    public PlayControllerLogic getLogic(){
        return this.playControllerLogic;
    }

    public GridPane getPlayerLogicPane(){
        return this.playerLogic;
    }

    public GridPane getPlayerShipPane(){
        return this.playerShip;
    }

    public GridPane getMenuPane(){
        return this.menu;
    }

    public GridPane getAllCarrierH(){
        return this.allCarrierH;
    }

    public GridPane getAllCarrierV(){
        return this.allCarrierV;
    }

    public GridPane getAllBattleshipH() {
        return this.allBattleshipH;
    }

    public GridPane getAllBattleshipV() {
        return this.allBattleshipV;
    }

}

