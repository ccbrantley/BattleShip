package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/30/2019
 *
 * SettingsMenuView is the visual definition of the settings menu.
 */

import battleship.controller.Controller;
import battleship.tools.ResourceGetter;
import battleship.tools.ViewAssets;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SettingsMenuView {
    public SettingsMenuView(Controller _controller) {
        // Adding controller for access to events
        this.controller = _controller;
        this.MusicPlayerView = new MusicPlayerView(_controller);
        this.graphicEffectView = new GraphicEffectView(_controller);
        // Creating panes and children of the panes
        this.parentPane = this.viewAssets.createAnchorPane("settingsMenuPane", this.resources.getSettingsMenuCSS());
        this.musicPlayerParentPane = this.MusicPlayerView.getParentPane();
        this.graphicEffectParentPane = this.graphicEffectView.getParentPane();
        this.mainButton = this.viewAssets.createButton("main", "Main Menu", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio);
        this.menuVBoxArray.add(this.mainButton);
        this.menuVBox = this.viewAssets.createVBox(this.menuVBoxArray, 20, "menuVBox", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position
        this.parentPane.getChildren().addAll(this.musicPlayerParentPane, this.mainButton, this.graphicEffectParentPane);
        AnchorPane.setTopAnchor(this.musicPlayerParentPane, 0.0);
        AnchorPane.setBottomAnchor(this.musicPlayerParentPane, 0.0);
        AnchorPane.setLeftAnchor(this.musicPlayerParentPane, 0.0);
        AnchorPane.setBottomAnchor(this.mainButton, 0.0);
        this.mainButton.relocate((this.screenWidth - this.menuVBox.getMinWidth())/2, (this.screenHeight-this.menuVBox.getMinHeight())/2);
        AnchorPane.setTopAnchor(this.graphicEffectParentPane, 0.0);
        AnchorPane.setBottomAnchor(this.graphicEffectParentPane, 0.0);
        AnchorPane.setRightAnchor(this.graphicEffectParentPane, 0.0);
        // Initialize childrens events
        this.controller.setSceneOnActionEvent(this.mainButton);
    }

    private Controller controller;
    private MusicPlayerView MusicPlayerView;
    private GraphicEffectView graphicEffectView;
    private final double screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
    private final double screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
    private double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private double buttonWidthRatio = .3;
    private double buttonHeightRatio = .2;
    private ResourceGetter resources = new ResourceGetter();
    private ViewAssets viewAssets = new ViewAssets();
    private AnchorPane parentPane;
    private AnchorPane musicPlayerParentPane;
    private AnchorPane graphicEffectParentPane;
    private VBox menuVBox;
    private ArrayList<Node> menuVBoxArray = new ArrayList();
    private Button mainButton;

//*****************     GETTERS     *******************

    public Controller getController() {
        return controller;
    }

    public MusicPlayerView getMusicPlayerView() {
        return MusicPlayerView;
    }

    public GraphicEffectView getGraphicEffectView() {
        return graphicEffectView;
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
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

    public ResourceGetter getResources() {
        return resources;
    }

    public ViewAssets getViewAssets() {
        return viewAssets;
    }

    public AnchorPane getParentPane() {
        return parentPane;
    }

    public AnchorPane getMusicPlayerParentPane() {
        return musicPlayerParentPane;
    }

    public AnchorPane getGraphicEffectParentPane() {
        return graphicEffectParentPane;
    }

    public VBox getMenuVBox() {
        return menuVBox;
    }

    public ArrayList<Node> getMenuVBoxArray() {
        return menuVBoxArray;
    }

    public Button getMainButton() {
        return mainButton;
    }

//*****************     SETTERS     *******************

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setMusicPlayerView(MusicPlayerView MusicPlayerView) {
        this.MusicPlayerView = MusicPlayerView;
    }

    public void setGraphicEffectView(GraphicEffectView graphicEffectView) {
        this.graphicEffectView = graphicEffectView;
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

    public void setResources(ResourceGetter resources) {
        this.resources = resources;
    }

    public void setViewAssets(ViewAssets viewAssets) {
        this.viewAssets = viewAssets;
    }

    public void setParentPane(AnchorPane parentPane) {
        this.parentPane = parentPane;
    }

    public void setMusicPlayerParentPane(AnchorPane musicPlayerParentPane) {
        this.musicPlayerParentPane = musicPlayerParentPane;
    }

    public void setGraphicEffectParentPane(AnchorPane graphicEffectParentPane) {
        this.graphicEffectParentPane = graphicEffectParentPane;
    }

    public void setMenuVBox(VBox menuVBox) {
        this.menuVBox = menuVBox;
    }

    public void setMenuVBoxArray(ArrayList<Node> menuVBoxArray) {
        this.menuVBoxArray = menuVBoxArray;
    }

    public void setMainButton(Button mainButton) {
        this.mainButton = mainButton;
    }

}