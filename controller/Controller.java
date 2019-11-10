package battleship.controller;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 */

import battleship.models.BattleShipFleet;
import battleship.models.BattleShipGame;
import battleship.models.BattleShipShip;
import battleship.tools.EventBus;
import battleship.models.GraphicEffect;
import battleship.models.MusicPlayer;
import battleship.views.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    public Controller (Stage _stage) {
        this.stage = _stage;
    }
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {}
    private Stage stage;
    private static BattleShipGame battleShipGame;
    private static HashMap views = new HashMap();
    private MusicPlayer musicPlayer = new MusicPlayer(.25,true);
    private GraphicEffect graphicsEffect = new GraphicEffect();
    private final EventBus eventBus = BattleShipGame.eventBus;

    //Creates and returns a view based on the _sceneType argument
    private Pane createView (String _sceneType){
            switch (_sceneType) {
                case "play":
                    ShipSelectionView selectionPane = new ShipSelectionView(this);
                    this.views.put("play", selectionPane);
                    this.initializeGame();
                    return selectionPane.getParentPane();
                case "settings":
                    SettingsMenuView settingsPane = new SettingsMenuView(this);
                    this.views.put("settings", settingsPane);
                    return settingsPane.getParentPane();
                case "main":
                    MainMenuView mainePane = new MainMenuView(this);
                    this.views.put("main", mainePane);
                    return mainePane.getParentPane();
                default:
                    return null;
            }
    }

    // Will take a game type and instantiate BattleShipGame with the correct game type
    private void initializeGame () {
        Controller.battleShipGame = new BattleShipGame(BattleShipGame.PVPGAME);
    }

