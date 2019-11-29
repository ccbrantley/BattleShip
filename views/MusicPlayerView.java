package battleship.views;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/26/2019
 * MusicPlayerView is the definition of the visual for the music player modifier view.
 */

import battleship.controller.Controller;
import battleship.models.GraphicEffect;
import battleship.tools.ViewAssets;
import battleship.tools.ResourceGetter;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MusicPlayerView {

    private Controller controller;
    private final double screenWidth = GraphicEffect.getScreenWidth();
    private final double screenHeight = GraphicEffect.getScreenHeight();
    private double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private double buttonWidthRatio = 1/3;
    private double buttonHeightRatio = .15;
    private AnchorPane parentPane;
    private VBox soundMenuVBox;
    private ArrayList<Node> soundMenuArray = new ArrayList<Node>();
    private Label soundSettingsLabel;
    private Button soundOffOnButton;
    private Label volumeLevelLabel;
    private Slider volumeLevelSlider;
    private Label musicSelectionlabel;
    private ComboBox musicSelectionBox;

    public MusicPlayerView (Controller _controller) {

        // Adding controller for access to events
        this.controller = _controller;
        // Creating pane and children of the pane
        this.parentPane = ViewAssets.createAnchorPane("musicPlayerPane", ResourceGetter.getMusicPlayerCSS());
        this.soundSettingsLabel = ViewAssets.createLabel("soundSettingsLabel", "Sound Settings", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, true);
        this.soundOffOnButton = ViewAssets.createButton("soundOffOnButton", "Sound Off/On", this.screenSize * this.buttonHeightRatio);
        this.volumeLevelLabel = ViewAssets.createLabel("volumeLevelLabel", "Volume Level", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, true);
        this.volumeLevelSlider = ViewAssets.createSlider("volumeLevelSlider", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, 0.0, .5, .25, .05, .1, 2, true);
        // This line should be updated to not reach into the controller and pull data from a model.
        this.musicSelectionBox = ViewAssets.createComboBox("musicSelectionComboBox", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, "intro.mp3", this.controller.getMusicPlayer().getObservableMusic());
        // Adding all children to array, then using array to populate VBox
        this.soundMenuArray.add(this.soundSettingsLabel);
        this.soundMenuArray.add(this.soundOffOnButton);
        this.soundMenuArray.add(this.volumeLevelLabel);
        this.soundMenuArray.add(this.volumeLevelSlider);
        this.soundMenuArray.add(this.musicSelectionBox);
        this.soundMenuVBox = ViewAssets.createVBox(this.soundMenuArray, (.25 / 10) * this.screenSize, "soundMenuVBox", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position
        this.parentPane.getChildren().addAll(this.soundMenuVBox);
        // Initialize childrens events
        this.controller.setMediaPlayerStateOnMousePress(this.soundOffOnButton);
        this.controller.setMediaPlayerVolumeListener(this.volumeLevelSlider);
        this.controller.setMediaPlayerSelectionListener(this.musicSelectionBox);
    }

//*****************     GETTERS     *******************

    public AnchorPane getParentPane () {
        return this.parentPane;
    }

    public Slider getVolumeLevelSlider () {
        return this.volumeLevelSlider;
    }

    public ComboBox getMusicSelectionBox () {
        return this.musicSelectionBox;
    }

//*****************     SETTERS     *******************


    public void setParentPane (AnchorPane parentPane) {
        this.parentPane = parentPane;
    }

    public void setSoundOffOnButton (Button soundOffOnButton) {
        this.soundOffOnButton = soundOffOnButton;
    }

    public void setVolumeLevelSlider (Slider volumeLevelSlider) {
        this.volumeLevelSlider = volumeLevelSlider;
    }

    public void setMusicSelectionBox (ComboBox musicSelectionBox) {
        this.musicSelectionBox = musicSelectionBox;
    }

}