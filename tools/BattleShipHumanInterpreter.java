package battleship.tools;

import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.tools.events.*;

public class BattleShipHumanInterpreter{

    public BattleShipHumanInterpreter(BattleShipPlayer _player) {
        this.player = _player;
    }

    BattleShipPlayer player;

    public void catchEvent(Object _event) {
        if (_event instanceof MoveShipIncrementEvent) {
            BattleShipGame.getEventBus().throwEvent(new ClearGridEvent());
            MoveShipIncrementEvent event = (MoveShipIncrementEvent)_event;
            int row = event.getRow();
            int column = event.getColumn();
            String type = event.getType();
            this.player.getBattleShipFleet().moveShipIncrementally(row, column, type);
            this.player.getBattleShipFleet().throwAllPositionUpdateEvents();
        }

        if (_event instanceof MoveShipEvent) {
            BattleShipGame.getEventBus().throwEvent(new ClearGridEvent());
            MoveShipEvent event = (MoveShipEvent)_event;
            int row = event.getRow();
            int column = event.getColumn();
            String type = event.getType();
            this.player.getBattleShipFleet().moveShip(row, column, type);
            this.player.getBattleShipFleet().throwAllPositionUpdateEvents();
        }

        if (_event instanceof RotateShipEvent) {
            BattleShipGame.getEventBus().throwEvent(new ClearGridEvent());
            RotateShipEvent event = (RotateShipEvent)_event;
            String type = event.getType();
            this.player.getBattleShipFleet().getFleetOfShips().forEach(ship -> {
                if(ship.getShipId().equals(type)) {
                    ship.rotateShip();
                }
            });
            this.player.getBattleShipFleet().throwAllPositionUpdateEvents();
        }

        if (_event instanceof RandomizeShipsEvent) {
            BattleShipGame.getEventBus().throwEvent(new ClearGridEvent());
            this.player.getBattleShipFleet().randomizeShips();
            this.player.getBattleShipFleet().throwAllPositionUpdateEvents();
        }

        if (_event instanceof SetTargetEvent) {
            BattleShipGame.getEventBus().throwEvent(new ClearGridEvent());
            SetTargetEvent event = (SetTargetEvent)_event;
            this.player.setCurrentTarget(event.getCoordinate());
            this.player.getBattleShipFleet().throwAllPositionUpdateEvents();
        }

    }
}
