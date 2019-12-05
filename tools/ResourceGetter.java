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

    // Enumerators -> FilePath
    public static String RESOURCEPATH = "src/battleship/assets/stylesheets/";

    public static String getChatBoxCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "chatbox.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getBlueButtonCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH +"bluebutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getYellowButtonCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "yellowbutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getRedButtonCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "redbutton.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getBattleShipGameCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "battleshipgame.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getTopGridCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "topgrid.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getBottomGridCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "bottomgrid.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getCarrierCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "carrier.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getShipSelectionCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "shipselection.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getSettingsMenuCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "settingsmenu.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getMusicPlayerCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "musicplayer.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getGraphicEffectCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "graphiceffect.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getMainMenuCSS () {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "mainmenu.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

    public static String getGameTypeSelectionCSS() {
        File styleSheet = new File(ResourceGetter.RESOURCEPATH + "gametypeselection.css");
        return "file:///" + styleSheet.getAbsolutePath().replace("\\", "/").replaceAll("\\u0020", "%20");
    }

}