package battleship;
/* BattleShip
 *
 * @author Area 51 Block Party: 
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 */
import battleship.Controllers.FXMLMainController;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Modality;
public class BattleShip extends Application {
    private LoaderGetter loaderGetter = new LoaderGetter();
    
    @Override
    public void start(Stage stage) throws Exception {
        // Setting All Loaders
        FXMLLoader loader;
        ArrayList<String> resourcePaths = new ArrayList();
        resourcePaths.add("Controllers/FXML/FXMLMain.fxml");
        resourcePaths.add("Controllers/FXML/FXMLPlay.fxml");
        resourcePaths.add("Controllers/FXML/FXMLResume.fxml");
        resourcePaths.add("Controllers/FXML/FXMLSettings.fxml");
        for (String path : resourcePaths){
            loader = new FXMLLoader(getClass().getResource(path));
            this.loaderGetter.addLoader(loader,path.substring(20));
        }
        FXMLMainController mainController = this.loaderGetter.getMainController();
        mainController.setLoaderGetter(this.loaderGetter);
        Scene scene = new Scene(loaderGetter.getMainRoot());
        ControllerHandler controllerHandler = new ControllerHandler();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
        controllerHandler.manageLayout(stage,mainController);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
