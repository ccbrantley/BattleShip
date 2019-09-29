package battleship.Controllers;
/* This class serves to navigate between controllers and to handle
 * initialization of various objects and attributes to be used
 * within all other controllers.
 * @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 09/13/2019
 */
import battleship.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Popup;

public class FXMLMainController implements Initializable {
    private MediaPlayer mediaPlayer;
    private LoaderGetter loaderGetter;
    private ColorAdjust colorAdjust = new ColorAdjust();
    private FXMLGoogleSignInController apple = new FXMLGoogleSignInController();
    @FXML
        GridPane mainMenuPane;
    @FXML
        GridPane signInPane ;
    @FXML
        Button signInButton;
    
    public void setLoaderGetter(LoaderGetter _loaderGetter){
        this.loaderGetter = _loaderGetter;
    }
    
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        startMusic();
    }
    
    /**
     * 
     * @param event: 
     * @throws IOException 
     */
    @FXML
    private void updateScene(ActionEvent event) throws IOException {
        Button pressed = (Button)event.getSource();
        String eventId = pressed.getId();
        Parent root = null;
        Stage mainStage = (Stage)pressed.getScene().getWindow();
        ControllerHandler controllerHandler = new ControllerHandler();
        if("play".equals(eventId)) {
            root = this.loaderGetter.getPlayRoot();
            FXMLPlayController playController = this.loaderGetter.getPlayController();
            playController.setLoaderGetter(this.loaderGetter);
            controllerHandler.manageLayout(mainStage, playController);      
        }
        else if("resume".equals(eventId)) {
            root = this.loaderGetter.getResumeRoot();
            FXMLResumeController resumeController = this.loaderGetter.getResumeController();
            resumeController.setLoaderGetter(this.loaderGetter);
        }
        else if("settings".equals(eventId)) {
            root = this.loaderGetter.getSettingsRoot();
            FXMLSettingsController settingsController = this.loaderGetter.getSettingsController();
            settingsController.setLoaderGetter(this.loaderGetter);
            controllerHandler.manageLayout(mainStage, settingsController);
        }
        else if("main".equals(eventId)) {
            root = this.loaderGetter.getMainRoot();
            FXMLMainController mainController = this.loaderGetter.getMainController();
            mainController.setLoaderGetter(this.loaderGetter);
            controllerHandler.manageLayout(mainStage, mainController);
        }
        root.setEffect(this.colorAdjust);
        mainStage.getScene().setRoot(root);
    }
    
     @FXML
    private void logIn(ActionEvent event) throws FileNotFoundException{
        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setScene(new Scene(this.apple.getScene(),500,500));
        popUp.showAndWait();
        
    }
    @FXML
    private void getInfo(ActionEvent event){
        this.apple.getInfo();
    }
    
    
    
    /**Initializes the MediaPlayer with music.
     * 
     */
    public void startMusic() {
        String musicFile = "src/battleship/Controllers/Music/The Planets, Op. 32_ Jupiter, the Bringer of Jollity.mp3";
        Media soundFile = new Media(new File(musicFile).toURI().toString());
        this.mediaPlayer = new MediaPlayer(soundFile);
        this.mediaPlayer.play();
        this.mediaPlayer.setVolume(1.0);
    }
    
    /**Exits the program.
     */
    @FXML
    private void closeGUI() {
        Platform.exit();
        System.exit(0);
    }

//*****************     SETTERS     *******************
    
    public void setScene(ActionEvent event) throws IOException {
        this.updateScene(event);
    }
    
    public void setMediaPlayer(MediaPlayer _mediaPlayer){
        this.mediaPlayer = _mediaPlayer;
    }
 
//*****************     GETTERS     *******************

    public MappingPane getChildren(){
        MappingPane mainPane = new MappingPane();
        //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeigh, fillvertical, fillhorizontal
        mainPane.mapToPane(new MapPane(this.mainMenuPane, "middle", "center", 1.5, 1, true, false));
        mainPane.mapToPane(new MapPane(this.signInPane, "bottom","left", .1, .1, false, false));
        return mainPane;
    }
    
    /**
     * @return the main MediaPlayer
     */
    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }
    
    public ColorAdjust getColorAdjust(){
        return this.colorAdjust;
    }
    
}
