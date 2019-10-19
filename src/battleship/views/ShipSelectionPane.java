package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/19/2019
 */

import battleship.controllers.PlayControllerLogic;
import battleship.models.ResourceGetter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class ShipSelectionPane {
    public ShipSelectionPane(PlayControllerLogic playLogic, double _screenWidth, double _screenHeight) throws IOException {
        this.playLogic = playLogic;
        this.screenWidth = _screenWidth;
        this.screenHeight = _screenHeight;
        this.gridScale = (((_screenWidth < _screenHeight) ? _screenWidth : _screenHeight)*.95)/10;
        this.initializeGUI(_screenWidth, _screenHeight);
        this.initializeShipPane(_screenWidth, _screenHeight);
    }

    private void initializeGUI(double _width, double _height) throws IOException {
        double newWidth = ((_width-(this.gridScale*10))/2)*.95;
        double newHeight = _height/4*.95;
        Button mainMenuButton = this.buttonStylizer(new Button("Main Menu"), "main",newWidth, newHeight, 0);
        Button shipSelectionButton = this.buttonStylizer(new Button("Confirm Layout"), "shipSelection", newWidth, newHeight, 0);
        mainMenuButton.getStylesheets().add(this.resourceGetter.getMainMenuCSS());
        shipSelectionButton.getStylesheets().add(this.resourceGetter.getMainMenuCSS());
        this.returnMainMenuEvent(mainMenuButton);
        shipSelectionButton.setOnAction(event -> {this.shipSelectionButtonOnAction(event);});
        this.shipSelectionButton = shipSelectionButton;
        this.mainMenuButton = mainMenuButton;
    }

    private void initializeShipPane(double _screenWidth, double  _screenHeight) {
        GridPane temporaryPane = new GridPane();
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 10; y++) {
                Button gridButton = this.buttonStylizer(new Button(), "grid", this.gridScale, this.gridScale, 0);
                temporaryPane.add(gridButton, x, y);
                gridButton.setOnDragOver(event -> {this.gridOnDragOver(event);});
                gridButton.setOnDragDropped(event -> {this.gridOnDragDropped(event);});
            }
        }
        temporaryPane.setAlignment(Pos.BASELINE_CENTER);
        temporaryPane.getStylesheets().add(this.resourceGetter.getBottomGridCSS());
        this.playerShipPane = temporaryPane;
        for(int x = 0; x < 5; x++){
            this.shipPopulate(x, (int) (Math.random() * 2), (int) (Math.random() * 9), (int) (Math.random() * 9));
        }
    }

    PlayControllerLogic playLogic;
    private ResourceGetter resourceGetter = new ResourceGetter();
    private double gridScale;
    private HashMap allShipHashMap = new HashMap();
    private double screenHeight;
    private double screenWidth;
    private final int BOARDCOLUMNSIZE = 10;
    private final int BOARDROWSIZE = 10;
    GridPane playerShipPane;
    Button shipSelectionButton;
    Button mainMenuButton;
    //Enumerations
    public final int CARRIER = 0;
    public final int BATTLESHIP = 1;
    public final int CRUISER = 2;
    public final int SUBMARINE = 3;
    public final int DESTROYER = 4;
    public final int HORIZONTAL = 0;
    public final int VERTICAL = 1;

    private Button buttonStylizer(Button _button, String _id, double _width, double _height, double _rotate) {
        _button.setId(_id);
        GridPane.setVgrow(_button, Priority.NEVER);
        GridPane.setHgrow(_button, Priority.NEVER);
        _button.setMinSize(_width, _height);
        _button.setRotate(_button.getRotate()+_rotate);
        return _button;
    }

    private int determineShipType (String _name) {
        int type = 0;
        switch (_name) {
            case "carrier":
                type = this.CARRIER;
                break;
            case "battleship":
                type = this.BATTLESHIP;
                break;
            case "cruiser":
                type = this.CRUISER;
                break;
            case "submarine":
                type = this.SUBMARINE;
                break;
            case "destroyer":
                type = this.DESTROYER;
                break;
        }
        return type;
    }

    private String determineShipId (int _type) {
        if(_type == this.CARRIER) {
            return "carrier";
        }
        else if (_type == this.BATTLESHIP) {
            return "battleship";
        }
        else if (_type == this.CRUISER) {
            return "cruiser";
        }
        else if (_type == this.SUBMARINE) {
            return "submarine";
        }
        else if (_type == this.DESTROYER) {
            return "destroyer";
        }
        return "error";
    }

    private int determineColumnRange (int _type) {
        if(_type == this.CARRIER) {
            return 5;
        }
        else if (_type == this.BATTLESHIP) {
            return 4;
        }
        else if (_type == this.CRUISER) {
            return 3;
        }
        else if (_type == this.SUBMARINE) {
            return 3;
        }
        else if (_type == this.DESTROYER) {
            return 2;
        }
        return -1;
    }

    // 0 = horizontal, 1 = vertical
    private int determineShipOrientation (String _name) {
        ArrayList ships = ((ArrayList)this.allShipHashMap.get(_name));
        int ship0RowIndex = GridPane.getRowIndex((Node)ships.get(0));
        int ship1RowIndex = GridPane.getRowIndex((Node)ships.get(1));
        if ((ship0RowIndex - ship1RowIndex) == 0){
            return 0;
        }
        return 1;
    }

    private int shipPopulateFixRange (int _index, int _totalRange) {
        if (!(this.gridBoundaryCheck(_index+_totalRange-1))) {
            _index = (this.BOARDROWSIZE-1) - (_totalRange-1);
        }
        if (!(this.gridBoundaryCheck(_index))) {
            _index =  0;
        }
        return _index;
    }

    private Boolean gridBoundaryCheck (int _index) {
        return !(_index < 0 | _index > this.BOARDROWSIZE-1 );
    }

    private void shipPopulate (int _type, int _orientation, int row, int column) {
        String buttonId = this.determineShipId(_type);
        int columnRange = this.determineColumnRange(_type);
        int rowRange = (_orientation == this.HORIZONTAL) ? 1: columnRange;
        columnRange = (rowRange == 1) ? columnRange : 1;
        row = this.shipPopulateFixRange(row,rowRange);
        column = this.shipPopulateFixRange(column, columnRange);
        if (this.allShipHashMap.containsKey(buttonId)) {
            ((ArrayList)this.allShipHashMap.get(buttonId)).forEach((Object child) -> {
                ShipSelectionPane.this.shipRemoveAllEvents((Button)child);
                ShipSelectionPane.this.playerShipPane.getChildren().remove((Button)child);
            });
        }
        int counter = 1;
        ArrayList temporaryList = new ArrayList();
        for (int x = 0; x < rowRange; x++) {
            for (int y = 0; y < columnRange; y++) {
                Button shipButton = this.buttonStylizer(new Button(), buttonId.concat(String.valueOf(counter)), this.gridScale, this.gridScale, (_orientation == this.VERTICAL) ? 90 : 0);
                this.playerShipPane.add(shipButton, y + column, x + row);
                temporaryList.add(shipButton);
                counter++;
            }
        }
        this.allShipHashMap.put(buttonId, temporaryList);
        temporaryList.forEach(child->{this.shipSetAllEvents((Button)child);});
    }

    public void loadPane(AnchorPane _anchorPane) {
        _anchorPane.getChildren().clear();
        _anchorPane.getChildren().addAll(this.playerShipPane, this.mainMenuButton, this.shipSelectionButton);
        AnchorPane.setRightAnchor(this.shipSelectionButton, ((this.screenWidth-(this.gridScale*10))/2)*.025);
        AnchorPane.setLeftAnchor(this.mainMenuButton, ((this.screenWidth-(this.gridScale*10))/2)*.025);
        this.mainMenuButton.setLayoutY((this.screenHeight/2)-(this.mainMenuButton.getMinHeight()/2));
        this.shipSelectionButton.setLayoutY((this.screenHeight/2)-(this.shipSelectionButton.getMinHeight()/2));
        this.playerShipPane.relocate((this.screenWidth/2)-((this.gridScale*this.BOARDROWSIZE)/2), this.screenHeight*.025);
    }

    private void shipSetAllEvents(Button _button) {
        _button.setOnKeyPressed(event -> {this.shipMovementEvent(event);});
        _button.setOnDragDetected(event -> {
            try {
                this.shipOnDragDetected(event);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ShipSelectionPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        _button.setOnScroll(event -> {this.shipOnScroll(event);});
    }

    private void shipRemoveAllEvents(Button _button) {
        _button.setOnScroll(null);
        _button.setOnDragDetected(null);
        _button.setOnKeyPressed(null);
    }

//*****************     EVENTS     *******************

    private void shipSelectionButtonOnAction(ActionEvent _event) {
        Set hashSet = this.allShipHashMap.keySet();
        hashSet.forEach(key -> {
            ArrayList<Button> ship = (ArrayList)this.allShipHashMap.get(key);
            ship.forEach(shipPiece -> this.shipRemoveAllEvents(shipPiece));
        });
        this.playLogic.startBattleShipGame(this.allShipHashMap, this.playerShipPane);
    }

    private void gridOnDragOver (DragEvent _event) {
        Button curButton = (Button)_event.getSource();
        if (_event.getGestureSource() != curButton &&
                _event.getDragboard().hasString()) {
            _event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        _event.consume();
    }

    private void gridOnDragDropped(DragEvent _event){
        Button curButton = (Button)_event.getSource();
        int rowIndex = GridPane.getRowIndex(curButton);
        int columnIndex = GridPane.getColumnIndex(curButton);
        String shipButtonType = _event.getDragboard().getString();
        this.shipPopulate(this.determineShipType(shipButtonType), this.determineShipOrientation(shipButtonType), rowIndex, columnIndex);
         _event.consume();
    }

    private void shipOnScroll(ScrollEvent _event){
        String buttonId = ((Button)_event.getSource()).getId();
        buttonId = buttonId.substring(0,buttonId.length()-1);
        ArrayList allOfShip = (ArrayList)this.allShipHashMap.get(buttonId);
        int arraySize = allOfShip.size();
        Button beginningShip = (Button)allOfShip.get(0);
        double curRotation = beginningShip.getRotate();
        int begColumnIndex = GridPane.getColumnIndex(beginningShip);
        int begRowIndex = GridPane.getRowIndex(beginningShip);
        int shipType = this.determineShipType(buttonId);
        int shipOrientation = this.determineShipOrientation(buttonId) == 1 ? 0 : 1;
        if (curRotation > -1 && curRotation < 1) {
            this.shipPopulate(shipType, shipOrientation, begRowIndex-(arraySize-1), begColumnIndex+(arraySize-1));
        }
        else {
            this.shipPopulate(shipType, shipOrientation, begRowIndex+(arraySize-1), begColumnIndex-(arraySize-1));
        }
    }

    private void shipOnDragDetected(MouseEvent _event) throws FileNotFoundException{
        Button curButton = (Button)_event.getSource();
        String curId = curButton.getId().substring(0,curButton.getId().length()-1);
        Dragboard db = curButton.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(curId);
        db.setContent(content);
        _event.consume();
        ImageView cursorView = new ImageView(new Image(new FileInputStream("src\\assets\\images\\ship\\".concat(curId).concat(".png")), 100, 100, true, false));
        Image cursorImage = cursorView.getImage();
        cursorView.setOpacity(100);
        if((this.determineShipOrientation(curId)) == 1){
            cursorView.setRotate(90);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            cursorImage = cursorView.snapshot(params, null);
        }
        db.setDragView(cursorImage,0,0);
    }

    private void shipMovementEvent (KeyEvent _event) {
        Button shipButton = (Button)_event.getSource();
        String type = shipButton.getId().substring(0,shipButton.getId().length()-1);
        ArrayList temporaryList = (ArrayList)this.allShipHashMap.get(type);
        Node firstElement;
        Node lastElement;
        switch (_event.getText().toUpperCase()) {
            case "D":
                lastElement = (Node) temporaryList.get(temporaryList.size()-1);
                if (this.gridBoundaryCheck(GridPane.getColumnIndex(lastElement)+1)) {
                    temporaryList.forEach(child -> {
                        Node curNode = (Node)child;
                        int curColumnIndex = GridPane.getColumnIndex(curNode);
                        GridPane.setColumnIndex(curNode, curColumnIndex + 1);
                    });
                }
                break;
            case "A":
                firstElement = (Node) temporaryList.get(0);
                if(this.gridBoundaryCheck(GridPane.getColumnIndex(firstElement)-1)) {
                    temporaryList.forEach(child -> {
                        Node curNode = (Node)child;
                        int curColumnIndex = GridPane.getColumnIndex(curNode);
                        GridPane.setColumnIndex(curNode, curColumnIndex - 1);
                    });
                }
                break;
            case "W":
                firstElement = (Node) temporaryList.get(0);
                if (this.gridBoundaryCheck(GridPane.getRowIndex(firstElement)-1)) {
                    temporaryList.forEach(child -> {
                        Node curNode = (Node)child;
                        int curRowIndex = GridPane.getRowIndex(curNode);
                        GridPane.setRowIndex(curNode, curRowIndex - 1);
                    });
                }
                break;
            case "S":
                lastElement = (Node) temporaryList.get(temporaryList.size()-1);
                if(this.gridBoundaryCheck(GridPane.getRowIndex(lastElement)+1)) {
                    temporaryList.forEach(child -> {
                        Node curNode = (Node)child;
                        int curRowIndex = GridPane.getRowIndex(curNode);
                        GridPane.setRowIndex(curNode, curRowIndex + 1);
                    });
                }
                break;
        }
    }

    private void returnMainMenuEvent (Button _button) throws IOException {
        _button.setOnAction(event -> {
            try {
                this.playLogic.returnMainMenu(event);
            } catch (IOException ex) {
                Logger.getLogger(ShipSelectionPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

//*****************     GETTERS     *******************

    public HashMap getAllBattleShips() {
        return this.allShipHashMap;
    }

    public GridPane getPlayerShipPane() {
        return this.playerShipPane;
    }

    private int getShipSize(String _name) {
        return ((ArrayList)this.allShipHashMap.get(_name)).size();
    }

//*****************     SETTERS     *******************

}