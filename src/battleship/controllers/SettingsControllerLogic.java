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
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SettingsControllerLogic {

    public SettingsControllerLogic(FXMLSettingsController _controller) {
        this.settingsController = _controller;
    }

    private final FXMLSettingsController settingsController;
    private LoaderGetter loaderGetter;


    public void initializeMusicSelection() {
        File musicFolder = new File("src/assets/music");
        File[] musicFiles = musicFolder.listFiles();
        Map musicMap = new HashMap();
        ObservableList<String> observableMusic = FXCollections.observableArrayList();
        for(File file : musicFiles ){
            musicMap.put(file.getPath().substring(17), file.getPath());
            observableMusic.add(file.getPath().substring(17));
            }
        this.settingsController.getMusicMap().putAll(musicMap);
        this.settingsController.setPlayList(observableMusic);
    }

//*****************     GETTERS     *******************

    public FXMLSettingsController getSettingsController() {
        return this.settingsController;
    }

    public MappingPane getChildren() {
        MappingPane mainPane = new MappingPane();
        //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeight
        mainPane.mapToPane(new MapPane(this.settingsController.getSettingsGridPane(),"middle","left", 1,1,true,false));
        mainPane.mapToPane(new MapPane(this.settingsController.getMainGridPane(), "bottom","center",1,1,false,false));
        mainPane.mapToPane(new MapPane(this.settingsController.getDisplaySettingsGridPane(), "middle", "right",1,1,true,false));
        return mainPane;
    }

//*****************    SETTERS     *******************

    public void setLoaderGetter (LoaderGetter _loaderGetter) {
        this.loaderGetter = _loaderGetter;
    }

    public void setMediaPlayerState() {
        MediaPlayer testProperty = this.loaderGetter.getMainController().getMediaPlayer();
        if(testProperty.getStatus().equals(MediaPlayer.Status.UNKNOWN) || testProperty.getStatus().equals(MediaPlayer.Status.PAUSED)) {
            this.loaderGetter.getMainController().getMediaPlayer().play();
        }
        else {
            this.loaderGetter.getMainController().getMediaPlayer().pause();
        }
    }

    public void setVolumeLevel(Number _volumeLevel) {
        this.loaderGetter.getMainController().getMediaPlayer().setVolume(_volumeLevel.doubleValue());
    }

    public void setSong(Object _file) {
        FXMLMainController mainController = this.loaderGetter.getMainController();
        mainController.getMediaPlayer().stop();
        String filePath = (String)this.settingsController.getMusicMap().get(_file.toString());
        Media newSong = new Media((new File(filePath)).toURI().toString());
        MediaPlayer newPlayer = new MediaPlayer(newSong);
        newPlayer.setVolume(mainController.getMediaPlayer().getVolume());
        mainController.setMediaPlayer(newPlayer);
        mainController.getMediaPlayer().play();
    }

    public void setBrightnessLevel(Number _brightnessLevel) {
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setBrightness(_brightnessLevel.doubleValue());
        this.loaderGetter.getSettingsRoot().setEffect(colorAdjust);
    }
    public void setContrastLevel(Number _contrastLevel) {
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setContrast(_contrastLevel.doubleValue());
        this.loaderGetter.getSettingsRoot().setEffect(colorAdjust);
    }
    public void setSaturationLevel(Number _saturationLevel) {
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setSaturation(_saturationLevel.doubleValue());
        this.loaderGetter.getSettingsRoot().setEffect(colorAdjust);
    }

    public void setHueLevel(Number _hueLevel) {
        ColorAdjust colorAdjust = this.loaderGetter.getMainController().getColorAdjust();
        colorAdjust.setHue(_hueLevel.doubleValue());
        this.loaderGetter.getSettingsRoot().setEffect(colorAdjust);
    }

    public void returnMainMenu(ActionEvent event) throws IOException {
        this.loaderGetter.getMainController().getLogic().setScene(event);
    }
}
