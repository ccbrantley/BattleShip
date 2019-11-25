package battleship.views.interpreters;

/* @author Area 51 Block Party:
 * Christopher Brantley, Andrew Braswell
 * Last Updated: 11/24/2019
 * This class is the interpreter for the event bus and the shipselectionview.
 * This class will define the protocol behind what happens when an event is
 * meant to be thrown to the ship selection view.
 */

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

    public BattleShipGameViewInterpreter(BattleShipGameView battleShipGameView) {
        this.battleShipGameView = battleShipGameView;
    }

    @Override
    public void catchEvent(Object _event) {
        if(_event instanceof UpdateSectorEvent) {
            UpdateSectorEvent event = ((UpdateSectorEvent)_event);
            int row = event.getRow();
            int column = event.getColumn();
            int rotation = event.getRotation();
            String newId = event.getNewId();
            int rowIndex;
            int columnIndex;
            for(Node curNode : this.battleShipGameView.getShipPane().getChildren()) {
                rowIndex = GridPane.getRowIndex(curNode);
                columnIndex = GridPane.getColumnIndex(curNode);
                if((rowIndex == row) && (columnIndex == column)) {
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

        if(_event instanceof RemoveAllRedLedEvent) {
             this.battleShipGameView.getPinPane().getChildren().forEach((curNode) -> {
                 if(curNode.getId().equals("redActive"))
                curNode.setId("blue");
            });
        }

        if(_event instanceof GameUpdateUserMessage) {
            GameUpdateUserMessage event = (GameUpdateUserMessage)_event;
            String message = event.getMessage();
            Label messageLabel = ViewAssets.createLabel("message", message, true);
            this.battleShipGameView.getMessageBox().getChildren().add(messageLabel);
        }
    }

}
