package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley, Richard Abrams
 * Last Updated: 11/27/2019
 * GraphicEffect serves as the model for a JavaFx colorAdjust Object as well
 * as an placeholder for various graphical properties.
 */

import java.awt.GraphicsEnvironment;
import javafx.scene.effect.ColorAdjust;

public class GraphicEffect {

    private ColorAdjust colorAdjust = new ColorAdjust();
    private static double screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
    private static double screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();

    // Enumerators -> Serialization Type.
    public static final int SCREENWIDTH = 1;
    public static final int SCREELENGTH = 2;
    public static final int CONTRAST = 3;
    public static final int BRIGHTNESS = 4;
    public static final int HUE = 5;
    public static final int SATURATION = 6;

    public GraphicEffect () {
    }

//*****************     GETTERS     *******************

    public ColorAdjust getColorAdjust () {
        return this.colorAdjust;
    }

    public static double getScreenWidth () {
        return screenWidth;
    }

    public static double getScreenHeight () {
        return screenHeight;
    }

//*****************     SETTERS     *******************

    public void setBrightnessLevel (Number _brightnessLevel) {
        this.colorAdjust.setBrightness(_brightnessLevel.doubleValue());
    }

    public void setContrastLevel (Number _contrastLevel) {
        this.colorAdjust.setContrast(_contrastLevel.doubleValue());
    }

    public void setSaturationLevel (Number _saturationLevel) {
        this.colorAdjust.setSaturation(_saturationLevel.doubleValue());
    }

    public void setHueLevel (Number _hueLevel) {
        this.colorAdjust.setHue(_hueLevel.doubleValue());
    }

    public void setColorAdjust (ColorAdjust _colorAdjust) {
        this.colorAdjust = _colorAdjust;
    }

    public static void setScreenWidth (double _screenWidth) {
        GraphicEffect.screenWidth = _screenWidth;
    }

    public static void setScreenHeight (double _screenHeight) {
        GraphicEffect.screenHeight = _screenHeight;
    }

}