package battleship.Controllers;

/*
 * @author Area 51 Block Party: 
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 */

import battleship.LoaderGetter;
import battleship.MapPane;
import battleship.MappingPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class FXMLResumeController implements Initializable {
    private LoaderGetter loaderGetter;
    
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
    }    
    
//*****************     SETTERS     *******************
    
    public void setLoaderGetter(LoaderGetter _loaderGetter){
        this.loaderGetter = _loaderGetter;
    }
    
//*****************     GETTERS     *******************
    
}
