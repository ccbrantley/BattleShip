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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class FXMLPlayController implements Initializable {

    public FXMLPlayController () {
        this.playControllerLogic = new PlayControllerLogic(this);
    }

    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        this.populateGrid.setOnAction(a -> this.playControllerLogic.setCarrier());
    }

    PlayControllerLogic playControllerLogic;
    @FXML
    private GridPane playerLogic;
    @FXML
    private GridPane playerShip;
    @FXML
    private GridPane menu;
    @FXML
    private Button populateGrid;

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
}

