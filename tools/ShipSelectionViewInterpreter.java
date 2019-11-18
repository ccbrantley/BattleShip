package battleship.tools;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/11/2019
 * This class is the interpreter for the event bus and the shipselectionview.
 * This class will define the protocol behind what happens when an event is
 * meant to be thrown to the ship selection view.
 */

import battleship.tools.events.*;
import battleship.views.ShipSelectionView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ShipSelectionViewInterpreter {
    public ShipSelectionViewInterpreter(ShipSelectionView shipSelectionView) {
        this.shipSelectionView = shipSelectionView;
    }
    private final ShipSelectionView shipSelectionView;

    public void catchEvent(Object _event) {
        if(_event instanceof UpdateSectorEvent) {
            UpdateSectorEvent event = ((UpdateSectorEvent)_event);
            int row = event.getRow();
            int column = event.getColumn();
            int rotation = event.getRotation();
            String newId = event.getNewId();
            int rowIndex;
            int columnIndex;
            for(Node curNode : this.shipSelectionView.getShipSelectionPane().getChildren()) {
                rowIndex = GridPane.getRowIndex(curNode);
                columnIndex = GridPane.getColumnIndex(curNode);
                if((rowIndex == row) && (columnIndex == column)) {
                    Button newButton = ViewAssets.createGridButton(newId, rotation, "");
                    if(!("grid".equals(newId))) {
                        this.shipSelectionView.setShipSelectionPaneShipEvents(newButton);
                    } else {
                        this.shipSelectionView.setShipSelectionPaneGridEvents(newButton);
                    }
                    this.shipSelectionView.getShipSelectionPane().getChildren().remove(curNode);
                    this.shipSelectionView.getShipSelectionPane().add(newButton, columnIndex, rowIndex);
                    return;
                }
            }
        }

        if(_event instanceof ClearGridEvent) {
            this.shipSelectionView.getShipSelectionPane().getChildren().forEach(child -> {
                child.setId("grid");
            });
        }
    }

}
