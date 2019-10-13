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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class FXMLPlayController implements Initializable {

    public FXMLPlayController () {
        this.playControllerLogic = new PlayControllerLogic(this);
    }

    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        this.playerShip.add(this.allCarrierH, 0, 0, 5, 1);
        this.allCarrierH.setOnScroll(FXMLPlayController.this.playControllerLogic::rotateGridPaneEvent);
        this.allCarrierH.setOnKeyPressed(FXMLPlayController.this.playControllerLogic::moveChildOfGridEvent);
        this.allCarrierV.setOnScroll(FXMLPlayController.this.playControllerLogic::rotateGridPaneEvent);
        this.allCarrierV.setOnKeyPressed(FXMLPlayController.this.playControllerLogic::moveChildOfGridEvent);
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
    private Button carrier;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane addShips;
    @FXML
    private GridPane allCarrierH;
    @FXML
    private GridPane allCarrierV;

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
    public GridPane getCarrierH(){
        return this.allCarrierH;
    }
    public GridPane getCarrierV(){
        return this.allCarrierV;
    }
}

