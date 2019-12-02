/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.tools.events;

/**
 *
 * @author Richard
 */
public class UpdateGraphicEffectViewEvent {

    private double brightness;
    private double contrast;
    private double hue;
    private double saturation;

    public UpdateGraphicEffectViewEvent(double _brightness, double _contrast,double _hue, double _saturation){
        this.brightness = _brightness;
        this.contrast = _contrast;
        this.hue = _hue;
        this.saturation = _saturation;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    public void setContrast(double contrast) {
        this.contrast = contrast;
    }

    public void setHue(double hue) {
        this.hue = hue;
    }

    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }

    public double getBrightness() {
        return brightness;
    }

    public double getContrast() {
        return contrast;
    }

    public double getHue() {
        return hue;
    }

    public double getSaturation() {
        return saturation;
    }


}