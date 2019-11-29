package battleship.controller;

/* @author Area 51 Block Party:
 * Christopher Brantley, Richard Abrams
 * Last Updated: 11/27/2019
 */

import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.models.BattleShipShip;
import battleship.models.Coordinate;
import battleship.tools.EventBus;
import battleship.models.GraphicEffect;
import battleship.models.MusicPlayer;
import battleship.tools.SerializerAdapter;
import battleship.tools.ViewAssets;
import battleship.tools.events.*;
import battleship.views.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller implements Initializable {

    private Stage stage;
    private BattleShipGame battleShipGame;
    private final SerializerAdapter serializerAdapter = new SerializerAdapter();
    private MusicPlayer musicPlayer = new MusicPlayer(.25,true);
    private final GraphicEffect graphicsEffect = new GraphicEffect();
    private final EventBus eventBus = BattleShipGame.eventBus;

    public Controller (Stage _stage) {
        this.stage = _stage;
        loadSettings();
    }

    @Override
    public void initialize (URL _url, ResourceBundle _rb) {}

    //Creates and returns a view based on the _sceneType argument.
    private Pane createView (String _sceneType){
        Pane parentPane;
            switch (_sceneType) {
                case ViewAssets.GAME:
                    BattleShipGameView gamePane = new BattleShipGameView(this);
                    this.battleShipGame.getPlayer1().getBattleShipFleet().throwAllPositionUpdateEvents();
                    parentPane = gamePane.getParentPane();
                    break;
                case ViewAssets.PLAY:
                    ShipSelectionView selectionPane = new ShipSelectionView(this);
                    this.initializeGame();
                    parentPane = selectionPane.getParentPane();
                    break;
                case ViewAssets.SETTINGS:
                    SettingsMenuView settingsPane = new SettingsMenuView(this);
                    parentPane = settingsPane.getParentPane();
                    break;
                default:
                    MainMenuView mainePane = new MainMenuView(this);
                    parentPane = mainePane.getParentPane();
            }
            return parentPane;
    }

    // Will take a game type and instantiate BattleShipGame with the game type.
    private void initializeGame () {
        this.battleShipGame = new BattleShipGame(BattleShipGame.PVBGAME);
    }

    //loads all the values from the settings file.
    private void loadSettings () {
        this.setSettings(this.serializerAdapter.extractData(GraphicEffect.CONTRAST), GraphicEffect.CONTRAST);
        this.setSettings(this.serializerAdapter.extractData(GraphicEffect.BRIGHTNESS), GraphicEffect.BRIGHTNESS);
        this.setSettings(this.serializerAdapter.extractData(GraphicEffect.HUE), GraphicEffect.HUE);
        this.setSettings(this.serializerAdapter.extractData(GraphicEffect.SATURATION), GraphicEffect.SATURATION);
        this.setSettings(this.serializerAdapter.extractData(MusicPlayer.VOLUME), MusicPlayer.VOLUME);
    }

    private void setSettings(String _data, int _loadType){
        if(_data.equals(" ")){
            return;
        }
        double data = Double.parseDouble(_data);
        switch (_loadType) {
            case GraphicEffect.CONTRAST:
                this.graphicsEffect.setContrastLevel(data);
            case GraphicEffect.BRIGHTNESS:
                this.graphicsEffect.setBrightnessLevel(data);
            case GraphicEffect.HUE:
                this.graphicsEffect.setHueLevel(data);
            case GraphicEffect.SATURATION:
                this.graphicsEffect.setSaturationLevel(data);
            case MusicPlayer.VOLUME:
                this.musicPlayer.setVolumeLevel(data);
        }
    }

    //saves saves various settings to the settings file.
    private void saveSettings () {
        ArrayList<Object> data = new ArrayList<>(
                Arrays.asList(
                        GraphicEffect.getScreenWidth(),
                        GraphicEffect.getScreenHeight(),
                        this.graphicsEffect.getColorAdjust().getContrast(),
                        this.graphicsEffect.getColorAdjust().getBrightness(),
                        this.graphicsEffect.getColorAdjust().getHue(),
                        this.graphicsEffect.getColorAdjust().getSaturation(),
                        this.musicPlayer.getMediaPlayer().getVolume()
                ));
        this.serializerAdapter.save(data);
    }

//*****************     EVENTS     *******************

    // Throws event for firing at a ship.
    public void fireEvent (Button _button) {
        _button.setOnAction(event ->{
            BattleShipGame.getEventBus().throwEvent(new FireAwayEvent(BattleShipPlayer.AWAY));
        });
    }

    // Event for switching led button colors.
    public void ledButtonSetOnAction (Button _button) {
        _button.setOnAction(event -> {
            Button curButton = ((Button)event.getSource());
            String buttonId = curButton.getId();
            String newId = ViewAssets.NULLLED;
            switch (buttonId) {
                case ViewAssets.BLUELED:
                    BattleShipGame.getEventBus().throwEvent(new RedActiveLedEvent());
                    int row = GridPane.getRowIndex(_button);
                    int column = GridPane.getColumnIndex(_button);
                    BattleShipGame.getEventBus().throwEvent(new SetTargetEvent(new Coordinate(row, column)));
                    newId = newId.concat(ViewAssets.REDLEDACTIVE);
                    break;
                case ViewAssets.REDLEDACTIVE:
                    newId = newId.concat(ViewAssets.YELLOWLED);
                    break;
                case ViewAssets.YELLOWLED:
                    newId = newId.concat(ViewAssets.REDLED);
                    break;
                case ViewAssets.REDLED:
                    newId = newId.concat(ViewAssets.BLUELED);
                    break;
            }
            curButton.setId(newId);
        });
    }

    // Event for w,a,s,d key presses to move ship.
    public void shipMovementEvent (Node _node) {
        _node.setOnKeyPressed(event -> {
            switch (event.getText().toUpperCase()) {
                case BattleShipGame.RIGHT:
                    BattleShipGame.getEventBus().throwEvent(new MoveShipIncrementallyEvent(0, +1));
                    break;
                case BattleShipGame.LEFT:
                    BattleShipGame.getEventBus().throwEvent(new MoveShipIncrementallyEvent(0, -1));
                    break;
                case BattleShipGame.UP:
                    BattleShipGame.getEventBus().throwEvent(new MoveShipIncrementallyEvent(-1, 0));
                    break;
                case BattleShipGame.DOWN:
                    BattleShipGame.getEventBus().throwEvent(new MoveShipIncrementallyEvent(+1, 0));
                    break;
            }
        });
    }

    public void setShipSelectionEvent (Node _node) {
        _node.setOnMousePressed(event -> {
            Node shipButton = (Node)event.getSource();
            String shipId = shipButton.getId();
            int shipType = BattleShipShip.convertShipIdToType(shipId.substring(0, shipId.length()-1));
            this.battleShipGame.getPlayer1().setSelectedShip(shipType);
        });
    }

    // Event upon dragging ship.
    public void shipOnDragDetectedEvent (Node _node) {
        _node.setOnDragDetected(event -> {
            Node shipButton = (Node)event.getSource();
            String type = shipButton.getId().substring(0,shipButton.getId().length()-1);
            Dragboard db = shipButton.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(type);
            db.setContent(content);
            event.consume();
            ImageView cursorView;
            try {
                cursorView = new ImageView(new Image(new FileInputStream(ViewAssets.SHIPIMAGES.concat(type).concat(ViewAssets.SHIPIMAGEEXTENSION)), 100, 100, true, false));
                Image cursorImage = cursorView.getImage();
                cursorView.setOpacity(100);
                int orientation = this.battleShipGame.getPlayer1().getBattleShipFleet().getFleetOfShips().get(BattleShipShip.convertShipIdToType(type)).getShipOrientation();
                if(orientation == BattleShipShip.VERTICAL){
                    cursorView.setRotate(90);
                    SnapshotParameters params = new SnapshotParameters();
                    params.setFill(Color.TRANSPARENT);
                    cursorImage = cursorView.snapshot(params, null);
                }
                db.setDragView(cursorImage,0,0);
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Event upon dragging ship over a grid.
    public void gridOnDragOverEvent (Node _node) {
        _node.setOnDragOver(event -> {
            Button curButton = (Button)event.getSource();
            if (event.getGestureSource() != curButton && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            //event.consume();
        });
    }

    // Event upon dragging and then dropping ship.
    public void gridOnDragDroppedEvent (Node _node) {
        _node.setOnDragDropped(event -> {
            Button curButton = (Button)event.getSource();
            int rowIndex = GridPane.getRowIndex(curButton);
            int columnIndex = GridPane.getColumnIndex(curButton);
            String shipType = event.getDragboard().getString();
            BattleShipGame.getEventBus().throwEvent(new MoveShipEvent(rowIndex, columnIndex, shipType));
             event.consume();
         });
    }

    // Event to rotate the ship.
    public void shipOnScrollEvent (Node _node) {
        _node.setOnScroll(event -> {
            Node shipButton = (Node)event.getSource();
            String shipId = shipButton.getId().substring(0,shipButton.getId().length()-1);
            int shipType = BattleShipShip.convertShipIdToType(shipId);
            BattleShipGame.getEventBus().throwEvent(new RotateShipEvent(shipType));
        });
    }

    // Event to close program and save last values of certain settings.
    public void setcloseGuiOnActionEvent (Button _button) {
        _button.setOnAction(event -> {
            saveSettings();
            Platform.exit();
            System.exit(0);
        });
    }

    // Event to change views.
    public void setSceneOnActionEvent (Button _button) {
        _button.setOnAction(event -> {
            this.setScene(_button.getId());
        });
    }

    // Event to pause/play music.
    public void setMediaPlayerStateOnActionEvent (Button _button) {
        _button.setOnAction(event -> {
            this.musicPlayer.setMediaPlayerState();
        });
    }

    // Event to set listener to volume slider.
    public void setMediaPlayerVolumeListener (Slider _slider) {
        _slider.valueProperty().addListener((observable, oldValue, newValue)-> {
            this.musicPlayer.setVolumeLevel(newValue);
        });
    }

    // Event to set listener to music selection.
    public void setMediaPlayerSelectionListener (ComboBox _comboBox) {
        _comboBox.valueProperty().addListener((observable,oldValue,newValue)->{
            this.musicPlayer.setSong(newValue);
        });
    }

    // Event to set listener to brigthness slider.
    public void setGraphicEffectBrightnessListener (Slider _slider) {
        _slider.valueProperty().addListener((observable,oldValue,newValue)->{
            this.graphicsEffect.setBrightnessLevel(newValue);
        });
    }

    // Event to set listener to contrast slider.
    public void setGraphicEffectContrastsListener (Slider _slider) {
        _slider.valueProperty().addListener((observable,oldValue,newValue)->{
            this.graphicsEffect.setContrastLevel(newValue);
        });
    }

    // Event to set listener to saturation slider.
    public void setGraphicEffectSaturationListener (Slider _slider) {
        _slider.valueProperty().addListener((observable,oldValue,newValue)->{
            this.graphicsEffect.setSaturationLevel(newValue);
        });
    }

    // Event to set listener to hue slider.
    public void setGraphicEffectHueListener (Slider _slider) {
       _slider.valueProperty().addListener((observable,oldValue,newValue)->{
           this.graphicsEffect.setHueLevel(newValue);
       });
    }

    // Throws event to randomize ship locations.
    public void setOnMousePressRandomizeShips (Node _node) {
        _node.setOnMousePressed(event -> {
            BattleShipGame.getEventBus().throwEvent(new RandomizeShipsEvent());
        });
    }

    // Used to remove current game.
    public void setSceneAndRemoveGame (Node _node) {
        _node.setOnMousePressed(event ->{
            BattleShipGame.eventBus.resetListeners();
            this.setSceneOnActionEvent((Button)_node);
        });
    }

//*****************     GETTERS     *******************

    public Stage getStage () {
        return stage;
    }

    public MusicPlayer getMusicPlayer () {
        return musicPlayer;
    }

    public GraphicEffect getGraphicsEffect () {
        return graphicsEffect;
    }

//*****************     SETTERS     *******************

    public void setStage (Stage stage) {
        this.stage = stage;
    }

    public void setMusicPlayer (MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    // Loads view based on _sceneType argument.
    // If view is not in HashMap, view is created and added to Hashmap.
    // Graphics Effect is also applied here.
    public void setScene (String _sceneType) {
        Pane parentPane = this.createView(_sceneType);
        this.stage.getScene().setRoot(parentPane);
        parentPane.setEffect(this.graphicsEffect.getColorAdjust());
    }

}
