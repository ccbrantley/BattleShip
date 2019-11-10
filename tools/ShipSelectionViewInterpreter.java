/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.tools;

import battleship.tools.events.*;
import battleship.views.ShipSelectionView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Christopher
 */
public class ShipSelectionViewInterpreter {

    public void catchEvent(Object _event) {
        if(_event instanceof UpdateSectorEvent) {
            UpdateSectorEvent event = ((UpdateSectorEvent)_event);
            int row = event.getRow();
            int column = event.getColumn();
            int rotation = event.getRotation();
            String newId = event.getNewId();
            int rowIndex;
            int columnIndex;
            for(Node curNode : ShipSelectionView.getShipSelectionPane().getChildren()) {
                rowIndex = GridPane.getRowIndex(curNode);
                columnIndex = GridPane.getColumnIndex(curNode);
                if((rowIndex == row) && (columnIndex == column)) {
                    Button newButton = ViewAssets.createGridButton(newId, rotation, "");
                    if(!("grid".equals(newId))) {
                        ShipSelectionView.setShipSelectionPaneShipEvents(newButton);
                    } else {
                        ShipSelectionView.setShipSelectionPaneGridEvents(newButton);
                    }
                    ShipSelectionView.getShipSelectionPane().getChildren().remove(curNode);
                    ShipSelectionView.getShipSelectionPane().add(newButton, columnIndex, rowIndex);
                    return;
                }
            }
        }

        if(_event instanceof ClearGridEvent) {
            for(Node curNode : ShipSelectionView.getShipSelectionPane().getChildren()) {
                Button gridButton = new Button();
                gridButton.setId("grid");
                ShipSelectionView.setShipSelectionPaneGridEvents(gridButton);
                curNode = gridButton;
            }
        }
    }

}
