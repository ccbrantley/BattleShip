/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.views;

import battleship.controller.Controller;
import battleship.models.BattleShipGame;
import battleship.models.GraphicEffect;
import battleship.tools.ResourceGetter;
import battleship.tools.ViewAssets;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GameTypeSelectionView {
    Controller controller;
    private final double screenWidth = GraphicEffect.getScreenWidth();
    private final double screenHeight = GraphicEffect.getScreenHeight();
    // Choosing the smallest 1:1 ratio to display our program on
    private double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private double buttonWidthRatio = .80;
    private double buttonHeightRatio = .15;
    private AnchorPane parentPane;
    private VBox gameTypeVBox;
    private ArrayList<Node> gameTypeArray = new ArrayList();
    private Button pvbButton;
    private Button bvbButton;
    private Button confirmSelection;
    private Button mainMenuButton;

    public GameTypeSelectionView (Controller _controller) {
        // Adding controller for access to events
        this.controller = _controller;
        // Creating pane and children of the pane
        this.parentPane = ViewAssets.createAnchorPane("gameTypePane", ResourceGetter.getGameTypeSelectionCSS());
        this.pvbButton = ViewAssets.createButton(String.valueOf(BattleShipGame.PVBGAME), "PVB", this.buttonHeightRatio * this.screenSize);
        this.bvbButton = ViewAssets.createButton(String.valueOf(BattleShipGame.BVBGAME), "BVB", this.buttonHeightRatio * this.screenSize);
        this.confirmSelection = ViewAssets.createButton("confirmSelection", "Confirm", this.buttonHeightRatio * this.screenSize);
        this.mainMenuButton = ViewAssets.createButton("main", "Main Menu", this.buttonHeightRatio * this.screenSize);
        this.gameTypeArray.add(this.pvbButton);
        this.gameTypeArray.add(this.bvbButton);
        this.gameTypeArray.add(this.confirmSelection);
        this.gameTypeArray.add(this.mainMenuButton);
        // Adding all children to array, then using array to populate VBox
        this.gameTypeVBox = ViewAssets.createVBox(this.gameTypeArray, (.25 / 8) * this.screenSize, "gameTypeVBox", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position
        this.parentPane.getChildren().add(this.gameTypeVBox);
        this.gameTypeVBox.relocate((this.screenWidth - (this.gameTypeVBox.getMinWidth()))/2, 0);
        this.controller.setGameTypeOnPressEvent(this.pvbButton);
        this.controller.setGameTypeOnPressEvent(this.bvbButton);
        this.controller.initializeGameOnPressEvent(this.confirmSelection);
        this.controller.setSceneOnMousePress(this.mainMenuButton);
    }

//*****************     GETTERS     *******************

    public AnchorPane getParentPane () {
        return this.parentPane;
    }

//*****************     SETTERS     *******************

    public void setParentPane (AnchorPane parentPane) {
        this.parentPane = parentPane;
    }

}