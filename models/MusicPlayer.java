package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley, Richard Abrams
 * Last Updated: 11/27/2019
 * MusicPlayer serves as the model for a JavaFx Music Player as well
 * as an placeholder for various musical properties.
 */

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

    private MediaPlayer mediaPlayer;
    private ObservableList<String> observableMusic =  FXCollections.observableArrayList();
    private Map musicMap = new HashMap();

    // Enumerators -> File Path.
    public static final String MUSICPATH = "src/battleship/assets/music";
    public static final String INTROSONG = "intro.mp3";
    // Enumerators -> Serialization Type.
    public static final int VOLUME = 7;
    //Enumerators -> state.
    public static boolean AUTOPLAY = true;
    public static boolean PRIME = false;

    public MusicPlayer (double _volumeLevel, boolean _autoPlay) {
        this.initializeMusicSelection();
        this.initializePlayer();
        this.mediaPlayer.setVolume(_volumeLevel);
        if(_autoPlay) {
            this.setStatePlay();
        }
    }

    // Primes the music map with available music at music path.
    private void initializeMusicSelection () {
        File musicFolder = new File(MusicPlayer.MUSICPATH);
        File[] musicFiles = musicFolder.listFiles();
        for(File file : musicFiles ){
            this.musicMap.put(file.getPath().substring(28), file.getPath());
            this.observableMusic.add(file.getPath().substring(28));
            }
    }

    // Primes the music player with the intro song.
    private  void initializePlayer () {
        Media soundFile = new Media(new File(this.musicMap.get(MusicPlayer.INTROSONG).toString()).toURI().toString());
        this.mediaPlayer = new MediaPlayer(soundFile);
    }

//*****************     GETTERS     *******************

    public MediaPlayer getMediaPlayer () {
        return this.mediaPlayer;
    }

    public ObservableList<String> getObservableMusic () {
        return this.observableMusic;
    }

    public Map getMusicMap () {
        return this.musicMap;
    }

//*****************     SETTERS     *******************

    public void setMediaPlayer (MediaPlayer _mediaPlayer) {
        this.mediaPlayer = _mediaPlayer;
    }

    public void setObservableMusic (ObservableList<String> _observableMusic) {
        this.observableMusic = _observableMusic;
    }

    public void setMusicMap (Map _musicMap) {
        this.musicMap = _musicMap;
    }

    public final void setStatePlay () {
        this.mediaPlayer.play();
    }

    public void setStatePause () {
        this.mediaPlayer.pause();
    }

    public void setMediaPlayerState () {
        if(this.mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            this.mediaPlayer.pause();
        }
        else {
            this.mediaPlayer.play();
        }
    }

    public void setVolumeLevel (Number _volumeLevel) {
        this.mediaPlayer.setVolume(_volumeLevel.doubleValue());
    }

    public void setSong (Object _file) {
        this.mediaPlayer.stop();
        String filePath = (String)this.musicMap.get(_file.toString());
        Media newSong = new Media((new File(filePath)).toURI().toString());
        MediaPlayer newPlayer = new MediaPlayer(newSong);
        newPlayer.setVolume(this.mediaPlayer.getVolume());
        this.mediaPlayer = newPlayer;
        this.mediaPlayer.play();
    }

}