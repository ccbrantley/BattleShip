package battleship.controllers;

/* This class serves to navigate between controllers and to handle
 * initialization of various objects and attributes to be used
 * within all other controllers.
 * @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 09/30/2019
 */

import battleship.models.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;

public class FXMLMainController implements Initializable {
    public MainControllerLogic mainControllerLogic;

    private MediaPlayer mediaPlayer;
    private ColorAdjust colorAdjust = new ColorAdjust();
    @FXML
    private GridPane mainMenuPane;

    public FXMLMainController() throws IOException {
        this.mainControllerLogic = new MainControllerLogic(this);
    }

    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
    }

    @FXML
    private void closeGUI() {
        this.mainControllerLogic.closeGUI();
    }

//*****************     SETTERS     *******************

    public void setMediaPlayer(MediaPlayer _mediaPlayer) {
        this.mediaPlayer = _mediaPlayer;
    }

    @FXML
    public void setScene(ActionEvent event) throws IOException{
        this.mainControllerLogic.setScene(event);
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

    public GridPane getMainMenuPane () {
        return this.mainMenuPane;
    }
}
