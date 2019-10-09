package battleship.controllers;

/*
 * @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated 09/30/2019
 */
import battleship.models.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class FXMLPlayController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
            private AnchorPane anchorPane;
    @FXML
            private GridPane gridPaneTop;
    @FXML
            private GridPane gridPaneBottom;
    private LoaderGetter loaderGetter;
    private ResourceGetter resourceGetter = new ResourceGetter();

    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
    }

//*****************     SETTERS     *******************

    public void setLoaderGetter(LoaderGetter _loaderGetter) {
        this.loaderGetter = _loaderGetter;
    }

    @FXML
    private void setButtonState(ActionEvent _event) {
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

    @FXML
    private void setStyleSheet(Object _node, String _oldStylesheet, String _newStylesheet) {
        if(_node instanceof Button){
            Button button = (Button)_node;
            button.getStylesheets().remove(_oldStylesheet);
            button.getStylesheets().add(_newStylesheet);
        }
    }

    @FXML
    private void setCarrier(ActionEvent _event) throws FileNotFoundException {
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
        
        gridPaneBottom.add(addingButton, 0,1,4,2);
        addingButton2.setRotate(90);
        gridPaneBottom.add(addingButton2,3,3,4,2);
        gridPaneBottom.add(addingButton3, 1, 6, 5, 6);
    }

//*****************     GETTERS     *******************

    public MappingPane getChildren() {
        MappingPane mainPane = new MappingPane();
        //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeight
        mainPane.mapToPane(new MapPane(gridPaneTop, "top", "center", 1, 1, false, false));
        mainPane.mapToPane(new MapPane(gridPaneBottom, "bottom","center", 1, 1, false, false));
        return mainPane;
    }

    @FXML
    private void getButtonState(ActionEvent _event) {
        Button pressed = (Button)_event.getSource();
        System.out.println(GridPane.getColumnIndex(pressed));
        System.out.println(GridPane.getRowIndex(pressed));
    }
}

