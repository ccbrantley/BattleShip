/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.views.interpreters;

import battleship.models.GraphicEffect;
import battleship.tools.Listener;
import battleship.tools.SerializerAdapter;
import battleship.tools.events.*;
import battleship.views.GraphicEffectView;
/**
 *
 * @author Richard
 */
public class GraphicsEffectViewInterpreter implements Listener{

    private final GraphicEffectView graphicEffectView;
    private SerializerAdapter serializerAdapter = new SerializerAdapter();
    private GraphicEffect graphicEffect = new GraphicEffect();

    public GraphicsEffectViewInterpreter (GraphicEffectView graphicEffectView) {
        this.graphicEffectView = graphicEffectView;
    }
    public void catchEvent(Object _event) {

        if(_event instanceof SaveGraphicsEvent){

            SaveGraphicsEvent event = ((SaveGraphicsEvent)_event);
            double brightness = event.getBrightness();
            double contrast = event.getContrast();
            double hue = event.getHue();
            double satruation = event.getSaturation();



        }

    }


}
