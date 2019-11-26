package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/25/2019
 *  BattleShipGameView is the visual for the Battle Ship Game.
 */

import battleship.controller.Controller;
import battleship.models.BattleShipBoard;
import battleship.models.BattleShipGame;
import battleship.models.GraphicEffect;
import battleship.views.interpreters.BattleShipGameViewInterpreter;
import battleship.tools.ResourceGetter;
import battleship.tools.ViewAssets;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BattleShipGameView {

    private Controller controller;
    private final double screenWidth = GraphicEffect.getScreenWidth();
    private final double screenHeight = GraphicEffect.getScreenHeight();
    private final double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private final double buttonWidthRatio = .25;
    private final double buttonHeightRatio = .10;
    private final double gridWidthRatio = .80;
    private final double gridHeightRatio = .80;
    private final AnchorPane parentPane;
    private final GridPane shipPane;
    private final GridPane pinPane;
    private final ScrollPane gameUpdatesScrollPane;
    private final VBox messageBox;
    private final HBox menuBarHBox;
    private ArrayList<Node> menuBarHBoxArray = new ArrayList();
    private final Button switchPaneButton;
    private final Button fireButton;
    private final Button mainMenuButton;
    private final Button quitGameButton;
    private final BattleShipGameViewInterpreter interpreter = new BattleShipGameViewInterpreter(this);

    public BattleShipGameView(Controller _controller) {
        // Adding controller for access to events.
        this.controller = _controller;
        // Creating pane and children of the pane.
        this.parentPane = ViewAssets.createAnchorPane("battleShipGamePane", ResourceGetter.getBattleShipGameCSS());
        this.shipPane = ViewAssets.createRowByColumnPane(BattleShipBoard.BOARDSIZE, BattleShipBoard.BOARDSIZE, "grid", "", this.screenSize * this.gridWidthRatio , this.screenSize * this.gridHeightRatio);
        this.pinPane = ViewAssets.createRowByColumnPane(BattleShipBoard.BOARDSIZE, BattleShipBoard.BOARDSIZE, "blue", "", this.screenSize * this.gridWidthRatio , this.screenSize * this.gridHeightRatio);
        this.messageBox = ViewAssets.createVBox("messageArea");
        this.gameUpdatesScrollPane = ViewAssets.createMessageScrollPane(this.messageBox,this.shipPane.getMinWidth(), this.screenSize * this.buttonHeightRatio);
        this.switchPaneButton = ViewAssets.createButton("switch", "Switch view", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        this.fireButton = ViewAssets.createButton("fire", "Fire", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        this.mainMenuButton = ViewAssets.createButton("main", "Main Menu", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        this.quitGameButton = ViewAssets.createButton("main", "Quit Game", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        this.menuBarHBoxArray.add(switchPaneButton);
        this.menuBarHBoxArray.add(fireButton);
        this.menuBarHBoxArray.add(mainMenuButton);
        this.menuBarHBoxArray.add(quitGameButton);
        this.menuBarHBox = ViewAssets.createHBox(menuBarHBoxArray, 50, "menuBar",  this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position.
        this.parentPane.getChildren().addAll(this.shipPane, this.gameUpdatesScrollPane, this.menuBarHBox);
        this.shipPane.relocate(0, 0);
        this.gameUpdatesScrollPane.relocate(0, this.screenHeight - (2 * (this.screenSize * this.buttonHeightRatio)));
        this.menuBarHBox.relocate(0, this.screenHeight - (this.screenSize * this.buttonHeightRatio));
        // Initialize childrens events.
        this.controller.setSceneOnActionEvent(this.mainMenuButton);
        // Visual event for switching the ship pane and pin pane.
        this.switchPaneButton.setOnAction(event -> {
            this.switchShipPinPaneEvent();
        });
        this.pinPane.getChildren().forEach(ledButton -> {
            this.controller.ledButtonSetOnAction((Button)ledButton);
        });
        this.controller.fireEvent(fireButton);
        this.controller.setSceneAndRemoveGame(quitGameButton);
        // Adding our interpreter to the event bus.
        BattleShipGame.getEventBus().addListener(interpreter);
    }

    /** Graphical Event for switching between the ship and the pin pane.
     */
    private void switchShipPinPaneEvent () {
            if(parentPane.getChildren().contains(this.shipPane)) {
                parentPane.getChildren().remove(this.shipPane);
                parentPane.getChildren().add(this.pinPane);
                this.pinPane.relocate(0, 0);
            }
            else {
                parentPane.getChildren().remove(this.pinPane);
                parentPane.getChildren().add(this.shipPane);
                this.shipPane.relocate(0, 0);
            }
    }

//*****************     GETTERS     *******************

    public VBox getMessageBox() {
        return messageBox;
    }

    public AnchorPane getParentPane() {
        return parentPane;
    }

    public GridPane getShipPane() {
        return shipPane;
    }

    public GridPane getPinPane() {
        return pinPane;
    }

}
