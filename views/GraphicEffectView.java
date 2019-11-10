package battleship.views;

import battleship.controller.Controller;
import battleship.tools.ResourceGetter;
import battleship.tools.ViewAssets;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/30/2019
 *
 * GraphicEffectView is the definition of the visual for the graphic effect modifier view.
 */

public class GraphicEffectView {

    public GraphicEffectView(Controller _controller) {
        // Adding controller for access to events
        this.controller = _controller;
        // Creating pane and children of the pane
        this.parentPane = this.viewAssets.createAnchorPane("graphicEffectPane", this.resources.getGraphicEffectCSS());
        this.displaySettingsLabel = this.viewAssets.createLabel("displaySettingsLabel", "Display Settings", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, true);
        this.brightnessLabel = this.viewAssets.createLabel("brightnesslabel", "Brightness", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, true);
        this.brightnessSlider = this.viewAssets.createSlider("brightnessSlider", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, -.5, .5, 0, .1, .2, 1, true);
        this.contrastLabel = this.viewAssets.createLabel("contrastLabel", "Contrast", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, true);
        this.contrastSlider = this.viewAssets.createSlider("contrastSlider", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, -.5, .5, 0, .1, .2, 1, true);
        this.saturationLabel = this.viewAssets.createLabel("saturationLabel", "Saturation Label", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, true);
        this.saturationSlider = this.viewAssets.createSlider("saturationSlider", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, -.5, .5, 0, .1, .2, 1, true);
        this.hueLabel = this.viewAssets.createLabel("hueLabel", "Hue", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, true);
        this.hueSlider = this.viewAssets.createSlider("hueSlider", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio, -.5, .5, 0, .1, .2, 1, true);
        // Adding all children to array, then using array to populate VBox
        graphicEffectArray.add(displaySettingsLabel);
        graphicEffectArray.add(brightnessLabel);
        graphicEffectArray.add(brightnessSlider);
        graphicEffectArray.add(contrastLabel);
        graphicEffectArray.add(contrastSlider);
        graphicEffectArray.add(saturationLabel);
        graphicEffectArray.add(saturationSlider);
        graphicEffectArray.add(hueLabel);
        graphicEffectArray.add(hueSlider);
        this.graphicEffectVBox = this.viewAssets.createVBox(this.graphicEffectArray, (.10/18) * this.screenSize, "graphicEffectVBox", this.screenSize * this.buttonWidthRatio, this.screenSize * this.buttonHeightRatio);
        // Adding all children to the Parent pane and setting their screen position
        this.parentPane.getChildren().addAll(this.graphicEffectVBox);
        // Initialize childrens events
        this.controller.setGraphicEffectBrightnessListener(this.brightnessSlider);
        this.controller.setGraphicEffectContrastsListener(this.contrastSlider);
        this.controller.setGraphicEffectSaturationListener(this.saturationSlider);
        this.controller.setGraphicEffectHueListener(this.hueSlider);
    }

    private Controller controller;
    private final double screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
    private final double screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
    private double screenSize = (this.screenWidth > this.screenHeight) ? this.screenHeight : this.screenWidth;
    private double buttonWidthRatio = 1/3;
    private double buttonHeightRatio = .10;
    private ResourceGetter resources = new ResourceGetter();
    private ViewAssets viewAssets = new ViewAssets();
    private AnchorPane parentPane;
    private VBox graphicEffectVBox;
    private ArrayList<Node> graphicEffectArray = new ArrayList<Node>();
    private Label displaySettingsLabel;
    private Label brightnessLabel;
    private Label contrastLabel;
    private Label saturationLabel;
    private Label hueLabel;
    private Slider brightnessSlider;
    private Slider contrastSlider;
    private Slider saturationSlider;
    private Slider hueSlider;

//*****************     GETTERS     *******************

    public Controller getController() {
        return controller;
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public double getButtonWidthRatio() {
        return buttonWidthRatio;
    }

    public double getButtonHeightRatio() {
        return buttonHeightRatio;
    }

    public ResourceGetter getResources() {
        return resources;
    }

    public ViewAssets getViewAssets() {
        return viewAssets;
    }

    public AnchorPane getParentPane() {
        return parentPane;
    }

    public VBox getGraphicEffectVBox() {
        return graphicEffectVBox;
    }

    public ArrayList<Node> getGraphicEffectArray() {
        return graphicEffectArray;
    }

    public Label getDisplaySettingsLabel() {
        return displaySettingsLabel;
    }

    public Label getBrightnessLabel() {
        return brightnessLabel;
    }

    public Label getContrastLabel() {
        return contrastLabel;
    }

    public Label getSaturationLabel() {
        return saturationLabel;
    }

    public Label getHueLabel() {
        return hueLabel;
    }

    public Slider getBrightnessSlider() {
        return brightnessSlider;
    }

    public Slider getContrastSlider() {
        return contrastSlider;
    }

    public Slider getSaturationSlider() {
        return saturationSlider;
    }

    public Slider getHueSlider() {
        return hueSlider;
    }


//*****************     SETTERS     *******************

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public void setButtonWidthRatio(double buttonWidthRatio) {
        this.buttonWidthRatio = buttonWidthRatio;
    }

    public void setButtonHeightRatio(double buttonHeightRatio) {
        this.buttonHeightRatio = buttonHeightRatio;
    }

    public void setResources(ResourceGetter resources) {
        this.resources = resources;
    }

    public void setViewAssets(ViewAssets viewAssets) {
        this.viewAssets = viewAssets;
    }

    public void setParentPane(AnchorPane parentPane) {
        this.parentPane = parentPane;
    }

    public void setGraphicEffectVBox(VBox graphicEffectVBox) {
        this.graphicEffectVBox = graphicEffectVBox;
    }

    public void setGraphicEffectArray(ArrayList<Node> graphicEffectArray) {
        this.graphicEffectArray = graphicEffectArray;
    }

    public void setDisplaySettingsLabel(Label displaySettingsLabel) {
        this.displaySettingsLabel = displaySettingsLabel;
    }

    public void setBrightnessLabel(Label brightnessLabel) {
        this.brightnessLabel = brightnessLabel;
    }

    public void setContrastLabel(Label contrastLabel) {
        this.contrastLabel = contrastLabel;
    }

    public void setSaturationLabel(Label saturationLabel) {
        this.saturationLabel = saturationLabel;
    }

    public void setHueLabel(Label hueLabel) {
        this.hueLabel = hueLabel;
    }

    public void setBrightnessSlider(Slider brightnessSlider) {
        this.brightnessSlider = brightnessSlider;
    }

    public void setContrastSlider(Slider contrastSlider) {
        this.contrastSlider = contrastSlider;
    }

    public void setSaturationSlider(Slider saturationSlider) {
        this.saturationSlider = saturationSlider;
    }

    public void setHueSlider(Slider hueSlider) {
        this.hueSlider = hueSlider;
    }

}