package battleship;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/12/2019
 */

import battleship.models.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCombination;

public class BattleShip extends Application {

    private LoaderGetter loaderGetter = new LoaderGetter();

    @Override
    public void start(Stage _stage) throws Exception {
        // Setting paths for all loaders
        ArrayList<String> resourcePaths = new ArrayList();
        resourcePaths.add("views/FXMLMain.fxml");
        //resourcePaths.add("views/FXMLPlay.fxml");
        resourcePaths.add("views/FXMLResume.fxml");
        resourcePaths.add("views/FXMLSettings.fxml");

        FXMLLoader loader;
        for (String path : resourcePaths) {
            // Initializing all loaders
            loader = new FXMLLoader(getClass().getResource(path));
            this.loaderGetter.addLoader(loader,path.substring(10));
            // Assigning all loaderGetter to all controllers Logic
            Object controller = loader.getController();
             try{
                Method getLogic = controller.getClass().getMethod("getLogic");
                Object controllerLogic = getLogic.invoke(controller);
                Method method = controllerLogic.getClass().getMethod("setLoaderGetter", LoaderGetter.class);
                method.invoke(controllerLogic, this.loaderGetter);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(ControllerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Setting the stage and stage properties
        Scene scene = new Scene(loaderGetter.getMainRoot());
        _stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); 
        _stage.setScene(scene);
        _stage.setFullScreen(true);
        _stage.setResizable(false);
        _stage.show();
        // Passing stage and maincontroller to position children
        ControllerHandler controllerHandler = new ControllerHandler();
        controllerHandler.manageLayout(_stage, this.loaderGetter.getMainController().getLogic());
    }

    /**
     * @param _args the command line arguments
     */
    public static void main(String[] _args) {
        launch(_args);
    }
}
