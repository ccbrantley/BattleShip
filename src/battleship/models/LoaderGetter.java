package battleship.models;
/*
 * @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated 09/30/2019
 */

import battleship.Controllers.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoaderGetter {
    private FXMLLoader mainLoader;
    private FXMLLoader playLoader;
    private FXMLLoader resumeLoader;
    private FXMLLoader settingsLoader;
    private Parent mainRoot;
    private Parent playRoot;
    private Parent resumeRoot;
    private Parent settingsRoot;

    public LoaderGetter() {
    }

    public void addLoader(FXMLLoader _loader, String _type) throws IOException {
        if(_type.contains("Main")) {
            this.mainLoader = _loader;
            this.mainRoot = _loader.load();
        }
        if(_type.contains("Play")) {
            this.playLoader = _loader;
            this.playRoot = _loader.load();

        }
        if(_type.contains("Resume")) {
            this.resumeLoader = _loader;
            this.resumeRoot = _loader.load();
        }
        if(_type.contains("Settings")) {
            this.settingsLoader = _loader;
            this.settingsRoot = _loader.load();
        }
    }

//*****************     GETTERS     *******************

    public FXMLLoader getMainLoader() {
        return this.mainLoader;
    }

    public FXMLLoader getPlayLoader() {
        return this.playLoader;
    }

    public FXMLLoader getResumeLoader() {
        return this.resumeLoader;
    }

    public FXMLLoader getSettingsLoader() {
        return this.settingsLoader;
    }
    public ArrayList getAllLoader() {
        ArrayList loaderArray = new ArrayList();
        loaderArray.add(this.mainLoader);
        loaderArray.add(this.playLoader);
        loaderArray.add(this.resumeLoader);
        loaderArray.add(this.settingsLoader);
        return loaderArray;
    }

    public Parent getMainRoot() {
        return this.mainRoot;
    }

    public Parent getPlayRoot() {
        return this.playRoot;
    }

    public Parent getResumeRoot() {
        return this.resumeRoot;
    }

    public Parent getSettingsRoot() {
        return this.settingsRoot;
    }

    public FXMLMainController getMainController() {
        return this.mainLoader.getController();
    }

    public FXMLPlayController getPlayController() {
        return this.playLoader.getController();
    }

    public FXMLResumeController getResumeController() {
        return (FXMLResumeController)this.resumeLoader.getController();
    }

    public FXMLSettingsController getSettingsController() {
        return (FXMLSettingsController)this.settingsLoader.getController();
    }

}
