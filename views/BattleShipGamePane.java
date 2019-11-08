package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/19/2019
 */

import battleship.controllers.PlayControllerLogic;
import battleship.models.ResourceGetter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class BattleShipGamePane {
    public BattleShipGamePane(PlayControllerLogic playLogic, double _screenWidth, double _screenHeight, HashMap _battleShips, GridPane _playerShipPane) throws IOException {
        this.playLogic = playLogic;
        this.battleShips = _battleShips;
        this.screenWidth = _screenWidth;
        this.screenHeight = _screenHeight;
        this.usedDimension = ((_screenWidth < _screenHeight) ? _screenWidth : _screenHeight);
        this.gridScale = ((this.usedDimension)*.95)/10;
        this.playerShipPane = _playerShipPane;
        this.initializeGUI(_screenWidth, _screenHeight);
    }
    private void initializeGUI(double _width, double _height) throws IOException {
        this.createLogicPane();
        double buttonWidth = (this.usedDimension*.95)/2;
        double buttonHeight = this.usedDimension*.05;
        Button loadLogicView = this.buttonStylizer(new Button("Logic View"), "logicView", buttonWidth, buttonHeight, 0);
        Button loadShipView = this.buttonStylizer(new Button("Ship View"), "shipView", buttonWidth, buttonHeight, 0);
        Button returnMainMenu = this.buttonStylizer(new Button("Main Menu"), "main", this.screenWidth - (buttonWidth*2), buttonHeight, 0);
        loadLogicView.setOnAction(event -> {this.loadLogicViewOnAction();});
        loadShipView.setOnAction(event -> {this.loadShipViewOnAction();});
        this.returnMainMenuEvent(returnMainMenu);
        this.loadLogicView = loadLogicView;
        this.loadShipView = loadShipView;
        this.returnMainMenu = returnMainMenu;
        this.screenPane.getChildren().addAll(this.loadLogicView);
        this.screenPane.getChildren().addAll(this.loadShipView);
        this.screenPane.getChildren().addAll(this.returnMainMenu);
        this.screenPane.getChildren().addAll(logicPane);
        this.logicPane.relocate(0,0);
        this.loadLogicView.relocate(0,this.screenHeight - this.loadLogicView.getMinHeight());
        this.loadShipView.relocate(this.loadShipView.getMinWidth(),this.screenHeight - this.loadShipView.getMinHeight());
        this.returnMainMenu.relocate(this.screenWidth-this.returnMainMenu.getMinWidth(), this.screenHeight - this.returnMainMenu.getMinHeight());
    }

    PlayControllerLogic playLogic;
    HashMap battleShips;
    GridPane playerShipPane;
    GridPane logicPane;
    Button loadShipView;
    Button loadLogicView;
    Button returnMainMenu;
    AnchorPane screenPane = new AnchorPane();
    private ResourceGetter resourceGetter = new ResourceGetter();
    double screenWidth;
    double screenHeight;
    double usedDimension;
    double gridScale;

    private void createLogicPane() {
        GridPane temporaryPane = new GridPane();
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 10; y++) {
                Button ledButton = this.buttonStylizer(new Button(), "blue", this.gridScale, this.gridScale, 0);
                ledButton.setOnAction(event -> {this.ledButtonSetOnAction(event);});
                temporaryPane.add(ledButton, x, y);
            }
        }
        temporaryPane.setAlignment(Pos.BASELINE_CENTER);
        temporaryPane.getStylesheets().add(this.resourceGetter.getTopGridCSS());
        this.logicPane = temporaryPane;
    }

        public Button buttonStylizer(Button _button, String _id, double _width, double _height, double _rotate) {
        _button.setId(_id);
        GridPane.setVgrow(_button, Priority.NEVER);
        GridPane.setHgrow(_button, Priority.NEVER);
        _button.setMinSize(_width, _height);
        _button.setRotate(_button.getRotate()+_rotate);
        return _button;
    }

//*****************     EVENTS     *******************

    private void loadLogicViewOnAction() {
        if (this.screenPane.getChildren().contains(this.playerShipPane)) {
            this.screenPane.getChildren().remove(this.playerShipPane);
            this.screenPane.getChildren().add(this.logicPane);
            this.logicPane.relocate(0,0);
        }
    }

    private void loadShipViewOnAction() {
        if (this.screenPane.getChildren().contains(this.logicPane)) {
            this.screenPane.getChildren().remove(this.logicPane);
            this.screenPane.getChildren().add(this.playerShipPane);
            this.playerShipPane.relocate(0,0);
        }
    }

    private void ledButtonSetOnAction(ActionEvent _event) {
        Button curButton = ((Button)_event.getSource());
        String buttonId = curButton.getId();
        String newId = "";
        switch (buttonId) {
            case "blue":
                newId = newId.concat("blueActive");
                break;
            case "blueActive":
                newId = newId.concat("yellow");
                break;
            case "yellow":
                newId = newId.concat("yellowActive");
                break;
            case "yellowActive":
                newId = newId.concat("red");
                break;
            case "red":
                newId = newId.concat("redActive");
                break;
            case "redActive":
                newId = newId.concat("blue");
                break;
            default:
                newId = newId.concat("error");
                break;
        }
        curButton.setId(newId);
    }

        private void returnMainMenuEvent (Button _button) throws IOException {
        _button.setOnAction(event -> {
            try {
                this.playLogic.returnMainMenu(event);
            } catch (IOException ex) {
                Logger.getLogger(ShipSelectionPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

//*****************     GETTERS     *******************

    public void loadPane (AnchorPane _anchorPane) {
        _anchorPane.getChildren().clear();
        _anchorPane.getChildren().addAll(this.screenPane);
    }
//*****************     SETTERS     *******************
}
