package battleship.tools;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated: 10/30/2019
 *
 * ViewAssets is used to force consistency in creation of nodes by allowing consistent property changes.
 * ViewAssetse purpose is to also maintain commonly used assets so that they may be
 * recreated with consistency.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ViewAssets {
    public ViewAssets() {}

    //liveAssets keeps track of all created nodes by id and will throw exceptions for repeated nodes
    private HashMap liveAssets = new HashMap();
    private int GRIDSIZE = 10;

    public Label createLabel(String _id, String _text, double _width, double _height, boolean _wrapText) {
        Label curLabel = new Label();
        curLabel.setId(_id);
        curLabel.setText(_text);
        curLabel.setMinWidth(_width);
        curLabel.setMinHeight(_height);
        curLabel.setMaxWidth(Double.MAX_VALUE);
        curLabel.setWrapText(true);
        this.addToLiveAssets(curLabel);
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

    public Button createButton(String _id, String _text, double _height) {
        Button curButton = new Button();
        curButton.setId(_id);
        curButton.setText(_text);
        curButton.setMaxWidth(Double.MAX_VALUE);
        curButton.setMinHeight(_height);
        return curButton;
    }

    public Button createButton(String _id, String _text, double _width, double _height) {
        Button curButton = new Button();
        curButton.setId(_id);
        curButton.setText(_text);
        curButton.setMinWidth(_width);
        curButton.setMinHeight(_height);
        curButton.setMaxWidth(_width);
        curButton.setMaxHeight(_height);
        curButton.setWrapText(true);
        this.addToLiveAssets(curButton);
        return curButton;
    }

    public VBox createVBox(ArrayList<Node> _children, double _inset, String _id, double _boxWidth, double _boxHeight) {
        VBox curVBox = new VBox();
        _children.forEach(child -> {
            VBox.setMargin(child, new Insets(_inset));
            VBox.setVgrow(child, Priority.ALWAYS);
            curVBox.getChildren().add(child);
        });
        curVBox.setId(_id);
        curVBox.setMinWidth(_boxWidth);
        curVBox.setMinHeight(_boxHeight);
        this.addToLiveAssets(curVBox);
        return curVBox;
    }

    public GridPane createRowByColumnPane(int _row, int _column, String _buttonId, String _buttonText, double _paneWidth, double _paneHeight) {
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
        this.addToLiveAssets(curPane);
        return curPane;
    }

    public AnchorPane createAnchorPane(String _id, String _path) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId(_id);
        anchorPane.getStylesheets().add(_path);
        this.addToLiveAssets(anchorPane);
        return anchorPane;
    }

    public Slider createSlider(String _id, double _width, double _height, double _min, double _max, double _value, double _increment, double _major, int _minor, boolean _snap) {
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
        this.addToLiveAssets(curSlider);
        return curSlider;
    }

    public ComboBox createComboBox(String _id, double _width, double _height, String _prompt, ObservableList _items) {
        ComboBox curComboBox = new ComboBox();
        curComboBox.setId(_id);
        curComboBox.setMinWidth(_width);
        curComboBox.setMinHeight(_height);
        curComboBox.setPromptText(_prompt);
        curComboBox.setItems(_items);
        this.addToLiveAssets(curComboBox);
        return curComboBox;
    }

    // Adds the passed node to the liveAssets hashmap
    // Keeps track of created nodes for each view
    private void addToLiveAssets(Node _curNode) {
        try {
            if(!(this.liveAssets.get(_curNode.getClass().toString()) == null)) {
                if((((HashMap)(this.liveAssets.get(_curNode.getClass().toString()))).get(_curNode.getId()) == null)) {
                    ((HashMap)(this.liveAssets.get(_curNode.getClass().toString()))).put(_curNode.getId(), _curNode);
                }
                else {
                    throw new RuntimeException("Asset Duplicated " + _curNode.getId() + ".");
                }
            }
            else {
                HashMap tempMap = new HashMap();
                tempMap.put(_curNode.getId(), _curNode);
                this.liveAssets.put(_curNode.getClass().toString(), tempMap);
            }
        }
        catch (RuntimeException exception){
            System.out.println(exception.toString());
        }
    }

//*****************     GETTERS     *******************

    // Returns array list of all created assets
    public ArrayList<Node> getArrayListOfLiveAssets() {
        ArrayList<Node> allNodeArrayList = new ArrayList();
        Set hashSet = this.liveAssets.keySet();
        hashSet.forEach(key -> {
            allNodeArrayList.add((Node)this.liveAssets.get(key));
        });
        return allNodeArrayList;
    }

    // Returns object from liveAssets by _id
    public Object getFromLiveAssets(String _id) {
        return this.liveAssets.get(_id);
    }
}
