package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/28/2019
 */

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

    public MusicPlayer (double _volumeLevel, boolean _autoPlay) {
        this.initializeMusicSelection();
        this.initializePlayer();
        this.mediaPlayer.setVolume(_volumeLevel);
        if(_autoPlay) {
            this.setStatePlay();
        }
    }

    private MediaPlayer mediaPlayer;
    private ObservableList<String> observableMusic =  FXCollections.observableArrayList();
    private Map musicMap = new HashMap();
    
    public final int VOLUME = 7;

    private void initializeMusicSelection() {
        File musicFolder = new File("src/battleship/assets/music");
        File[] musicFiles = musicFolder.listFiles();
        for(File file : musicFiles ){
            this.musicMap.put(file.getPath().substring(28), file.getPath());
            this.observableMusic.add(file.getPath().substring(28));
            }
    }

    private  void initializePlayer() {
        Media soundFile = new Media(new File(this.musicMap.get("intro.mp3").toString()).toURI().toString());
        this.mediaPlayer = new MediaPlayer(soundFile);
    }

//*****************     GETTERS     *******************

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public ObservableList<String> getObservableMusic() {
        return observableMusic;
    }

    public Map getMusicMap() {
        return musicMap;
    }

//*****************     SETTERS     *******************

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void setObservableMusic(ObservableList<String> observableMusic) {
        this.observableMusic = observableMusic;
    }

    public void setMusicMap(Map musicMap) {
        this.musicMap = musicMap;
    }

    public final void setStatePlay() {
        this.mediaPlayer.play();
    }

    public void setStatePause() {
        this.mediaPlayer.pause();
    }

    public void setMediaPlayerState() {
        if(this.mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            this.mediaPlayer.pause();
        }
        else {
            this.mediaPlayer.play();
        }
    }

    public void setVolumeLevel(Number _volumeLevel) {
        this.mediaPlayer.setVolume(_volumeLevel.doubleValue());
    }

    public void setSong(Object _file) {
        this.mediaPlayer.stop();
        String filePath = (String)this.musicMap.get(_file.toString());
        Media newSong = new Media((new File(filePath)).toURI().toString());
        MediaPlayer newPlayer = new MediaPlayer(newSong);
        newPlayer.setVolume(this.mediaPlayer.getVolume());
        this.mediaPlayer = newPlayer;
        this.mediaPlayer.play();
    }
}
