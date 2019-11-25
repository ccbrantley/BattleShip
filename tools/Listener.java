package battleship.tools;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Last Updated: 11/24/2019
 * This interface must be implemented by all functions which listen to the EventBus.
 *
 */

public interface Listener {

    public void catchEvent(Object _event);

}
