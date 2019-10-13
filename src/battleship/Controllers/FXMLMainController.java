package battleship.controllers;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/12/2019
 */

import battleship.models.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;

public class FXMLMainController implements Initializable {

    public FXMLMainController() throws IOException {
        this.mainControllerLogic = new MainControllerLogic(this);
    }

    public MainControllerLogic mainControllerLogic;
    private MediaPlayer mediaPlayer;
    private final ColorAdjust colorAdjust = new ColorAdjust();
    @FXML
    private GridPane mainMenuPane;
    @FXML
    private Button exit;

    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        this.exit.setOnAction(a -> this.mainControllerLogic.closeGUI());
    }

//*****************     SETTERS     *******************
    @FXML
    public void setScene(ActionEvent event) throws IOException{
        this.mainControllerLogic.setScene(event);
    }

    public void setMediaPlayer(MediaPlayer _mediaPlayer) {
        this.mediaPlayer = _mediaPlayer;
    }

//*****************     GETTERS     *******************

    public MappingPane getChildren() {
        return this.mainControllerLogic.getChildren();
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public ColorAdjust getColorAdjust(){
        return this.colorAdjust;
    }

    public MainControllerLogic getLogic() {
        return this.mainControllerLogic;
    }

    public GridPane getMainGridPane () {
        return this.mainMenuPane;
    }
}
