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
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class PlayControllerLogic {
    public PlayControllerLogic (FXMLPlayController _controller) {
     this.playController = _controller;
    }
    FXMLPlayController playController;
    private LoaderGetter loaderGetter;
    private final ResourceGetter resourceGetter = new ResourceGetter();

    public void returnMainMenu(ActionEvent event) throws IOException {
        this.loaderGetter.getMainController().getLogic().setScene(event);
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
