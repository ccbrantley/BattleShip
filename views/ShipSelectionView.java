package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/11/2019
 * ShipSelectionView is the definition of the visual for the ship selection view.
 */

import battleship.controller.Controller;
import battleship.models.BattleShipGame;
import battleship.tools.ResourceGetter;
import battleship.tools.ShipSelectionViewInterpreter;
import battleship.tools.ViewAssets;
import java.awt.GraphicsEnvironment;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ShipSelectionView {
    public ShipSelectionView(Controller _controller) {
        // Adding controller for access to events
        this.controller = _controller;
        // Creating pane and children of the pane
        this.parentPane = this.viewAssets.createAnchorPane("shipSelectionPane", resources.getShipSelectionCSS());
        this.shipSelectionPane = this.viewAssets.createRowByColumnPane(10, 10, "grid", "", this.screenSize * .90, this.screenSize * .90);
        this.mainMenuButton = this.viewAssets.createButton("main", "Main Menu", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        this.randomshipLayoutButton = this.viewAssets.createButton("random", "Random Layout", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        this.confirmLayoutButton = this.viewAssets.createButton("game", "Confirm Layout", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position
        this.parentPane.getChildren().addAll(this.shipSelectionPane, this.mainMenuButton, this.randomshipLayoutButton, this.confirmLayoutButton);
        this.mainMenuButton.relocate(0, ((this.screenHeight)-(this.screenSize * this.buttonHeightRatio))/2);
        this.randomshipLayoutButton.relocate(0, ((this.screenHeight)-(this.screenSize * this.buttonHeightRatio)));
        this.shipSelectionPane.relocate(((this.screenWidth)-(this.screenSize * .90))/2, ((this.screenHeight)-(this.screenSize * .90))/2);
        this.confirmLayoutButton.relocate((this.screenWidth-(this.screenSize * this.buttonWidthRatio)), ((this.screenHeight)-(this.screenSize * this.buttonWidthRatio))/2);
        // Initialize childrens events
        this.controller.setSceneOnActionEvent(this.mainMenuButton);
        this.shipSelectionPane.getChildren().forEach(grid -> {
            this.setShipSelectionPaneGridEvents(grid);
        });
        this.controller.setOnMousePressRandomizeShips(this.randomshipLayoutButton);
        BattleShipGame.getEventBus().addListener(this.interpreter);
    }
    private final Controller controller;
    private final double screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
    private final double screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
    private final double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private final double buttonWidthRatio = .10;
    private final double buttonHeightRatio = .10;
    private final ResourceGetter resources = new ResourceGetter();
    private final ViewAssets viewAssets = new ViewAssets();
    private final AnchorPane parentPane;
    private final GridPane shipSelectionPane;
    private final Button mainMenuButton;
    private final Button confirmLayoutButton;
    private final Button randomshipLayoutButton;
    private final ShipSelectionViewInterpreter interpreter = new ShipSelectionViewInterpreter(this);

    public void setShipSelectionPaneShipEvents(Node _curNode) {
        _curNode.setOnKeyPressed(event -> {
            this.controller.shipMovementEvent(event);
        });
        _curNode.setOnDragDetected(event -> {
            this.controller.shipOnDragDetectedEvent(event);
        });
        _curNode.setOnScroll(event -> {
            this.controller.shipOnScrollEvent(event);
        });
    }

    public void setShipSelectionPaneGridEvents(Node _curNode) {
        _curNode.setOnDragOver(event ->{
            this.controller.gridOnDragOverEvent(event);
        });

        _curNode.setOnDragDropped(event -> {
            this.controller.gridOnDragDroppedEvent(event);
        });
    }

//*****************     GETTERS     *******************

    public Controller getController() {
        return controller;
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

    public GridPane getShipSelectionPane() {
        return shipSelectionPane;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }

    public Button getConfirmLayoutButton() {
        return confirmLayoutButton;
    }

//*****************     SETTERS     *******************

}
