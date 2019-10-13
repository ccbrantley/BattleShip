package battleship.controllers;

/* @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/12/2019
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class FXMLResumeController implements Initializable {

    public FXMLResumeController () {
        this.resumeControllerLogic = new ResumeControllerLogic(this);
    }

    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
    }

    private final ResumeControllerLogic resumeControllerLogic;
    @FXML
    private GridPane mainGridPane;

    @FXML
    public void returnMainMenu(ActionEvent _event) throws IOException {
        this.resumeControllerLogic.returnMainMenu(_event);
    }

//*****************     SETTERS     *******************

//*****************     GETTERS     *******************

    public GridPane getMainGridPane() {
        return this.mainGridPane;
    }

    public ResumeControllerLogic getLogic() {
        return this.resumeControllerLogic;
    }
}
