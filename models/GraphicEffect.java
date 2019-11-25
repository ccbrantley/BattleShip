package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/30/2019
 */

import java.awt.GraphicsEnvironment;
import javafx.scene.effect.ColorAdjust;

public class GraphicEffect {

    public GraphicEffect() {}
    private ColorAdjust colorAdjust = new ColorAdjust();
    private static double screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
    private static double screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
    
    public final int SCREENWIDTH = 1;
    public final int SCREELENGTH = 2;
    public final int CONTRAST = 3;
    public final int BRIGHTNESS = 4;
    public final int HUE = 5;
    public final int SATURATION = 6;
    
//*****************     GETTERS     *******************

    public ColorAdjust getColorAdjust() {
        return colorAdjust;
    }

    public static double getScreenWidth() {
        return screenWidth;
    }

    public static double getScreenHeight() {
        return screenHeight;
    }

//*****************     SETTERS     *******************

    public void setBrightnessLevel(Number _brightnessLevel) {
        this.colorAdjust.setBrightness(_brightnessLevel.doubleValue());
    }

    public void setContrastLevel(Number _contrastLevel) {
        this.colorAdjust.setContrast(_contrastLevel.doubleValue());
    }

    public void setSaturationLevel(Number _saturationLevel) {
        this.colorAdjust.setSaturation(_saturationLevel.doubleValue());
    }

    public void setHueLevel(Number _hueLevel) {
        this.colorAdjust.setHue(_hueLevel.doubleValue());
    }

    public void setColorAdjust(ColorAdjust colorAdjust) {
        this.colorAdjust = colorAdjust;
    }

    public static void setScreenWidth(double screenWidth) {
        GraphicEffect.screenWidth = screenWidth;
    }

    public static void setScreenHeight(double screenHeight) {
        GraphicEffect.screenHeight = screenHeight;
    }

}
