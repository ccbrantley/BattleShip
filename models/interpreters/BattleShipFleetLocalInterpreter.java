package battleship.models.interpreters;

import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.tools.events.*;

public class BattleShipFleetLocalInterpreter{

    public BattleShipFleetLocalInterpreter(BattleShipPlayer _player) {
        this.player = _player;
    }

    BattleShipPlayer player;

    public void catchEvent(Object _event) {
        if (_event instanceof MoveShipIncrementEvent) {
            MoveShipIncrementEvent event = (MoveShipIncrementEvent)_event;
            int row = event.getRow();
            int column = event.getColumn();
            String type = event.getType();
            this.player.getBattleShipFleet().moveShipIncrementally(row, column, type);
            this.refreshGame();
        }

        if (_event instanceof MoveShipEvent) {
            MoveShipEvent event = (MoveShipEvent)_event;
            int row = event.getRow();
            int column = event.getColumn();
            String type = event.getType();
            this.player.getBattleShipFleet().moveShip(row, column, type);
            this.refreshGame();
        }

        if (_event instanceof RotateShipEvent) {
            RotateShipEvent event = (RotateShipEvent)_event;
            String type = event.getType();
            this.player.getBattleShipFleet().getFleetOfShips().forEach(ship -> {
                if(ship.getShipId().equals(type)) {
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
            BattleShipGame.getEventBus().throwEvent(new ClearGridEvent());
            SetTargetEvent event = (SetTargetEvent)_event;
            this.refreshGame();
        }

    }
    private void refreshGame() {
        BattleShipGame.getEventBus().throwEvent(new ClearGridEvent());
        this.player.getBattleShipFleet().throwAllPositionUpdateEvents();
    }
}
