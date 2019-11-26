package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/28/2019
 *
 * MainMenuView is the visual definition of the main menu.
 */
import battleship.controller.Controller;
import battleship.models.GraphicEffect;
import battleship.tools.ViewAssets;
import battleship.tools.ResourceGetter;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainMenuView {
    public MainMenuView(Controller _controller) {
        // Adding controller for access to events
        this.controller = _controller;
        // Creating pane and children of the pane
        this.parentPane = ViewAssets.createAnchorPane("mainMenuPane", ResourceGetter.getMainMenuCSS());
        this.gameLogo = ViewAssets.createLabel("battleShipLabel", "Battleship", this.screenSize * this.buttonWidthRatio, this.screenSize * .30, false);
        this.playButton = ViewAssets.createButton("shipSelection", "Play", this.screenSize * this.buttonHeightRatio);
        this.settingButton = ViewAssets.createButton("settings", "Settings", this.screenSize * this.buttonHeightRatio);
        this.quitButton = ViewAssets.createButton("quit", "Quit", this.screenSize * this.buttonHeightRatio);
        // Adding all children to array, then using array to populate VBox
        this.menuButtonArray.add(this.gameLogo);
        this.menuButtonArray.add(this.playButton);
        this.menuButtonArray.add(this.settingButton);
        this.menuButtonArray.add(this.quitButton);
        this.mainMenuVBox = ViewAssets.createVBox(this.menuButtonArray, (.25/8) * this.screenSize, "mainMenuVBox", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position
        this.parentPane.getChildren().addAll(this.mainMenuVBox);
        this.mainMenuVBox.relocate((this.screenWidth - this.screenSize * this.buttonWidthRatio)/2-(.25/8) * this.screenSize, 0);
        // Initialize childrens events
        this.controller.setcloseGuiOnActionEvent(this.quitButton);
        this.controller.setSceneOnActionEvent(this.settingButton);
        this.controller.setSceneOnActionEvent(this.playButton);
    }

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

//*****************     GETTERS     *******************

    public Controller getController() {
        return controller;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public double getButtonWidthRatio() {
        return buttonWidthRatio;
    }

    public double getButtonHeightRatio() {
        return buttonHeightRatio;
    }

    public AnchorPane getParentPane() {
        return parentPane;
    }

    public VBox getMainMenuVBox() {
        return mainMenuVBox;
    }

    public ArrayList<Node> getMenuButtonArray() {
        return menuButtonArray;
    }

    public Label getGameLogo() {
        return gameLogo;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getSettingButton() {
        return settingButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }

//*****************     SETTERS     *******************

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public void setButtonWidthRatio(double buttonWidthRatio) {
        this.buttonWidthRatio = buttonWidthRatio;
    }

    public void setButtonHeightRatio(double buttonHeightRatio) {
        this.buttonHeightRatio = buttonHeightRatio;
    }

    public void setParentPane(AnchorPane parentPane) {
        this.parentPane = parentPane;
    }

    public void setMainMenuVBox(VBox mainMenuVBox) {
        this.mainMenuVBox = mainMenuVBox;
    }

    public void setMenuButtonArray(ArrayList<Node> menuButtonArray) {
        this.menuButtonArray = menuButtonArray;
    }

    public void setGameLogo(Label gameLogo) {
        this.gameLogo = gameLogo;
    }

    public void setPlayButton(Button playButton) {
        this.playButton = playButton;
    }

    public void setSettingButton(Button settingButton) {
        this.settingButton = settingButton;
    }

    public void setQuitButton(Button quitButton) {
        this.quitButton = quitButton;
    }

}