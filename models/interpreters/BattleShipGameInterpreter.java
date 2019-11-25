package battleship.models.interpreters;

/* @author Area 51 Block Party:
 * Christopher Brantley, Andrew Braswell
 * Last Updated: 11/24/2019
 */

import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.models.Coordinate;
import battleship.tools.events.FireAwayEvent;
import battleship.tools.events.*;
import battleship.tools.Listener;


public class BattleShipGameInterpreter implements Listener {

    public BattleShipGameInterpreter(BattleShipGame _battleShipGame) {
        this.battleShipGame = _battleShipGame;
    }
    private BattleShipGame battleShipGame;

    @Override
    public void catchEvent(Object _event) {
        if (_event instanceof FireAwayEvent) {
            FireAwayEvent event = (FireAwayEvent)_event;
            int destination = event.getDestination();
            if (destination == BattleShipPlayer.AWAY) {
                Coordinate selectedCoordinate = this.battleShipGame.getPlayer1().getCurrentTarget();
                GameUpdateUserMessage messageEvent;
                if (this.battleShipGame.getPlayer2().getBattleShipFleet().receiveFire(selectedCoordinate)) {
                    messageEvent = new GameUpdateUserMessage("Ship Hit.");
                }
                else {
                    messageEvent = new GameUpdateUserMessage("Shot Missed.");
                }
                BattleShipGame.getEventBus().throwEvent(messageEvent);
                this.battleShipGame.getPlayer1().setTurn(true);
                this.battleShipGame.getPlayer2().setTurn(true);
            }
        }
    }
}
