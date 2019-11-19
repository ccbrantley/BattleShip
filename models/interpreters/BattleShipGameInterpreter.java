/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.models.interpreters;

import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.models.Coordinate;
import battleship.tools.events.FireAwayEvent;

/**
 *
 * @author Christopher
 */
public class BattleShipGameInterpreter {
    public BattleShipGameInterpreter(BattleShipGame _battleShipGame) {
        this.battleShipGame = _battleShipGame;
    }
    private BattleShipGame battleShipGame;

    public void catchEvent(Object _event) {
        if (_event instanceof FireAwayEvent) {
            FireAwayEvent event = (FireAwayEvent)_event;
            int destination = event.getDestination();
            if (destination == BattleShipPlayer.AWAY) {
                System.out.println("shotsfired");
                Coordinate selectedCoordinate = this.battleShipGame.getPlayer1().getCurrentTarget();
                this.battleShipGame.getPlayer2().getBattleShipFleet().fireAt(selectedCoordinate);
                this.battleShipGame.getPlayer1().setTurn(true);
                this.battleShipGame.getPlayer2().setTurn(true);
            }
        }
    }
}
