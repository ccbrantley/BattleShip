package battleship.Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Last Updated 09/30/2019
 */

import battleship.LoaderGetter;
import battleship.MapPane;
import battleship.MappingPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer.Status;
/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class FXMLSettingsController implements Initializable {
    private LoaderGetter loaderGetter;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.volumeSlider.valueProperty().addListener((observable, oldValue, newValue)->{this.setVolumeLevel(newValue);});
        this.brightnessSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.setBrigthnessLevel(newValue);});
        this.contrastSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.setContrastLevel(newValue);});
        this.saturationSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.setSaturationLevel(newValue);});
        this.hueSlider.valueProperty().addListener((observable,oldValue,newValue)->{this.setHueLevel(newValue);});
        this.musicSelection.valueProperty().addListener((observable,oldValue,newValue)->{this.setSong(newValue);});
        this.initializeMusicSelection();
    }

    @FXML
    public void returnMainMenu(ActionEvent event) throws IOException{
        loaderGetter.getMainController().setScene(event);
    }

    public void initializeMusicSelection(){
        File musicFolder = new File("src/battleship/Controllers/Music");
        File[] musicFiles = musicFolder.listFiles();
        ObservableList<String> observableMusic = FXCollections.observableArrayList();
        for(File file : musicFiles ){
            musicMap.put(file.getPath().substring(33), file.getPath());
            observableMusic.add(file.getPath().substring(33));
        }
        this.musicSelection.setItems(observableMusic);
    }

//*****************     SETTERS     *******************
    @FXML
    public void setMediaPlayerState(){
        MediaPlayer testProperty = this.loaderGetter.getMainController().getMediaPlayer();
        if(testProperty.getStatus().equals(Status.UNKNOWN) || testProperty.getStatus().equals(Status.PAUSED)) {
            this.loaderGetter.getMainController().getMediaPlayer().play();
        }
        else {
            this.loaderGetter.getMainController().getMediaPlayer().pause();
        }
    }
    @FXML
    public void setVolumeLevel(Number _volumeLevel){
        this.loaderGetter.getMainController().getMediaPlayer().setVolume(_volumeLevel.doubleValue());
    }

    @FXML
    public void setSong(Object _file){
        this.loaderGetter.getMainController().getMediaPlayer().stop();
        String filePath = (String)this.musicMap.get(_file.toString());
        Media newSong = new Media((new File(filePath)).toURI().toString());
        MediaPlayer newPlayer = new MediaPlayer(newSong);
        newPlayer.setVolume(this.loaderGetter.getMainController().getMediaPlayer().getVolume());
        this.loaderGetter.getMainController().setMediaPlayer(newPlayer);
        this.loaderGetter.getMainController().getMediaPlayer().play();
    }

    @FXML
    public void setBrigthnessLevel(Number _brightnessLevel){
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setBrightness(_brightnessLevel.doubleValue());
        this.loaderGetter.getSettingsRoot().setEffect(colorAdjust);
    }
    @FXML
    public void setContrastLevel(Number _contrastLevel){
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setContrast(_contrastLevel.doubleValue());
        this.loaderGetter.getSettingsRoot().setEffect(colorAdjust);
    }
    @FXML
    public void setSaturationLevel(Number _saturationLevel){
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setSaturation(_saturationLevel.doubleValue());
        this.loaderGetter.getSettingsRoot().setEffect(colorAdjust);
    }

    @FXML
    public void setHueLevel(Number _hueLevel){
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setHue(_hueLevel.doubleValue());
        this.loaderGetter.getSettingsRoot().setEffect(colorAdjust);

    }

    public void setLoaderGetter(LoaderGetter _loaderGetter){
        this.loaderGetter = _loaderGetter;
    }

//*****************     GETTERS     *******************

    public MappingPane getChildren(){
        MappingPane mainPane = new MappingPane();
        //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeight
        mainPane.mapToPane(new MapPane(soundSettingsGridPane,"middle","left", 1,1,true,false));
        mainPane.mapToPane(new MapPane(mainGridPane, "middle","center",1,1,true,false));
        mainPane.mapToPane(new MapPane(displaySettingsGridPane, "middle", "right",1,1,true,false));
        return mainPane;
    }
}
