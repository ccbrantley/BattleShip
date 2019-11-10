package battleship.tools;

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

    public String getChatBoxCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/chatbox.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getBlueButtonCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/bluebutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getYellowButtonCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/yellowbutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getRedButtonCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/redbutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getBattleShipCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/battleship.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getTopGridCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/topgrid.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getBottomGridCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/bottomgrid.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getCarrierCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/carrier.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }


    public String getPlayMenuCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/fxmlplay.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getShipSelectionCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/shipselection.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getSettingsMenuCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/settingsmenu.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getMusicPlayerCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/musicplayer.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getGraphicEffectCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/graphiceffect.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public String getMainMenuCSS() {
        File styleSheet = new File("src/battleship/assets/stylesheets/mainmenu.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

}
