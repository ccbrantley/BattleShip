
package battleship.models;

import battleship.tools.BattleshipClient;
import battleship.tools.ResourceGetter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ChatBox {
    public ChatBox() {
        this.initializeInputBox();
        this.initializeChatBox();
        this.fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        this.fiveSecondsWonder.play();
    }
    private AnchorPane parentPane = new AnchorPane();
    private ScrollPane scrollPane = new ScrollPane();
    private VBox messageArea = new VBox();
    private HBox inputBox = new HBox();
    private Button sendMessageButton = new Button("Send");
    private TextField inputTextField = new TextField();
    private ResourceGetter resourceGetter = new ResourceGetter();
    private String currentMessage = "";
    private String lastMessaage = "";
    private Label testLabel = new Label();
    private BattleshipClient battleshipClient;
    Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(.5), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            String test = ChatBox.this.battleshipClient.getCurrentMessage();
            if(!(test.equals(ChatBox.this.lastMessaage))){
                ChatBox.this.displayMessage(ChatBox.this.battleshipClient.getCurrentMessage());
                ChatBox.this.lastMessaage = test;
            }
        }
    }));

    private void initializeChatBox() {
        this.messageArea.setPrefHeight(0);
        this.scrollPane.setContent(this.messageArea);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.messageArea.heightProperty().addListener(obs -> this.scrollPane.setVvalue(1D));
        this.parentPane.getChildren().add(this.scrollPane);
        this.parentPane.getStylesheets().add(this.resourceGetter.getChatBoxCSS());
        AnchorPane.setTopAnchor(this.scrollPane, 0.0);
        AnchorPane.setLeftAnchor(this.scrollPane, 4.0);
        AnchorPane.setBottomAnchor(this.scrollPane, this.inputBox.getMinHeight());
        AnchorPane.setRightAnchor(this.scrollPane, 0.0);
    }

    private void initializeInputBox() {
        this.sendMessageButtonOnAction(this.sendMessageButton);
        this.inputTextField.setOnKeyPressed(_event -> {this.sendMessageButtonOnActionDefault(_event);});
        this.inputBox.getChildren().addAll(this.sendMessageButton, this.inputTextField);
        this.inputBox.setMinHeight(28);
        HBox.setHgrow(this.inputTextField, Priority.ALWAYS);
        this.parentPane.getChildren().add(this.inputBox);
        AnchorPane.setBottomAnchor(this.inputBox, 0.0);
        AnchorPane.setLeftAnchor(this.inputBox, 4.0);
        AnchorPane.setRightAnchor(this.inputBox,0.0);
    }

    public void displayMessage(String _message) {
        Label newMessage = new Label("Player 2: " + _message);
        this.messageArea.getChildren().add(new Label("Player 2: " + _message));
    }

//*****************     EVENTS     *******************

    public void sendMessageButtonOnAction(Button _button) {
        _button.setOnAction((ActionEvent _event) -> {
            String playerMessage = this.inputTextField.getText();
            if(!(playerMessage.equals(""))){
                Label newMessage = new Label("Player 1: " + playerMessage);
                this.messageArea.getChildren().add(newMessage);
                this.inputTextField.clear();
                try {
                    this.battleshipClient.sendMessage(playerMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void sendMessageButtonOnActionDefault(KeyEvent _event) {
        if(_event.getCode().equals(KeyCode.ENTER)) {
            this.sendMessageButton.fire();
        }
    }

//*****************     GETTERS     *******************

    public AnchorPane getChatBox() {
        return this.parentPane;
    }

    public String getCurrentMessage() {
    return this.currentMessage;
    }

//*****************     SETTERS     *******************

    public void setCurrentMessage(String _message) {
        this.currentMessage = _message;
    }

    public void setLocation(double _x, double _y) {
        this.parentPane.relocate(_x, _y);
    }

    public void setSize(double _width, double _height) {
        this.parentPane.setMinWidth(_width);
        this.parentPane.setMinHeight( _height);
    }
    public void setBattleshipClient (BattleshipClient _battleshipClient) {
        this.battleshipClient = _battleshipClient;
    }

}
