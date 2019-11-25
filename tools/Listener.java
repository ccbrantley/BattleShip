package battleship.tools;
/**
* This interface must be implemented by all functions which listen to the EventBus.
*
* @author Andrew Braswell Last Updated: 11/24/2019
*/

public interface Listener {

    public void catchEvent(Object _event);

}
