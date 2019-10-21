package battleship.models;

import java.io.File;

/**
 * @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 10/09/2019
 */
public class ResourceGetter {

    public ResourceGetter(){}

    public String getBlueButtonCSS() {

        File styleSheet = new File("src/assets/stylesheets/bluebutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getYellowButtonCSS() {
        File styleSheet = new File("src/assets/stylesheets/yellowbutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getRedButtonCSS() {
        File styleSheet = new File("src/assets/stylesheets/redbutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getBattleShipCSS() {
        File styleSheet = new File("src/assets/stylesheets/battleship.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getTopGridCSS() {
        File styleSheet = new File("src/assets/stylesheets/topgrid.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getBottomGridCSS() {
        File styleSheet = new File("src/assets/stylesheets/bottomgrid.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getCarrierCSS() {
        File styleSheet = new File("src/assets/stylesheets/carrier.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getFxmlGoogleSignInCSS() {
        File styleSheet = new File("src/assets/stylesheets/fxmlgooglesign.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getFxmlPlayCSS() {
        File styleSheet = new File("src/assets/stylesheets/fxmlplay.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getFxmlResumeCSS() {
        File styleSheet = new File("src/assets/stylesheets/fxmlresume.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getFxmlSettingsCSS() {
        File styleSheet = new File("src/assets/stylesheets/fxmlsettings.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getMainMenuCSS() {
        File styleSheet = new File("src/assets/stylesheets/mainmenu.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

}
