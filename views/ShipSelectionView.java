package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/31/2019
 *
 * ShipSelectionView is the definition of the visual for the ship selection view.
 */

import battleship.controller.Controller;
import battleship.tools.ResourceGetter;
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
        this.parentPane.getChildren().addAll(ShipSelectionView.shipSelectionPane, this.mainMenuButton, this.randomshipLayoutButton, this.confirmLayoutButton);
        this.mainMenuButton.relocate(0, ((this.screenHeight)-(this.screenSize * this.buttonHeightRatio))/2);
        this.randomshipLayoutButton.relocate(0, ((this.screenHeight)-(this.screenSize * this.buttonHeightRatio)));
        ShipSelectionView.shipSelectionPane.relocate(((this.screenWidth)-(this.screenSize * .90))/2, ((this.screenHeight)-(this.screenSize * .90))/2);
        this.confirmLayoutButton.relocate((this.screenWidth-(this.screenSize * this.buttonWidthRatio)), ((this.screenHeight)-(this.screenSize * this.buttonWidthRatio))/2);
        // Initialize childrens events
        this.controller.setSceneOnActionEvent(this.mainMenuButton);
        ShipSelectionView.shipSelectionPane.getChildren().forEach(grid -> {
            ShipSelectionView.setShipSelectionPaneGridEvents(grid);
        });
        this.controller.setOnMousePressRandomizeShips(this.randomshipLayoutButton);
    }
    private Controller controller;
    private final double screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
    private final double screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
    private final double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private double buttonWidthRatio = .10;
    private double buttonHeightRatio = .10;
    private ResourceGetter resources = new ResourceGetter();
    private ViewAssets viewAssets = new ViewAssets();
    private AnchorPane parentPane;
    private static GridPane shipSelectionPane;
    private Button mainMenuButton;
    private Button confirmLayoutButton;
    private Button randomshipLayoutButton;

    public static void setShipSelectionPaneShipEvents(Node _curNode) {
        _curNode.setOnKeyPressed(event -> {
            Controller.shipMovementEvent(event);
        });
        _curNode.setOnDragDetected(event -> {
            Controller.shipOnDragDetectedEvent(event);
        });
        _curNode.setOnScroll(event -> {
            Controller.shipOnScrollEvent(event);
        });
    }

    public static void setShipSelectionPaneGridEvents(Node _curNode) {
        _curNode.setOnDragOver(event ->{
            Controller.gridOnDragOverEvent(event);
        });

        _curNode.setOnDragDropped(event -> {
            Controller.gridOnDragDroppedEvent(event);
        });
    }

//*****************     GETTERS     *******************

//*****************     SETTERS     *******************

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

    public static GridPane getShipSelectionPane() {
        return shipSelectionPane;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }

    public Button getConfirmLayoutButton() {
        return confirmLayoutButton;
    }

}
