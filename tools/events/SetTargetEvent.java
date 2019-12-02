package battleship.tools.events;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/28/2019
 * Event that contains information
 * to update the model to show
 * which index to fire at.
 */

import battleship.models.Coordinate;

public class SetTargetEvent {

    private Coordinate coordinate;

    public SetTargetEvent (Coordinate _coordinate){
        this.coordinate = _coordinate;
    }

//*****************     GETTERS     *******************

    public Coordinate getCoordinate () {
        return this.coordinate;
    }

//*****************     SETTERS     *******************

    public void setCoordinate (Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}