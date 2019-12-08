package battleship.tools;

/* @author Area 51 Block Party:
 * Christopher Brantley, Andrew Braswell
 * Last Updated: 11/24/2019
 */

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class EventBus {

    private ArrayList<Listener> listeners = new ArrayList();

    public EventBus() {
    }

    public final void throwEvent(Object _event) {
        this.listeners.forEach(listener -> {
            try {
                listener.catchEvent(_event);
            } catch (Exception e) {
                Logger.getLogger(EventBus.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public final void addListener (Listener _listener) {
        this.listeners.add(_listener);
    }

    public final void removeListener (Listener _listener) {
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

    public void resetListeners() {
        this.listeners = null;
        this.listeners = new ArrayList();
    }

}
