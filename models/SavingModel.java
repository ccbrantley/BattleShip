package battleship.models;

/* @author Area 51 Block Party:
 * Richard Abrams
 * Last Updated: 12/8/2019
 * This class acts as a pipeline to the saving translator and applies the saved data appropiately.
 */

import battleship.tools.SavingTranslator;
import java.util.ArrayList;
import java.util.Arrays;

public class SavingModel {

    private final SavingTranslator savingTranslator = new SavingTranslator();
    private MusicPlayer musicPlayer;
    private GraphicEffect graphicsEffect;

    //This method is the constructor.
    public SavingModel (MusicPlayer _musicPlayer, GraphicEffect _graphicsEffect) {
        musicPlayer = _musicPlayer;
        graphicsEffect = _graphicsEffect;
    }

    //This method loads all the values from the settings file.
    public void loadSettings () {
        this.setSettings(this.savingTranslator.load(GraphicEffect.CONTRAST), GraphicEffect.CONTRAST);
        this.setSettings(this.savingTranslator.load(GraphicEffect.BRIGHTNESS), GraphicEffect.BRIGHTNESS);
        this.setSettings(this.savingTranslator.load(GraphicEffect.HUE), GraphicEffect.HUE);
        this.setSettings(this.savingTranslator.load(GraphicEffect.SATURATION), GraphicEffect.SATURATION);
        this.setSettings(this.savingTranslator.load(MusicPlayer.VOLUME), MusicPlayer.VOLUME);
    }

    //This method saves various settings to the settings file.
    public void saveSettings (GraphicEffect _graphicsEffect, MusicPlayer _musicPlayer) {
        ArrayList<Object> data = new ArrayList<> (
                Arrays.asList(
                        GraphicEffect.getScreenWidth(),
                        GraphicEffect.getScreenHeight(),
                        _graphicsEffect.getColorAdjust().getContrast(),
                        _graphicsEffect.getColorAdjust().getBrightness(),
                        _graphicsEffect.getColorAdjust().getHue(),
                        _graphicsEffect.getColorAdjust().getSaturation(),
                        _musicPlayer.getMediaPlayer().getVolume()
                ));
        this.savingTranslator.saveList(data);
    }

    //A helper method that applies loaded values to the appropiate classes for settings.
    private void setSettings (String _data, int _loadType) {
        if (_data.equals(" ")) {
            return;
        }
        switch (_loadType) {
            case GraphicEffect.CONTRAST:
                this.graphicsEffect.setContrastLevel(Double.parseDouble(_data));
            case GraphicEffect.BRIGHTNESS:
                 this.graphicsEffect.setBrightnessLevel(Double.parseDouble(_data));
            case GraphicEffect.HUE:
                 this.graphicsEffect.setHueLevel(Double.parseDouble(_data));
            case GraphicEffect.SATURATION:
                 this.graphicsEffect.setSaturationLevel(Double.parseDouble(_data));
            case MusicPlayer.VOLUME:
                this.musicPlayer.setVolumeLevel(Double.parseDouble(_data));
        }
    }

}