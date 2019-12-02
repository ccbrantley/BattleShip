package battleship.tools;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/30/2019
 * ViewAssets is used to force consistent property changes
 * in javaFX nodes.
 */

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ViewAssets {

    public ViewAssets() {
    }

    //Enumerators -> LED
    public static final String NULLLED = "";
    public static final String BLUELED = "blue";
    public static final String YELLOWLED = "yellow";
    public static final String REDLED = "red";
    public static final String REDLEDACTIVE = "redActive";

    //Enumerators -> Scene Selection
    public static final String MAIN = "main";
    public static final String SETTINGS = "settings";
    public static final String SHIPSELECTION = "shipSelection";
    public static final String GAMETYPE = "gameTypeSelection";
    public static final String PLAY = "play";

    //Enumerators -> File Path
    public static final String SHIPIMAGES = "src/battleship/assets/images/ship/";

    //Enumerators -> File Extension
    public static final String SHIPIMAGEEXTENSION= ".png";


    public static Label createLabel(String _id, String _text, boolean _wrapText) {
        Label curLabel = new Label();
        curLabel.setId(_id);
        curLabel.setText(_text);
        curLabel.setWrapText(true);
        return curLabel;
    }

    public static Label createLabel(String _id, String _text, double _width, double _height, boolean _wrapText) {
        Label curLabel = new Label();
        curLabel.setId(_id);
        curLabel.setText(_text);
        curLabel.setMinWidth(_width);
        curLabel.setMinHeight(_height);
        curLabel.setMaxWidth(Double.MAX_VALUE);
        curLabel.setWrapText(true);
        return curLabel;
    }

    public static Button createGridButton(String _id, double _rotation, String _text) {
        Button curButton = new Button();
        curButton.setId(_id);
        curButton.setRotate(_rotation);
        curButton.setText(_text);
        curButton.setMaxWidth(Double.MAX_VALUE);
        curButton.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(curButton, Priority.ALWAYS);
        GridPane.setHgrow(curButton, Priority.ALWAYS);
        GridPane.setFillHeight(curButton, Boolean.TRUE);
        GridPane.setFillWidth(curButton, Boolean.TRUE);
        return curButton;
    }

    public static Button createButton(String _id, String _text, double _height) {
        Button curButton = new Button();
        curButton.setId(_id);
        curButton.setText(_text);
        curButton.setMaxWidth(Double.MAX_VALUE);
        curButton.setMinHeight(_height);
        return curButton;
    }

    public static Button createButton(String _id, String _text, double _width, double _height) {
        Button curButton = new Button();
        curButton.setId(_id);
        curButton.setText(_text);
        curButton.setMinWidth(_width);
        curButton.setMinHeight(_height);
        curButton.setMaxWidth(_width);
        curButton.setMaxHeight(_height);
        curButton.setWrapText(true);
        return curButton;
    }

    public static VBox createVBox(String id) {
        VBox curVBox = new VBox();
        curVBox.setId(id);
        return curVBox;
    }

    public static VBox createVBox(ArrayList<Node> _children, double _inset, String _id, double _boxWidth, double _boxHeight) {
        VBox curVBox = new VBox();
        _children.forEach(child -> {
            VBox.setMargin(child, new Insets(_inset));
            VBox.setVgrow(child, Priority.ALWAYS);
            curVBox.getChildren().add(child);
        });
        curVBox.setId(_id);
        curVBox.setMinWidth(_boxWidth);
        curVBox.setMinHeight(_boxHeight);
        return curVBox;
    }

    public static HBox createHBox(ArrayList<Node> _children, double _inset, String _id, double _boxWidth, double _boxHeight) {
        HBox curVBox = new HBox();
        _children.forEach(child -> {
            VBox.setMargin(child, new Insets(_inset));
            VBox.setVgrow(child, Priority.ALWAYS);
            curVBox.getChildren().add(child);
        });
        curVBox.setId(_id);
        curVBox.setMinWidth(_boxWidth);
        curVBox.setMinHeight(_boxHeight);
        return curVBox;
    }

    public static GridPane createRowByColumnPane(int _row, int _column, String _buttonId, String _buttonText, double _paneWidth, double _paneHeight) {
        int paneWidth = (int) _paneWidth;
        int paneHeight = (int) _paneHeight;
        GridPane curPane = new GridPane();
        for (int row = 0; row < _row; row ++) {
            for (int column = 0; column < _column; column++) {
                curPane.add(ViewAssets.createGridButton(_buttonId, 0 , ""), row, column);
            }
        }
        while((paneWidth % _row) != 0) {
           --paneWidth;
        }
        while((paneHeight % _column) != 0) {
           --paneHeight;
        }
        curPane.setMinWidth(paneWidth);
        curPane.setMinHeight(paneHeight);
        curPane.setMaxWidth(paneWidth);
        curPane.setMaxHeight(paneHeight);
        return curPane;
    }

    public static AnchorPane createAnchorPane(String _id, String _path) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId(_id);
        anchorPane.getStylesheets().add(_path);
        return anchorPane;
    }

    public static Slider createSlider(String _id, double _width, double _height, double _min, double _max, double _value, double _increment, double _major, int _minor, boolean _snap) {
        Slider curSlider = new Slider();
        curSlider.setId(_id);
        curSlider.setMinWidth(_width);
        curSlider.setMinHeight(_height);
        curSlider.setMin(_min);
        curSlider.setMax(_max);
        curSlider.setValue(_value);
        curSlider.setBlockIncrement(_increment);
        curSlider.setMajorTickUnit(_major);
        curSlider.setMinorTickCount(_minor);
        curSlider.setSnapToTicks(_snap);
        curSlider.setShowTickMarks(true);
        return curSlider;
    }

    public static ComboBox createComboBox(String _id, double _width, double _height, String _prompt, ObservableList _items) {
        ComboBox curComboBox = new ComboBox();
        curComboBox.setId(_id);
        curComboBox.setMinWidth(_width);
        curComboBox.setMinHeight(_height);
        curComboBox.setPromptText(_prompt);
        curComboBox.setItems(_items);
        return curComboBox;
    }

    public static ScrollPane createMessageScrollPane(VBox messageArea, double _width, double _height) {
        ScrollPane scrollPane = new ScrollPane();
        messageArea.setPrefHeight(0);
        scrollPane.setMinWidth(_width);
        scrollPane.setMinHeight(_height);
        scrollPane.setContent(messageArea);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        messageArea.heightProperty().addListener(obs -> scrollPane.setVvalue(1D));
        return scrollPane;
    }

//*****************     GETTERS     *******************

//*****************     GETTERS     *******************
}
