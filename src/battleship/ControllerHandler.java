package battleship;

/* This class serves to take a controller and readjust its children
 * on the stage to allow proper graphic display.
 * @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 */

import battleship.Controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.lang.Object;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerHandler extends Pane {

/** This function takes a stage and the respective controller and, after
 *  returning the controllers children, will adjust their position.
 *  calculatePosition on the panes of the
 * @param _stage the stage
 * @param _currentController the controller
 */
    public void manageLayout(Stage _stage, Object _currentController) {
        MappingPane mainPane = null;
        try{
            Method method = _currentController.getClass().getMethod("getChildren");
            mainPane = (MappingPane) method.invoke(_currentController);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ControllerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(mainPane != null){
            double stageHeight = _stage.getHeight();
            double stageWidth = _stage.getWidth();
            mainPane.calculatePosition(stageHeight, stageWidth);
        }
    }
}
