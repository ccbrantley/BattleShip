package battleship.controllers;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/15/2019
 */

import battleship.models.Animator;
import battleship.models.LoaderGetter;
import battleship.models.MapPane;
import battleship.models.MappingPane;
import battleship.models.ResourceGetter;
import battleship.views.BattleShipGamePane;
import battleship.views.ShipSelectionPane;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class PlayControllerLogic {

    public PlayControllerLogic (FXMLPlayController _controller) throws FileNotFoundException {
     this.playController = _controller;
    }

    public void initializeController(){
        this.playController.getMainButton().setOnAction(event -> {try {
            this.returnMainMenu(event);
            } catch (IOException ex) {
                Logger.getLogger(PlayControllerLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.playController.getSinglePlayerButton().setOnAction(event -> {this.startShipSelectionPaneSequence(event);});
    }

    FXMLPlayController playController;
    private LoaderGetter loaderGetter;
    private final ResourceGetter resourceGetter = new ResourceGetter();
    private final int BOARDCOLUMNSIZE = 10;
    private final int BOARDROWSIZE = 10;
    private final Animator explosionAnimation = new Animator("EXPLOSION");
    private HashMap allShips = new HashMap();

    public void returnMainMenu(ActionEvent _event) throws IOException {
        this.loaderGetter.getMainController().getLogic().setScene(_event);
    }

    public void startShipSelectionPaneSequence(ActionEvent _event) {
        double screenWidth = this.loaderGetter.getScreenWidth();
        double screenHeight = this.loaderGetter.getScreenHeight();
        AnchorPane mainPane = this.playController.getMainPane();
        ShipSelectionPane shipSelectionPane = new ShipSelectionPane(this,screenWidth, screenHeight);
        shipSelectionPane.loadPane(mainPane);

    }

    public void startBattleShipGame(HashMap _battleShips, GridPane _playerShipPane){
        AnchorPane mainPane = this.playController.getMainPane();
        BattleShipGamePane battleShipGamePane = new BattleShipGamePane(this,this.getScreenWidth(), this.getScreenHeight(), _battleShips, _playerShipPane);
        battleShipGamePane.loadPane(mainPane);
    }
// Probably allow this to take in grid pane row and coordinates and then display
// Should allow this to take in x, y coordinates or use getSectorFromAlpha to get a button then receive it's position
    public void displayExplosion (ActionEvent _event) throws FileNotFoundException {
        ImageView explosionView = this.explosionAnimation.getImageView();
        this.explosionAnimation.setImageViewScale(.2,.2);
        this.explosionAnimation.setImageViewLayout(600, 610);
        this.playController.getMainPane().getChildren().add(explosionView);
        this.explosionAnimation.playAnimation();
        this.explosionAnimation.getTimeline().setOnFinished(event -> {this.playController.getMainPane().getChildren().remove(explosionView);});
    }

   public static Predicate<Node> isButton(){
        return p -> (p instanceof Button);
    }

//*****************     GETTERS     *******************

    public MappingPane getChildren () {
        MappingPane mainPane = new MappingPane();
        //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeight
        mainPane.mapToPane(new MapPane(this.playController.getMenuPane(), "middle", "center", 1, 1, true, false));
        return mainPane;
    }

    public FXMLPlayController getPlayController() {
        return this.playController;
    }

    public LoaderGetter getLoaderGetter() {
        return this.loaderGetter;
    }

    public double getScreenWidth() {
        return this.loaderGetter.getScreenWidth();
    }

    public double getScreenHeight() {
        return this.loaderGetter.getScreenHeight();
    }

//*****************     SETTERS     *******************

    public void setLoaderGetter(LoaderGetter _loaderGetter) {
        this.loaderGetter = _loaderGetter;
    }
}
