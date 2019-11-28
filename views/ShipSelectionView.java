package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/26/2019
 * ShipSelectionView is the visual for the ship selection view.
 */

import battleship.controller.Controller;
import battleship.models.BattleShipBoard;
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

    private final Controller controller;
    private final double screenWidth = GraphicEffect.getScreenWidth();
    private final double screenHeight = GraphicEffect.getScreenHeight();
    private final double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private final double buttonWidthRatio = .10;
    private final double buttonHeightRatio = .10;
    private final AnchorPane parentPane;
    public GridPane shipSelectionPane;
    private final Button mainMenuButton;
    private final Button confirmLayoutButton;
    private final Button randomshipLayoutButton;
    private final ShipSelectionViewInterpreter interpreter = new ShipSelectionViewInterpreter(this);

    public ShipSelectionView (Controller _controller) {
        // Adding controller for access to events.
        this.controller = _controller;
        // Creating pane and children of the pane.
        this.parentPane = ViewAssets.createAnchorPane("shipSelectionPane", ResourceGetter.getShipSelectionCSS());
        this.shipSelectionPane = ViewAssets.createRowByColumnPane(BattleShipBoard.BOARDSIZE, BattleShipBoard.BOARDSIZE, "grid", "", this.screenSize * .90, this.screenSize * .90);
        this.mainMenuButton = ViewAssets.createButton("main", "Main Menu", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        this.randomshipLayoutButton = ViewAssets.createButton("random", "Random Layout", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        this.confirmLayoutButton = ViewAssets.createButton("play", "Confirm Layout", this.screenSize * this.buttonWidthRatio , this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position.
        this.parentPane.getChildren().addAll(this.shipSelectionPane, this.mainMenuButton, this.randomshipLayoutButton, this.confirmLayoutButton);
        this.mainMenuButton.relocate(0, ((this.screenHeight) - (this.screenSize * this.buttonHeightRatio)) / 2);
        this.randomshipLayoutButton.relocate(0, ((this.screenHeight) - (this.screenSize * this.buttonHeightRatio)));
        this.shipSelectionPane.relocate(((this.screenWidth) - (this.screenSize * .90)) / 2, ((this.screenHeight)-(this.screenSize * .90)) / 2);
        this.confirmLayoutButton.relocate((this.screenWidth - (this.screenSize * this.buttonWidthRatio)), ((this.screenHeight) - (this.screenSize * this.buttonWidthRatio)) / 2);
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

    /** Sets the events specific to the ship buttons.
     * @param _curNode
     */
    public void setShipSelectionPaneShipEvents (Node _curNode) {
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

    /** Removes the events specific to ship buttons.
     * @param _curNode
     */
    public void removeShipSelectionPaneShipEvents (Node _curNode) {
        _curNode.setOnKeyPressed(null);
        _curNode.setOnDragDetected(null);
        _curNode.setOnScroll(null);
    }

    /** Sets the events specific to grid buttons.
     *  @param _curNode
     */
    public void setShipSelectionPaneGridEvents (Node _curNode) {
        _curNode.setOnDragOver(event ->{
            this.controller.gridOnDragOverEvent(event);
        });
        _curNode.setOnDragDropped(event -> {
            this.controller.gridOnDragDroppedEvent(event);
        });
    }

    /** Removes the events specific to grid buttons.
     *  @param _curNode
     */
    public void removeShipSelectionPaneGridEvents (Node _curNode) {
        _curNode.setOnDragOver(null);
        _curNode.setOnDragDropped(null);
    }

//*****************     GETTERS     *******************

    public GridPane getShipSelectionPane () {
        return this.shipSelectionPane;
    }

    public AnchorPane getParentPane () {
        return this.parentPane;
    }

}
