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

import battleship.models.LoaderGetter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SettingsControllerLogic {
    private static SettingsControllerLogic object;
    private FXMLSettingsController settingsController;
    private FXMLLoader settingsLoader;
    private Parent settingsParent;
    private LoaderGetter loaderGetter;

    public SettingsControllerLogic(FXMLSettingsController _controller) {
        this.settingsController = _controller;
    }
    public void initializeMusicSelection() {
        File musicFolder = new File("src/assets/music");
        File[] musicFiles = musicFolder.listFiles();
        Map musicMap = new HashMap();
        ObservableList<String> observableMusic = FXCollections.observableArrayList();
        for(File file : musicFiles ){
            musicMap.put(file.getPath().substring(33), file.getPath());
            observableMusic.add(file.getPath().substring(33));
            }
        this.settingsController.getMusicMap().putAll(musicMap);
        this.settingsController.setMusicSelection(observableMusic);
    }

//*****************     GETTERS     *******************

    public FXMLSettingsController getSettingsController() {
        return this.settingsController;
    }

    public FXMLLoader getSettingsLoader() {
        return this.settingsLoader;
    }

    public Parent getSettingsParent() {
        return this.settingsParent;
    }

//*****************    SETTERS     *******************

    public void setLoaderGetter (LoaderGetter _loaderGetter) {
        this.loaderGetter = _loaderGetter;
    }

    public void setMediaPlayerState(){
        MediaPlayer testProperty = this.loaderGetter.getMainController().getMediaPlayer();
        if(testProperty.getStatus().equals(MediaPlayer.Status.UNKNOWN) || testProperty.getStatus().equals(MediaPlayer.Status.PAUSED)) {
            this.loaderGetter.getMainController().getMediaPlayer().play();
        }
        else {
            this.loaderGetter.getMainController().getMediaPlayer().pause();
        }
    }

    public void setVolumeLevel(Number _volumeLevel){
        this.loaderGetter.getMainController().getMediaPlayer().setVolume(_volumeLevel.doubleValue());
    }

    public void setSong(Object _file){
        FXMLMainController mainController = this.loaderGetter.getMainController();
        mainController.getMediaPlayer().stop();
        String filePath = (String)this.settingsController.getMusicMap().get(_file.toString());
        Media newSong = new Media((new File(filePath)).toURI().toString());
        MediaPlayer newPlayer = new MediaPlayer(newSong);
        newPlayer.setVolume(mainController.getMediaPlayer().getVolume());
        mainController.setMediaPlayer(newPlayer);
        mainController.getMediaPlayer().play();
    }

    public void setBrightnessLevel(Number _brightnessLevel){
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setBrightness(_brightnessLevel.doubleValue());
        this.settingsParent.setEffect(colorAdjust);
    }
    public void setContrastLevel(Number _contrastLevel){
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setContrast(_contrastLevel.doubleValue());
        this.settingsParent.setEffect(colorAdjust);
    }
    public void setSaturationLevel(Number _saturationLevel){
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setSaturation(_saturationLevel.doubleValue());
        this.settingsParent.setEffect(colorAdjust);
    }

    public void setHueLevel(Number _hueLevel){
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setHue(_hueLevel.doubleValue());
        this.settingsParent.setEffect(colorAdjust);

    }

    public void returnMainMenu(ActionEvent event) throws IOException{
        this.loaderGetter.getMainController().getLogic().setScene(event);
    }
}
