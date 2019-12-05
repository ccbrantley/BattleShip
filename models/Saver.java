/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.models;

import battleship.tools.SerializerAdapter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author darth
 */



public class Saver {

    private final SerializerAdapter serializerAdapter = new SerializerAdapter();

 //loads all the values from the settings file.
    private void loadSettings (GraphicEffect _graphicsEffect) {
        this.setSettings(this.serializerAdapter.extractData(GraphicEffect.CONTRAST), GraphicEffect.CONTRAST, _graphicsEffect);
        this.setSettings(this.serializerAdapter.extractData(GraphicEffect.BRIGHTNESS), GraphicEffect.BRIGHTNESS, _graphicsEffect);
        this.setSettings(this.serializerAdapter.extractData(GraphicEffect.HUE), GraphicEffect.HUE, _graphicsEffect);
        this.setSettings(this.serializerAdapter.extractData(GraphicEffect.SATURATION), GraphicEffect.SATURATION, _graphicsEffect);
        //this.setSettings(this.serializerAdapter.extractData(MusicPlayer.VOLUME), MusicPlayer.VOLUME);
    }

    private void setSettings (String _data, int _loadType, GraphicEffect _graphicsEffect) {
        if (_data.equals(" ")) {
            return;
        }
        switch (_loadType) {
            case GraphicEffect.CONTRAST:
                _graphicsEffect.setContrastLevel(Double.parseDouble(_data));
            case GraphicEffect.BRIGHTNESS:
                 _graphicsEffect.setBrightnessLevel(Double.parseDouble(_data));
            case GraphicEffect.HUE:
                 _graphicsEffect.setHueLevel(Double.parseDouble(_data));
            case GraphicEffect.SATURATION:
                 _graphicsEffect.setSaturationLevel(Double.parseDouble(_data));
            //case MusicPlayer.VOLUME:
                //this.musicPlayer.setVolumeLevel(Double.parseDouble(_data));
        }
    }

    //saves saves various settings to the settings file.
    private void saveSettings (GraphicEffect _graphicsEffect, MusicPlayer _musicPlayer) {
        ArrayList<Object> data = new ArrayList<>(
                Arrays.asList(
                        GraphicEffect.getScreenWidth(),
                        GraphicEffect.getScreenHeight(),
                        _graphicsEffect.getColorAdjust().getContrast(),
                        _graphicsEffect.getColorAdjust().getBrightness(),
                        _graphicsEffect.getColorAdjust().getHue(),
                        _graphicsEffect.getColorAdjust().getSaturation(),
                        _musicPlayer.getMediaPlayer().getVolume()
                ));
        this.serializerAdapter.save(data);
    }

    private class serializerAdapter {

        public serializerAdapter() {
        }
    }
}
