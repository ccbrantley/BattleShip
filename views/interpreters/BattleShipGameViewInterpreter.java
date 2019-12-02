package battleship.views.interpreters;

/* @author Area 51 Block Party:
 * Christopher Brantley, Andrew Braswell
 * Last Updated: 11/25/2019
 * This class is the interpreter for the event bus and the shipselectionview.
 * This class will define the protocol behind what happens when an event is
 * meant to be thrown to the ship selection view.
 */

import battleship.models.BattleShipBoard;
import battleship.models.BattleShipPlayer;
import battleship.tools.ViewAssets;
import battleship.tools.Listener;
import battleship.tools.events.*;
import battleship.views.BattleShipGameView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BattleShipGameViewInterpreter implements Listener {

    private final BattleShipGameView battleShipGameView;

    public BattleShipGameViewInterpreter (BattleShipGameView battleShipGameView) {
        this.battleShipGameView = battleShipGameView;
    }

    @Override
    public void catchEvent (Object _event) {
        if (_event instanceof UpdateSectorEvent) {
            UpdateSectorEvent event = ((UpdateSectorEvent)_event);
            int row = event.getRow();
            int column = event.getColumn();
            int rotation = event.getRotation();
            String newId = event.getNewId();
            int rowIndex;
            int columnIndex;
            for (Node curNode : this.battleShipGameView.getShipPane().getChildren()) {
                rowIndex = GridPane.getRowIndex(curNode);
                columnIndex = GridPane.getColumnIndex(curNode);
                if ((rowIndex == row) && (columnIndex == column)) {
                    Button newButton = ViewAssets.createGridButton(newId, rotation, "");
                    if(!("grid".equals(newId))) {
                    } else {
                    }
                    this.battleShipGameView.getShipPane().getChildren().remove(curNode);
                     this.battleShipGameView.getShipPane().add(newButton, columnIndex, rowIndex);
                    return;
                }
            }
        }

        if (_event instanceof RedActiveLedEvent) {
             this.battleShipGameView.getPinPane().getChildren().forEach((curNode) -> {
                 if(curNode.getId().equals("redActive"))
                curNode.setId("blue");
            });
        }

        if (_event instanceof GameMessageEvent) {
            GameMessageEvent event = (GameMessageEvent)_event;
            String message = event.getMessage();
            Label messageLabel = ViewAssets.createLabel("message", message, true);
            this.battleShipGameView.getMessageBox().getChildren().add(messageLabel);
        }

        if (_event instanceof ShipHitEvent) {
            ShipHitEvent event = (ShipHitEvent)_event;
            if (!this.checkIfLocal(event.getPlayer())) {
                return;
            }
            int row = event.getRow();
            int column = event.getColumn();
            double paneLength = this.battleShipGameView.getShipPane().getMinHeight();
            double childSize = paneLength / BattleShipBoard.BOARDSIZE;
            double paneLocationX = this.battleShipGameView.getShipPane().getLayoutX();
            double paneLocationY = this.battleShipGameView.getShipPane().getLayoutY();
            double xPos = paneLocationX + (column * childSize) - 220;
            double yPos = paneLocationY + (row * childSize) - 220;
            this.battleShipGameView.getAnimator().setImageViewLayout(xPos, yPos);
            this.battleShipGameView.getAnimator().playAnimation();
            this.battleShipGameView.getAnimator().getImageView().toFront();
        }

        if (_event instanceof UpdatePinEvent) {
            UpdatePinEvent event = (UpdatePinEvent)_event;
            if (!this.checkIfLocal(event.getPlayer())) {
                return;
            }
            int row = event.getRow();
            int column = event.getColumn();
            String color = event.getColor();
            for (Node curNode : this.battleShipGameView.getPinPane().getChildren()) {
                if (GridPane.getRowIndex(curNode) == row) {
                    if (GridPane.getColumnIndex(curNode) == column) {
                        curNode.setId(color);
                    }
                }
            }
        }

    }

    public Boolean checkIfLocal (int _playerTeam) {
        return _playerTeam == BattleShipPlayer.LOCAL;
    }

}