package battleship.controllers;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/12/2019
 */

import battleship.models.Animator;
import battleship.models.LoaderGetter;
import battleship.models.MapPane;
import battleship.models.MappingPane;
import battleship.models.ResourceGetter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;

public class PlayControllerLogic {

    public PlayControllerLogic (FXMLPlayController _controller) {
     this.playController = _controller;
    }

    FXMLPlayController playController;
    private LoaderGetter loaderGetter;
    private final ResourceGetter resourceGetter = new ResourceGetter();
    private final Animator animator = new Animator();
    private final int BOARDCOLUMNSIZE = 10;
    private final int BOARDROWSIZE = 10;

    public void initializeController(ArrayList<GridPane> _allShips){
        this.playController.getExplosion().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent _event) {
                try {
                    PlayControllerLogic.this.displayExplosion(_event);
                }catch (FileNotFoundException ex) {
                    Logger.getLogger(PlayControllerLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        _allShips.forEach(child -> {
            child.setOnScroll((_event) -> {
                try {
                    this.rotateGridPaneEvent(_event);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(FXMLPlayController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        child.setOnKeyPressed(this::moveChildOfGridEvent);
        });
    }

    public void returnMainMenu(ActionEvent event) throws IOException {
        this.loaderGetter.getMainController().getLogic().setScene(event);
    }

    public void rotateGridPaneEvent (ScrollEvent _event) throws NoSuchMethodException {
        GridPane rotatePane = (GridPane)_event.getSource();
        int colIndex = GridPane.getColumnIndex(rotatePane);
        int rowIndex = GridPane.getRowIndex(rotatePane);
        int rotateSize = rotatePane.getChildren().size();
        int colSpan = GridPane.getColumnSpan(rotatePane).equals(1) ? rotateSize : 1;
        int rowSpan = (colSpan == 1) ? rotateSize : 1;
        Method getShip;
        String baseMethodString = ("get".concat(rotatePane.getId().substring(0,1).toUpperCase().concat(rotatePane.getId().substring(1,(rotatePane.getId().length()- 1)))));
        if(rotatePane.getId().endsWith("V")) {
            getShip = this.playController.getClass().getMethod(baseMethodString.concat("H"));
        }
        else {
                getShip = this.playController.getClass().getMethod(baseMethodString.concat("V"));
            }
        if(this.gridBoundaryCheck(colIndex + rotateSize) && this.gridBoundaryCheck(rowIndex + rotateSize)){
            try {
                this.playController.getPlayerShipPane().getChildren().remove(rotatePane);
                this.playController.getPlayerShipPane().add((Node)getShip.invoke(this.playController),colIndex,rowIndex,colSpan,rowSpan);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(PlayControllerLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public void displayExplosion (ActionEvent _event) throws FileNotFoundException {
        this.playController.getAnchorPane().getChildren().add(this.animator.getExplosionAnimation());
    }
//*****************     GETTERS     *******************

    public MappingPane getChildren () {
        MappingPane mainPane = new MappingPane();
        //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeight
        mainPane.mapToPane(new MapPane(this.playController.getPlayerLogicPane(), "top", "center", 1, 1, false, false));
        mainPane.mapToPane(new MapPane(this.playController.getPlayerShipPane(), "bottom","center", 1, 1, false, false));
        mainPane.mapToPane(new MapPane(this.playController.getMenuPane(), "bottom", "right", 1, 1, false, false));
        return mainPane;
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

    public static Predicate<Node> isButton(){
        return p -> (p instanceof Button);
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
