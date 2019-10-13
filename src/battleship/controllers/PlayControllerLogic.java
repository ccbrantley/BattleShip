package battleship.controllers;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/12/2019
 */

import battleship.models.LoaderGetter;
import battleship.models.MapPane;
import battleship.models.MappingPane;
import battleship.models.ResourceGetter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

public class PlayControllerLogic {

    public PlayControllerLogic (FXMLPlayController _controller) {
     this.playController = _controller;
    }

    FXMLPlayController playController;
    private LoaderGetter loaderGetter;
    private final ResourceGetter resourceGetter = new ResourceGetter();
    private int BOARDCOLUMNSIZE = 7;
    private int BOARDROWSIZE = 7;

    public void returnMainMenu(ActionEvent event) throws IOException {
        this.loaderGetter.getMainController().getLogic().setScene(event);
    }

    public void rotateGridPaneEvent (ScrollEvent _event) {
        GridPane rotatePane = (GridPane)_event.getSource();
        int colIndex = GridPane.getColumnIndex(rotatePane);
        int rowIndex = GridPane.getRowIndex(rotatePane);
        this.playController.getPlayerShipPane().getChildren().remove(rotatePane);

        if(rotatePane.getId().endsWith("V")) {
            this.playController.getPlayerShipPane().add(this.playController.getCarrierH(),colIndex,rowIndex,5,1);
        }
        else {
            this.playController.getPlayerShipPane().add(this.playController.getCarrierV(),colIndex,rowIndex,1,5);
        }
    }

    public void moveChildOfGridEvent (KeyEvent event) {
        GridPane eventPane = (GridPane)event.getSource();
        int colIndex = GridPane.getColumnIndex(eventPane);
        int rowIndex = GridPane.getRowIndex(eventPane);
        int colSpan = GridPane.getColumnSpan(eventPane);
        int rowSpan = GridPane.getRowSpan(eventPane);
        switch (event.getText().toUpperCase()) {
            case "D":
                GridPane.setColumnIndex(eventPane, this.gridBoundaryCheck((colIndex+colSpan)+1) ? colIndex+1 : colIndex);
                break;
            case "A":
                GridPane.setColumnIndex(eventPane, this.gridBoundaryCheck(colIndex-1) ? colIndex-1 : colIndex);
                break;
            case "W":
                GridPane.setRowIndex(eventPane, this.gridBoundaryCheck(rowIndex-1) ? rowIndex-1 : rowIndex);
                break;
            case "S":
                GridPane.setRowIndex(eventPane, this.gridBoundaryCheck((rowIndex+rowSpan)+1) ? rowIndex+1 : rowIndex);
                break;
        }
    }

    private Boolean gridBoundaryCheck (int _index) {
        return !(_index < 0 | _index > this.BOARDROWSIZE | _index > this.BOARDCOLUMNSIZE);
    }

//*****************     GETTERS     *******************

    public MappingPane getChildren() {
        MappingPane mainPane = new MappingPane();
        //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeight
        mainPane.mapToPane(new MapPane(this.playController.getPlayerLogicPane(), "top", "center", 1, 1, false, false));
        mainPane.mapToPane(new MapPane(this.playController.getPlayerShipPane(), "bottom","center", 1, 1, false, false));
        mainPane.mapToPane(new MapPane(this.playController.getMenuPane(), "bottom", "right", 1, 1, false, false));
        return mainPane;
    }

    public static Predicate<Node> isButton(){
        return p -> (p instanceof Button);
    }

    public Button getSectorFromAlpha(String _sector) {
        GridPane shipPane = this.playController.getPlayerShipPane();
        Button actualSector;
        ArrayList<Button> temporaryList = new ArrayList();
        shipPane.getChildren().filtered(isButton()).forEach(child -> {temporaryList.add((Button)child);});
        for(Button child : temporaryList){
            if(child.getId().equals(_sector)){
                return child;
            }
        }
        return null;
    }

//*****************     SETTERS     *******************

    public void setButtonState(ActionEvent _event) {
        Button pressedButton = (Button)_event.getSource();
        String blueButtonstyleSheet = this.resourceGetter.getBlueButtonCSS();
        String yellowButtonstyleSheet = this.resourceGetter.getYellowButtonCSS();
        String redButtonStylesheet = this.resourceGetter.getRedButtonCSS();
        if(pressedButton.getStylesheets().contains(blueButtonstyleSheet)){
            setStyleSheet(pressedButton,blueButtonstyleSheet,yellowButtonstyleSheet);
        }
        else if (pressedButton.getStylesheets().contains(yellowButtonstyleSheet)){
            setStyleSheet(pressedButton,yellowButtonstyleSheet,redButtonStylesheet);
        }
        else if (pressedButton.getStylesheets().contains(redButtonStylesheet)){
            setStyleSheet(pressedButton,redButtonStylesheet,blueButtonstyleSheet);
        }
        else{
            setStyleSheet(pressedButton,blueButtonstyleSheet,yellowButtonstyleSheet);
        }
    }
        //Legacy Code
        public void setCarrier() {
        Button addingButton = new Button();
        Button addingButton2 = new Button();
        Button addingButton3 = new Button();
        addingButton.setId("Carrier");
        addingButton2.setId("Carrier");
        addingButton3.setId("Battleship");
        addingButton.getStylesheets().add(this.resourceGetter.getCarrierCSS());
        addingButton2.getStylesheets().add(this.resourceGetter.getCarrierCSS());
        addingButton3.getStylesheets().add(this.resourceGetter.getBattleShipCSS());
        //column index rowe index, column span, row span

        this.playController.getPlayerShipPane().add(addingButton, 0,1,4,2);
        addingButton2.setRotate(90);
        this.playController.getPlayerShipPane().add(addingButton2,3,3,4,2);
        this.playController.getPlayerShipPane().add(addingButton3, 1, 6, 5, 6);
    }

    private void setStyleSheet(Object _node, String _oldStylesheet, String _newStylesheet) {
        if(_node instanceof Button){
            Button button = (Button)_node;
            button.getStylesheets().remove(_oldStylesheet);
            button.getStylesheets().add(_newStylesheet);
        }
    }

    public void setLoaderGetter(LoaderGetter _loaderGetter) {
        this.loaderGetter = _loaderGetter;
    }
}
