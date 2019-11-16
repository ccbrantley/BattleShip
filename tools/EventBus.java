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
    public EventBus() {}
    private ArrayList listeners = new ArrayList();

    public final void throwEvent(Object _event) {
        this.listeners.forEach(child -> {
            try {
                Method catchEvent = child.getClass().getMethod("catchEvent", Object.class);
                catchEvent.invoke(child, _event);
            } catch (Exception e) {
                Logger.getLogger(EventBus.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public final void addListener (Object _listener) {
        this.listeners.add(_listener);
    }

    public final void removeListener (Object _listener) {
        this.listeners.remove(_listener);
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
