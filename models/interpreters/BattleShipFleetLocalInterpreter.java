package battleship.models.interpreters;

/* @author Area 51 Block Party:
 * Christopher Brantley, Andrew Braswell
 * Last Updated: 12/03/2019
 */

import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.models.BattleShipShip;
import battleship.tools.Listener;
import battleship.tools.events.*;

public class BattleShipFleetLocalInterpreter implements Listener {

    BattleShipPlayer player;

    public BattleShipFleetLocalInterpreter (BattleShipPlayer _player) {
        this.player = _player;
    }

    @Override
    public void catchEvent (Object _event) {
        if (_event instanceof MoveShipIncrementallyEvent) {
            MoveShipIncrementallyEvent event = (MoveShipIncrementallyEvent)_event;
            int row = event.getRow();
            int column = event.getColumn();
            this.player.getBattleShipFleet().moveShipIncrementally(row, column, this.player.getSelectedShip());
            this.refreshGame();
        }

        if (_event instanceof MoveShipEvent) {
            MoveShipEvent event = (MoveShipEvent)_event;
            int row = event.getRow();
            int column = event.getColumn();
            int shipType = event.getShipType();
            this.player.getBattleShipFleet().moveShip(row, column, shipType);
            this.refreshGame();
        }

        if (_event instanceof RotateShipEvent) {
            RotateShipEvent event = (RotateShipEvent)_event;
            int shipType = event.getShipType();
            String shipId = BattleShipShip.convertShipTypeToId(shipType);
            this.player.getBattleShipFleet().getFleetOfShips().forEach(ship -> {
                if(ship.getShipId().equals(shipId)) {
                    ship.rotateShip();
                }
            });
            this.refreshGame();
        }

        if (_event instanceof RandomizeShipsEvent) {
            this.player.getBattleShipFleet().randomizeShips();
            this.player.getBattleShipFleet().throwAllPositionUpdateEvents();
            this.refreshGame();
        }

        if (_event instanceof SetTargetEvent) {
            SetTargetEvent event = (SetTargetEvent)_event;
            this.player.setCurrentTarget(event.getCoordinate());
        }

    }

    private void refreshGame () {
        BattleShipGame.getEventBus().throwEvent(new ClearGridEvent());
        this.player.getBattleShipFleet().throwAllPositionUpdateEvents();
    }

}