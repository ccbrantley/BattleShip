/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.tools;

import battleship.models.BattleShipPlayer;

/**
 *
 * @author Christopher
 */
public class BattleShipBotInterpreter {
    public BattleShipBotInterpreter(BattleShipPlayer _player) {
    this.player = _player;
    }
    BattleShipPlayer player;

    public void catchEvent(Object _event) {
    }
}