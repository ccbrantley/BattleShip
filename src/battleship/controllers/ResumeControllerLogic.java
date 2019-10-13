package battleship.controllers;

import battleship.models.LoaderGetter;
import battleship.models.MapPane;
import battleship.models.MappingPane;
import java.io.IOException;
import javafx.event.ActionEvent;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/12/2019
 */

public class ResumeControllerLogic {

    public ResumeControllerLogic (FXMLResumeController _controller) {
        this.resumeController = _controller;
    }
    private final FXMLResumeController resumeController;
    private LoaderGetter loaderGetter;

    public void returnMainMenu(ActionEvent event) throws IOException {
        this.loaderGetter.getMainController().getLogic().setScene(event);
    }

//*****************     GETTERS     *******************
    public MappingPane getChildren() {
    MappingPane mainPane = new MappingPane();
    //Pane passedPane, String relativePosition, double aspectWidth, double aspectHeigh, fillvertical, fillhorizontal
    mainPane.mapToPane(new MapPane(this.resumeController.getMainGridPane(), "middle", "center", 1, 1, true, true));
    return mainPane;
}
//*****************     SETTERS     *******************

    public void setLoaderGetter (LoaderGetter _loaderGetter) {
        this.loaderGetter = _loaderGetter;
    }
}
