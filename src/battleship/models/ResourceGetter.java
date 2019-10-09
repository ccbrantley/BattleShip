package battleship.models;

import java.io.File;
import sun.plugin.dom.stylesheets.StyleSheet;

/**
 * @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/09/2019
 */
public class ResourceGetter {
    
    public String getBlueButtonCSS() {
        File styleSheet = new File("src/assets/bluebutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    public String getYellowButtonCSS() {
        File styleSheet = new File("src/assets/yellowbutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    public String getRedButtonCSS() {
        File styleSheet = new File("src/assets/redbutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    public String getBattleShipCSS() {
        File styleSheet = new File("src/assets/battleship.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    public String getBottomGridCSS() {
        File styleSheet = new File("src/assets/bottomGrid.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    public String getCarrierCSS() {
        File styleSheet = new File("src/assets/carrier.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    public String getFxmlGoogleSignInCSS() {
        File styleSheet = new File("src/assets/fxmlgooglesign.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    public String getFxmlPlayCSS() {
        File styleSheet = new File("src/assets/fxmlplay.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    public String getFxmlResumeCSS() {
        File styleSheet = new File("src/assets/fxmlresume.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    public String getFxmlSettingsCSS() {
        File styleSheet = new File("src/assets/fxmlsettings.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    public String getMainMenuCSS() {
        File styleSheet = new File("src/assets/mainmenu.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/");
    }
    
    
}
