package battleship.controllers;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/15/2019
 */

import battleship.models.Animator;
import battleship.models.LoaderGetter;
import battleship.models.MapPane;
import battleship.models.MappingPane;
import battleship.models.ResourceGetter;
import battleship.views.ShipSelectionPane;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PlayControllerLogic {

    public PlayControllerLogic (FXMLPlayController _controller) throws FileNotFoundException {
     this.playController = _controller;
    }

    public void initializeController(){
        this.playController.getMainButton().setOnAction(event -> {try {
            this.returnMainMenu(event);
            } catch (IOException ex) {
                Logger.getLogger(PlayControllerLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.playController.getSinglePlayerButton().setOnAction(event -> {this.startShipSelectionPaneSequence(event);});
    }

    FXMLPlayController playController;
    private LoaderGetter loaderGetter;
    private final ResourceGetter resourceGetter = new ResourceGetter();
    private final int BOARDCOLUMNSIZE = 10;
    private final int BOARDROWSIZE = 10;
    private final Animator explosionAnimation = new Animator("EXPLOSION");

    public void returnMainMenu(ActionEvent _event) throws IOException {
        this.loaderGetter.getMainController().getLogic().setScene(_event);
    }

    public void startShipSelectionPaneSequence(ActionEvent _event) {
        double screenWidth = this.loaderGetter.getScreenWidth();
        double screenHeight = this.loaderGetter.getScreenHeight();
        AnchorPane mainPane = this.playController.getMainPane();
        ShipSelectionPane shipSelectionPane = new ShipSelectionPane(this,screenWidth, screenHeight, mainPane);
        shipSelectionPane.showPane();
        shipSelectionPane.shipPopulate(shipSelectionPane.CARRIER, shipSelectionPane.HORIZONTAL, 0, 0);
        shipSelectionPane.shipPopulate(shipSelectionPane.BATTLESHIP, shipSelectionPane.VERTICAL, 4, 5);
        shipSelectionPane.shipPopulate(shipSelectionPane.CRUISER,shipSelectionPane.HORIZONTAL,3,4);
        shipSelectionPane.shipPopulate(shipSelectionPane.SUBMARINE,shipSelectionPane.VERTICAL,7,5);
        shipSelectionPane.shipPopulate(shipSelectionPane.DESTROYER,shipSelectionPane.HORIZONTAL,2,5);
    }

// Should allow this to take in x, y coordinates or use getSectorFromAlpha to get a button then receive it's position
    public void displayExplosion (ActionEvent _event) throws FileNotFoundException {
        ImageView explosionView = this.explosionAnimation.getImageView();
        this.explosionAnimation.setImageViewScale(.2,.2);
        this.explosionAnimation.setImageViewLayout(600, 610);
        this.playController.getMainPane().getChildren().add(explosionView);
        this.explosionAnimation.playAnimation();
        this.explosionAnimation.getTimeline().setOnFinished(event -> {this.playController.getMainPane().getChildren().remove(explosionView);});
    }

   public static Predicate<Node> isButton(){
        return p -> (p instanceof Button);
    }

//*****************     GETTERS     *******************

    public MappingPane getChildren () {
        MappingPane mainPane = new MappingPane();
        //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeight
        mainPane.mapToPane(new MapPane(this.playController.getMenuPane(), "middle", "center", 1, 1, true, false));
        return mainPane;
    }

    public FXMLPlayController getPlayController() {
        return this.playController;
    }

    public LoaderGetter getLoaderGetter() {
        return this.loaderGetter;
    }

//*****************     SETTERS     *******************
// Probably just update id's and have the css file manually update the style
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
