package battleship.controllers;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import battleship.models.*;
import com.sun.javafx.webkit.WebConsoleListener;
import java.lang.Thread.State;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jdk.nashorn.api.scripting.JSObject;
/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class FXMLGoogleSignInController implements Initializable {
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    ArrayList<String> userInfo = new ArrayList();
    
 public VBox getScene(){
        webView.getEngine().load("https://ccbrantley.github.io");
        VBox vBox = new VBox(webView);
                com.sun.javafx.webkit.WebConsoleListener.setDefaultListener(
            (test, message, lineNumber, sourceId)-> checkInfo(message));
        return vBox;
 }
 public void checkInfo(String message){
     if(message.contains("post"))
        webView.getEngine().load("https://ccbrantley.github.io/success.html");
     if(message.contains("Success"))
         userInfo.add(message);
 
 }
 public void getInfo(){                      
        for(String x : userInfo){
            System.out.println(x);
        };
 }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
    }    
    
}
