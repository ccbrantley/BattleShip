package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/26/2019
 * SettingsMenuView is the visual definition of the settings menu.
 */

import battleship.controller.Controller;
import battleship.models.GraphicEffect;
import battleship.tools.ResourceGetter;
import battleship.tools.ViewAssets;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SettingsMenuView {

    private Controller controller;
    private MusicPlayerView MusicPlayerView;
    private GraphicEffectView graphicEffectView;
    private final double screenWidth = GraphicEffect.getScreenWidth();
    private final double screenHeight = GraphicEffect.getScreenHeight();
    private double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private double buttonWidthRatio = .3;
    private double buttonHeightRatio = .2;
    private AnchorPane parentPane;
    private AnchorPane musicPlayerParentPane;
    private AnchorPane graphicEffectParentPane;
    private VBox menuVBox;
    private ArrayList<Node> menuVBoxArray = new ArrayList();
    private Button mainButton;

    public SettingsMenuView (Controller _controller) {
        // Adding controller for access to events
        this.controller = _controller;
        this.MusicPlayerView = new MusicPlayerView(_controller);
        this.graphicEffectView = new GraphicEffectView(_controller);
        // Creating panes and children of the panes
        this.parentPane = ViewAssets.createAnchorPane("settingsMenuPane", ResourceGetter.getSettingsMenuCSS());
        this.musicPlayerParentPane = this.MusicPlayerView.getParentPane();
        this.graphicEffectParentPane = this.graphicEffectView.getParentPane();
        this.mainButton = ViewAssets.createButton("main", "Main Menu", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio);
        this.menuVBoxArray.add(this.mainButton);
        this.menuVBox = ViewAssets.createVBox(this.menuVBoxArray, 20, "menuVBox", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position
        this.parentPane.getChildren().addAll(this.musicPlayerParentPane, this.mainButton, this.graphicEffectParentPane);
        AnchorPane.setTopAnchor(this.musicPlayerParentPane, 0.0);
        AnchorPane.setBottomAnchor(this.musicPlayerParentPane, 0.0);
        AnchorPane.setLeftAnchor(this.musicPlayerParentPane, 0.0);
        AnchorPane.setBottomAnchor(this.mainButton, 0.0);
        this.mainButton.relocate((this.screenWidth - this.menuVBox.getMinWidth()) / 2, (this.screenHeight-this.menuVBox.getMinHeight()) / 2);
        AnchorPane.setTopAnchor(this.graphicEffectParentPane, 0.0);
        AnchorPane.setBottomAnchor(this.graphicEffectParentPane, 0.0);
        AnchorPane.setRightAnchor(this.graphicEffectParentPane, 0.0);
        // Initialize childrens events
        this.controller.setSceneOnActionEvent(this.mainButton);
    }

//*****************     GETTERS     *******************

    public AnchorPane getParentPane () {
        return parentPane;
    }

}