package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/26/2019
 * MainMenuView is the visual definition of the main menu.
 */

import battleship.controller.Controller;
import battleship.models.GraphicEffect;
import battleship.tools.ViewAssets;
import battleship.tools.ResourceGetter;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainMenuView {

    Controller controller;
    private final double screenWidth = GraphicEffect.getScreenWidth();
    private final double screenHeight = GraphicEffect.getScreenHeight();
    // Choosing the smallest 1:1 ratio to display our program on
    private double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private double buttonWidthRatio = .80;
    private double buttonHeightRatio = .15;
    private AnchorPane parentPane;
    private VBox mainMenuVBox;
    private ArrayList<Node> menuButtonArray = new ArrayList<Node>();
    private Label gameLogo;
    private Button playButton;
    private Button settingButton;
    private Button quitButton;

    public MainMenuView (Controller _controller) {
        // Adding controller for access to events
        this.controller = _controller;
        // Creating pane and children of the pane
        this.parentPane = ViewAssets.createAnchorPane("mainMenuPane", ResourceGetter.getMainMenuCSS());
        this.gameLogo = ViewAssets.createLabel("battleShipLabel", "Battleship", this.screenSize * this.buttonWidthRatio, this.screenSize * .30, false);
        this.playButton = ViewAssets.createButton("gameTypeSelection", "Play", this.screenSize * this.buttonHeightRatio);
        this.settingButton = ViewAssets.createButton("settings", "Settings", this.screenSize * this.buttonHeightRatio);
        this.quitButton = ViewAssets.createButton("quit", "Quit", this.screenSize * this.buttonHeightRatio);
        // Adding all children to array, then using array to populate VBox
        this.menuButtonArray.add(this.gameLogo);
        this.menuButtonArray.add(this.playButton);
        this.menuButtonArray.add(this.settingButton);
        this.menuButtonArray.add(this.quitButton);
        this.mainMenuVBox = ViewAssets.createVBox(this.menuButtonArray, (.25 / 8) * this.screenSize, "mainMenuVBox", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position
        this.parentPane.getChildren().addAll(this.mainMenuVBox);
        this.mainMenuVBox.relocate((this.screenWidth - this.screenSize * this.buttonWidthRatio) / 2 - (.25 / 8) * this.screenSize, 0);
        // Initialize childrens events
        this.controller.setcloseGuiOnActionEvent(this.quitButton);
        this.controller.setSceneOnMousePress(this.settingButton);
        this.controller.setSceneOnMousePress(this.playButton);
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