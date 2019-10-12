package battleship.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Last Updated 09/30/2019
 */

import battleship.models.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class FXMLSettingsController implements Initializable {
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

    public FXMLSettingsController() throws MalformedURLException {
        this.settingsControllerLogic = new SettingsControllerLogic(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.volumeSlider.valueProperty().addListener((observable, oldValue, newValue)->{this.settingsControllerLogic.setVolumeLevel(newValue);});
        this.brightnessSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.settingsControllerLogic.setBrightnessLevel(newValue);});
        this.contrastSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.settingsControllerLogic.setContrastLevel(newValue);});
        this.saturationSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.settingsControllerLogic.setSaturationLevel(newValue);});
        this.hueSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.settingsControllerLogic.setHueLevel(newValue);});
        this.musicSelection.valueProperty().addListener((observable,oldValue,newValue)->{this.settingsControllerLogic.setSong(newValue);});
        this.settingsControllerLogic.initializeMusicSelection();
    }

    @FXML
    public void returnMainMenu(ActionEvent event) throws IOException{
        this.settingsControllerLogic.returnMainMenu(event);
    }

//*****************     SETTERS     *******************
    public void setMediaPlayerState(){
        this.settingsControllerLogic.setMediaPlayerState();
    }
    public void setControllerLogic(SettingsControllerLogic _settingsControllerLogic) {
                this.settingsControllerLogic = _settingsControllerLogic;
    }

    public void setMusicMap(Map _musicMap){
        this.musicMap = _musicMap;
    }

    public void setMusicSelection (ObservableList<String> _observableMusic) {
        this.musicSelection.setItems(_observableMusic);
    }

//*****************     GETTERS     *******************

    public ComboBox getMusicSelection(){
        return this.musicSelection;
    }

    public Map getMusicMap() {
        return this.musicMap;
    }

    public MappingPane getChildren(){
        MappingPane mainPane = new MappingPane();
        //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeight
        mainPane.mapToPane(new MapPane(soundSettingsGridPane,"middle","left", 1,1,true,false));
        mainPane.mapToPane(new MapPane(mainGridPane, "middle","center",1,1,true,false));
        mainPane.mapToPane(new MapPane(displaySettingsGridPane, "middle", "right",1,1,true,false));
        return mainPane;
    }
    public SettingsControllerLogic getLogic () {
        return this.settingsControllerLogic;
    }
}
