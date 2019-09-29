/*
 * @author Area 51 Block Party: 
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 */
package battleship.Controllers;
import battleship.LoaderGetter;
import battleship.MapPane;
import battleship.MappingPane;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class FXMLPlayController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @FXML 
            AnchorPane anchorPane;
    @FXML 
            GridPane gridPaneTop;
    @FXML
            GridPane gridPaneBottom;
    private LoaderGetter loaderGetter;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    public void setLoaderGetter(LoaderGetter _loaderGetter){
        this.loaderGetter = _loaderGetter;
    }
    
    @FXML
    private void setButtonState(ActionEvent event){
        Button pressedButton = (Button)event.getSource();
        String blueButtonstyleSheet = "Stylesheets/bluebutton.css";
        String yellowButtonstyleSheet = "Stylesheets/yellowbutton.css";
        String redButtonStylesheet = "Stylesheets/redbutton.css";
        if(pressedButton.getStylesheets().contains(getClass().getResource(blueButtonstyleSheet).toExternalForm())){
            setStyleSheet(pressedButton,blueButtonstyleSheet,yellowButtonstyleSheet);
        }
        else if (pressedButton.getStylesheets().contains(getClass().getResource(yellowButtonstyleSheet).toExternalForm())){
            setStyleSheet(pressedButton,yellowButtonstyleSheet,redButtonStylesheet);
        }
        else{
            setStyleSheet(pressedButton,redButtonStylesheet,blueButtonstyleSheet);
        }
    }
//*****************     GETTERS     *******************
    @FXML
    private void setStyleSheet(Object node, String oldStylesheet, String newStylesheet){
        if(node instanceof Button){
            Button button = (Button)node;
            button.getStylesheets().remove(getClass().getResource(oldStylesheet).toExternalForm());
            button.getStylesheets().add(getClass().getResource(newStylesheet).toExternalForm());
        }
    }
    
    @FXML
    private void setCarrier(ActionEvent event) throws FileNotFoundException{
        Button addingButton = new Button();
        Button addingButton2 = new Button();
        Button addingButton3 = new Button();
        addingButton.setId("Carrier");
        addingButton2.setId("Carrier");
        addingButton3.setId("Battleship");
        addingButton.getStylesheets().add(getClass().getResource("Stylesheets/carrier.css").toExternalForm());
        addingButton2.getStylesheets().add(getClass().getResource("Stylesheets/carrier.css").toExternalForm());
        addingButton3.getStylesheets().add(getClass().getResource("Stylesheets/battleship.css").toExternalForm());
        //column index rowe index, column span, row span
        gridPaneBottom.add(addingButton, 0,1,4,2);
        addingButton2.setRotate(90);
        gridPaneBottom.add(addingButton2,3,3,4,2);
        
        gridPaneBottom.add(addingButton3, 1, 6, 5, 6);
    }
    
//*****************     GETTERS     *******************
    
    public MappingPane getChildren(){
        MappingPane mainPane = new MappingPane();
        //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeight
        mainPane.mapToPane(new MapPane(gridPaneTop, "top", "center", 1, 1, false, false));
        mainPane.mapToPane(new MapPane(gridPaneBottom, "bottom","center", 1, 1, false, false));
        return mainPane;
    } 
    
    @FXML
    private void getButtonState(ActionEvent event){
        Button pressed = (Button)event.getSource();
        System.out.println(GridPane.getColumnIndex(pressed));
        System.out.println(GridPane.getRowIndex(pressed));
    }
}

