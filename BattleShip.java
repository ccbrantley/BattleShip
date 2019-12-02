package battleship;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 11/03/2019
 */

import battleship.controller.Controller;
import battleship.tools.ViewAssets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;

public class BattleShip extends Application {

    @Override
    public void start(Stage _stage) throws Exception {
        // Create the controller, set the scene to the main menu
        this.setStageProperties(_stage);
        (new Controller(_stage)).setScene(ViewAssets.MAIN);
        // Show the stage on the window
        _stage.show();
    }

    // Procedure to apply all properties to stage
    public void setStageProperties(Stage _stage) {
        _stage.setScene(new Scene(new Pane()));
        _stage.setFullScreen(true);
        _stage.setResizable(false);
        _stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    }

    /**
     * @param _args the command line arguments
     */
    public static void main(String[] _args) {
        launch(_args);
    }

}
