package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/30/2019
 */

import javafx.scene.effect.ColorAdjust;

public class GraphicEffect {

    public GraphicEffect() {}
    private ColorAdjust colorAdjust = new ColorAdjust();

//*****************     GETTERS     *******************

    public ColorAdjust getColorAdjust() {
        return colorAdjust;
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

}
