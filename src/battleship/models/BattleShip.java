package battleship.models;

/* BattleShip
 *
 * @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated 09/30/2019
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BattleShip extends Application {

    private LoaderGetter loaderGetter = new LoaderGetter();

    @Override
    public void start(Stage _stage) throws Exception {
        // Setting paths for all loaders
        ArrayList<String> resourcePaths = new ArrayList();
        resourcePaths.add("../views/FXML/FXMLMain.fxml");
        resourcePaths.add("../views/FXML/FXMLPlay.fxml");
        resourcePaths.add("../views/FXML/FXMLResume.fxml");
        resourcePaths.add("../views/FXML/FXMLSettings.fxml");

        FXMLLoader loader;
        for (String path : resourcePaths) {
            // Initializing all loaders
            loader = new FXMLLoader(getClass().getResource(path));
            this.loaderGetter.addLoader(loader,path.substring(20));
            // Assigning all loaderGetter to all controllers
            Object controller = loader.getController();
             try{
                Method method = controller.getClass().getMethod("setLoaderGetter", LoaderGetter.class);
                method.invoke(controller, this.loaderGetter);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(ControllerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

// Setting the stage and stage properties
        _stage.setScene(new Scene(loaderGetter.getMainRoot()));
        _stage.setFullScreen(true);
        _stage.setResizable(false);
        _stage.show();

// Passing stage and maincontroller to position children
        ControllerHandler controllerHandler = new ControllerHandler();
        controllerHandler.manageLayout(_stage,this.loaderGetter.getMainController());
    }

    /**
     * @param _args the command line arguments
     */
    public static void main(String[] _args) {
        launch(_args);
    }
}