//*****************     EVENTS     *******************

    public static void shipMovementEvent (KeyEvent _event) {
        Node shipButton = (Node)_event.getSource();
        String type = shipButton.getId().substring(0,shipButton.getId().length()-1);
        switch (_event.getText().toUpperCase()) {
            case "D":
                Controller.battleShipGame.getPlayer1().getBattleShipFleet().moveShipIncrementally(0, +1, type);
                break;
            case "A":
                Controller.battleShipGame.getPlayer1().getBattleShipFleet().moveShipIncrementally(0, -1, type);
                break;
            case "W":
                Controller.battleShipGame.getPlayer1().getBattleShipFleet().moveShipIncrementally(-1, 0, type);
                break;
            case "S":
                Controller.battleShipGame.getPlayer1().getBattleShipFleet().moveShipIncrementally(+1, 0, type);
                break;
        }
    }

    public static void shipOnDragDetectedEvent (MouseEvent _event) {
        Node shipButton = (Node)_event.getSource();
        String type = shipButton.getId().substring(0,shipButton.getId().length()-1);
        Dragboard db = shipButton.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(type);
        db.setContent(content);
        _event.consume();
        ImageView cursorView;
        try {
            cursorView = new ImageView(new Image(new FileInputStream("src\\battleship\\assets\\images\\ship\\".concat(type).concat(".png")), 100, 100, true, false));
            Image cursorImage = cursorView.getImage();
            cursorView.setOpacity(100);
            int orientation = BattleShipFleet.getFleetOfShips().get(BattleShipShip.convertShipIdToType(type)).getShipOrientation();
            if(orientation == BattleShipShip.VERTICAL){
                cursorView.setRotate(90);
                SnapshotParameters params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);
                cursorImage = cursorView.snapshot(params, null);
            }
            db.setDragView(cursorImage,0,0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gridOnDragOverEvent (DragEvent _event) {
        Button curButton = (Button)_event.getSource();
        if (_event.getGestureSource() != curButton &&
                _event.getDragboard().hasString()) {
            _event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        _event.consume();
    }

    public static void gridOnDragDroppedEvent (DragEvent _event) {
        Button curButton = (Button)_event.getSource();
        int rowIndex = GridPane.getRowIndex(curButton);
        int columnIndex = GridPane.getColumnIndex(curButton);
        String shipType = _event.getDragboard().getString();
        Controller.battleShipGame.getPlayer1().getBattleShipFleet().moveShip(rowIndex, columnIndex, shipType);
         _event.consume();
    }

    public static void shipOnScrollEvent (ScrollEvent _event) {
        Node shipButton = (Node)_event.getSource();
        String type = shipButton.getId().substring(0,shipButton.getId().length()-1);
        BattleShipFleet.getFleetOfShips().forEach(ship -> {
            if(ship.getShipId().equals(type)) {
                ship.rotateShip();
            }
        });
    }

    // Event to close program
    public void setcloseGuiOnActionEvent (Button _button) {
        _button.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    // Event to change views
    public void setSceneOnActionEvent(Button _button) {
        _button.setOnAction(event -> {
            this.setScene(_button.getId());
        });
    }

    // Event to pause/play music
    public void setMediaPlayerStateOnActionEvent(Button _button) {
        _button.setOnAction(event -> {
            this.musicPlayer.setMediaPlayerState();
        });
    }

    // Event to set listener to volume slider
    public void setMediaPlayerVolumeListener(Slider _slider) {
        _slider.valueProperty().addListener((observable, oldValue, newValue)-> {
            this.musicPlayer.setVolumeLevel(newValue);
        });
    }

    // Event to set listener to music selection
    public void setMediaPlayerSelectionListener(ComboBox _comboBox) {
        _comboBox.valueProperty().addListener((observable,oldValue,newValue)->{
            this.musicPlayer.setSong(newValue);
        });
    }

    // Event to set listener to brigthness slider
    public void setGraphicEffectBrightnessListener(Slider _slider) {
        _slider.valueProperty().addListener((observable,oldValue,newValue)->{
            this.graphicsEffect.setBrightnessLevel(newValue);
        });
    }

    // Event to set listener to contrast slider
    public void setGraphicEffectContrastsListener(Slider _slider) {
        _slider.valueProperty().addListener((observable,oldValue,newValue)->{
            this.graphicsEffect.setContrastLevel(newValue);
        });
    }

    // Event to set listener to saturation slider
    public void setGraphicEffectSaturationListener(Slider _slider) {
        _slider.valueProperty().addListener((observable,oldValue,newValue)->{
            this.graphicsEffect.setSaturationLevel(newValue);
        });
    }

    // Event to set listener to hue slider
    public void setGraphicEffectHueListener(Slider _slider) {
       _slider.valueProperty().addListener((observable,oldValue,newValue)->{
           this.graphicsEffect.setHueLevel(newValue);
       });
    }

    public void setOnMousePressRandomizeShips(Node _node) {
        _node.setOnMousePressed(event -> {
            this.battleShipGame.getPlayer1().randomizeShips();
        });
    }

//*****************     GETTERS     *******************

    public Stage getStage() {
        return stage;
    }

    public HashMap getViews() {
        return views;
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public GraphicEffect getGraphicsEffect() {
        return graphicsEffect;
    }

//*****************     SETTERS     *******************

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setViews(HashMap views) {
        this.views = views;
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    // Loads view based on _sceneType argument
    // If view is not in HashMap, view is created and added to Hashmap
    // Graphics Effect is also applied here
    public void setScene(String _sceneType) {
        Pane parentPane;
        try {
            Object view = this.views.get(_sceneType);
            Method getParentPaneMethod = view.getClass().getMethod("getParentPane");
            parentPane = (Pane)getParentPaneMethod.invoke(view);
        }
        catch(Exception e) {
            parentPane = this.createView(_sceneType);
        }
        if(parentPane != null) {
            if(this.stage.getScene() == null) {
                this.stage.setScene(new Scene(parentPane));
            }
            else {
                this.stage.getScene().setRoot(parentPane);
            }
            parentPane.setEffect(this.graphicsEffect.getColorAdjust());
        }

    }

}
