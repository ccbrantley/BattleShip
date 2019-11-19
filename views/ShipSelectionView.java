package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/11/2019
 * ShipSelectionView is the visual for the ship selection view.
 */

import battleship.controller.Controller;
import battleship.models.BattleShipGame;
import battleship.models.GraphicEffect;
import battleship.tools.ResourceGetter;
import battleship.views.interpreters.ShipSelectionViewInterpreter;
import battleship.tools.ViewAssets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ShipSelectionView {
    public ShipSelectionView(Controller _controller) {
        // Adding controller for access to events.
        this.controller = _controller;
        // Creating pane and children of the pane.
        this.parentPane = ViewAssets.createAnchorPane("shipSelectionPane", ResourceGetter.getShipSelectionCSS());
        this.shipSelectionPane = ViewAssets.createRowByColumnPane(10, 10, "grid", "", this.screenSize * .90, this.screenSize * .90);
        this.mainMenuButton = ViewAssets.createButton("main", "Main Menu", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        this.randomshipLayoutButton = ViewAssets.createButton("random", "Random Layout", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        this.confirmLayoutButton = ViewAssets.createButton("game", "Confirm Layout", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position.
        this.parentPane.getChildren().addAll(this.shipSelectionPane, this.mainMenuButton, this.randomshipLayoutButton, this.confirmLayoutButton);
        this.mainMenuButton.relocate(0, ((this.screenHeight)-(this.screenSize * this.buttonHeightRatio))/2);
        this.randomshipLayoutButton.relocate(0, ((this.screenHeight)-(this.screenSize * this.buttonHeightRatio)));
        this.shipSelectionPane.relocate(((this.screenWidth)-(this.screenSize * .90))/2, ((this.screenHeight)-(this.screenSize * .90))/2);
        this.confirmLayoutButton.relocate((this.screenWidth-(this.screenSize * this.buttonWidthRatio)), ((this.screenHeight)-(this.screenSize * this.buttonWidthRatio))/2);
        // Initialize childrens events.
        this.controller.setSceneOnActionEvent(this.mainMenuButton);
        this.shipSelectionPane.getChildren().forEach(grid -> {
            this.setShipSelectionPaneGridEvents(grid);
        });
        this.controller.setOnMousePressRandomizeShips(this.randomshipLayoutButton);
        this.controller.setSceneOnActionEvent(this.confirmLayoutButton);
        // Adding our interpreter to the event bus.
        BattleShipGame.getEventBus().addListener(this.interpreter);
    }
    private final Controller controller;
    private final double screenWidth = GraphicEffect.getScreenWidth();
    private final double screenHeight = GraphicEffect.getScreenHeight();
    private final double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private final double buttonWidthRatio = .10;
    private final double buttonHeightRatio = .10;
    private final AnchorPane parentPane;
    private final GridPane shipSelectionPane;
    private final Button mainMenuButton;
    private final Button confirmLayoutButton;
    private final Button randomshipLayoutButton;
    private final ShipSelectionViewInterpreter interpreter = new ShipSelectionViewInterpreter(this);

    /** Sets the events for the ship button.
     * @param _curNode
     */
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

    /** Sets the events for the grid button.
     * @param _curNode
     */
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
