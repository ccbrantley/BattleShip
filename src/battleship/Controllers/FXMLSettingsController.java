package battleship.controllers;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/12/2019
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public class FXMLSettingsController implements Initializable {

    public FXMLSettingsController() throws MalformedURLException {
        this.settingsControllerLogic = new SettingsControllerLogic(this);
    }

    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        this.pausePlay.setOnAction(a -> this.settingsControllerLogic.setMediaPlayerState());
        this.volumeSlider.valueProperty().addListener((observable, oldValue, newValue)->{this.settingsControllerLogic.setVolumeLevel(newValue);});
        this.brightnessSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.settingsControllerLogic.setBrightnessLevel(newValue);});
        this.contrastSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.settingsControllerLogic.setContrastLevel(newValue);});
        this.saturationSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.settingsControllerLogic.setSaturationLevel(newValue);});
        this.hueSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.settingsControllerLogic.setHueLevel(newValue);});
        this.musicSelection.valueProperty().addListener((observable,oldValue,newValue)->{this.settingsControllerLogic.setSong(newValue);});
        this.settingsControllerLogic.initializeMusicSelection();
    }

    private SettingsControllerLogic settingsControllerLogic;
    private Map musicMap = new HashMap();
    @FXML
        private GridPane soundSettingsGridPane;
    @FXML
        private GridPane mainGridPane;
    @FXML
        private GridPane displaySettingsGridPane;
    @FXML
        private Slider volumeSlider;
    @FXML
        private Slider brightnessSlider;
    @FXML
        private Slider contrastSlider;
    @FXML
        private Slider saturationSlider;
    @FXML
        private Slider hueSlider;
    @FXML
        private ComboBox musicSelection;
    @FXML
        private Button pausePlay;

    @FXML
    public void returnMainMenu(ActionEvent _event) throws IOException {
        this.settingsControllerLogic.returnMainMenu(_event);
    }

//*****************     SETTERS     *******************

    public void setMusicMap(Map _musicMap){
        this.musicMap = _musicMap;
    }

    public void setPlayList (ObservableList<String> _observableMusic) {
        this.musicSelection.setItems(_observableMusic);
    }

    public void setControllerLogic(SettingsControllerLogic _settingsControllerLogic) {
                this.settingsControllerLogic = _settingsControllerLogic;
    }

//*****************     GETTERS     *******************

    public ComboBox getMusicSelection(){
        return this.musicSelection;
    }

    public Map getMusicMap() {
        return this.musicMap;
    }

    public GridPane getSettingsGridPane() {
        return this.soundSettingsGridPane;
    }

    public GridPane getMainGridPane() {
        return this.mainGridPane;
    }

    public GridPane getDisplaySettingsGridPane() {
        return this.displaySettingsGridPane;
    }

    public SettingsControllerLogic getLogic () {
        return this.settingsControllerLogic;
    }
}
