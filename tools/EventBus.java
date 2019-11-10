package battleship.tools;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 */

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class EventBus {
    public EventBus(){
        this.listeners.add(new ShipSelectionViewInterpreter());
    }
    private ArrayList listeners = new ArrayList();
    ShipSelectionViewInterpreter interpreter = new ShipSelectionViewInterpreter();

    public final void throwEvent(Object _event) {
        this.interpreter.catchEvent(_event);
        /*this.listeners.forEach(child -> {
            try {
                Method catchEvent = child.getClass().getMethod("catchEvent", Object.class);
                catchEvent.invoke(child, _event);
            } catch (Exception e) {
                Logger.getLogger(EventBus.class.getName()).log(Level.SEVERE, null, e);
            }
        });*/
    }

//*****************     GETTERS     *******************

    public final ArrayList getListeners() {
        return this.listeners;
    }

//*****************     SETTERS     *******************

    public final void setListeners(ArrayList listeners) {
        this.listeners = listeners;
    }

}
