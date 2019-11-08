package battleship.controllers;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/12/2019
 */

import battleship.models.ControllerHandler;
import battleship.models.LoaderGetter;
import battleship.models.MapPane;
import battleship.models.MappingPane;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MainControllerLogic {

    public MainControllerLogic(FXMLMainController _controller) {
        this.mainController = _controller;
        this.startMusic();
    }

    private final FXMLMainController mainController;
    private LoaderGetter loaderGetter;

    private void startMusic() {
        String musicFile = "src/assets/music/The Planets, Op. 32_ Jupiter, the Bringer of Jollity.mp3";
        Media soundFile = new Media(new File(musicFile).toURI().toString());
        this.mainController.setMediaPlayer(new MediaPlayer(soundFile));
        this.mainController.getMediaPlayer().play();
        this.mainController.getMediaPlayer().setVolume(.25);
    }

    public void closeGUI() {
        Platform.exit();
        System.exit(0);
    }

//*****************     GETTERS     *******************
    public FXMLMainController getMainController() {
        return this.mainController;
    }


    public MappingPane getChildren() {
    MappingPane mainPane = new MappingPane();
    //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeigh, fillvertical, fillhorizontal
    mainPane.mapToPane(new MapPane(this.mainController.getMainGridPane(), "middle", "center", 1.5, 1, true, false));
    return mainPane;
}

//*****************     SETTERS     *******************
    public void setLoaderGetter (LoaderGetter _loaderGetter) {
        this.loaderGetter = _loaderGetter;
    }

    public void setScene(ActionEvent _event) throws IOException {
        Button pressed = (Button)_event.getSource();
        String eventId = pressed.getId();
        Parent root = null;
        Stage mainStage = (Stage)pressed.getScene().getWindow();
        ControllerHandler controllerHandler = new ControllerHandler();
        if(null != eventId) switch (eventId) {
            case "play":
                root = this.loaderGetter.getPlayRoot();
                FXMLPlayController playController = this.loaderGetter.getPlayController();
                controllerHandler.manageLayout(mainStage, playController.getLogic());
                break;
            case "resume":
                root = this.loaderGetter.getResumeRoot();
                FXMLResumeController resumeController = this.loaderGetter.getResumeController();
                controllerHandler.manageLayout(mainStage, resumeController.getLogic());
                break;
            case "settings":
                root = this.loaderGetter.getSettingsRoot();
                FXMLSettingsController settingsController = this.loaderGetter.getSettingsController();
                controllerHandler.manageLayout(mainStage, settingsController.getLogic());
                break;
            case "main":
                root = this.loaderGetter.getMainRoot();
                controllerHandler.manageLayout(mainStage, this.loaderGetter.getMainController());
                break;
            default:
                break;
        }
        if(root!= null){
            root.setEffect(this.mainController.getColorAdjust());
        }
        mainStage.getScene().setRoot(root);
    }
}
