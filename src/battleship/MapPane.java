package battleship;

/**MapPane serves to make a Panes position on a parent Pane easier to quantify.
 * It will allow access to various Pane properties through their Type.
 * It is a requirement that these Panes be stored with a relative position.
 * It is a requirement that these Panes be stored with a aspect ratio which, if not
 * explicitly set, will automatically be determined from their preferred width and
 * preferred height.
 * @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 09/13/2019
 */

import javafx.scene.layout.Pane;

public class MapPane extends Pane {
    private Pane mappedPane;
    private String verticalAlignment;
    private String horizontalAlignment;
    private double aspectWidth;
    private double aspectHeight;
    private boolean fillVertical;
    private boolean fillHorizontal;

    /**MapPane is a constructor that takes a pane and a relative position
     * and stores them together. This constructor will calculate an aspect ratio
     * based on the Pane's preferred width and size.
     * @param _passedPane accepts an subclass of Pane.
     * @param _verticalAlignment top, middle, bottom
     * @param _horizontalAlignment left, center, right
     * @param _fillVertical: fill the vertical alignment
     * @param _fillHorizontal: fill the horizontal alignment
     */
    public MapPane(Pane _passedPane, String _verticalAlignment, String _horizontalAlignment, boolean _fillVertical, boolean _fillHorizontal) {
        this.mappedPane = _passedPane;
        this.verticalAlignment = _verticalAlignment;
        this.horizontalAlignment = _horizontalAlignment;
        this.aspectWidth = _passedPane.getPrefWidth()/_passedPane.getPrefHeight();
        this.aspectHeight = _passedPane.getPrefHeight()/_passedPane.getPrefWidth();
        this.fillVertical = _fillVertical;
        this.fillHorizontal = _fillHorizontal;
    }

    /** MapPane is a constructor that takes a pane, a relative position
     *  and and aspect ratio and stores them together.
     * @param _passedPane: accepts an subclass of Pane.
     * @param _verticalAlignment: top, middle, bottom
     * @param _horizontalAlignment: left, center, right
     * @param _aspectWidth: the proportional relationship between width
     * @param _aspectHeight: the proportional relationship between height
     * @param _fillVertical: fill the vertical alignment
     * @param _fillHorizontal: fill the horizontal alignment
     */
    public MapPane(Pane _passedPane, String _verticalAlignment, String _horizontalAlignment, double _aspectWidth, double _aspectHeight, boolean _fillVertical, boolean _fillHorizontal) {
        this.mappedPane = _passedPane;
        this.verticalAlignment = _verticalAlignment;
        this.horizontalAlignment = _horizontalAlignment;
        this.aspectWidth = _aspectHeight;
        this.aspectHeight = _aspectWidth;
        this.fillVertical = _fillVertical;
        this.fillHorizontal = _fillHorizontal;
    }

//*****************     GETTERS     *******************

    /**
     * @return Returns the pane assigned to this MapPane.
     */
    public Pane getPane() {
        return this.mappedPane;
    }

    /**
     * @return the aspect ratio for width
     */
    public double getAspectRatioWidth() {
        return this.aspectWidth;
    }

    /**
     * @return the aspect ratio for height
     */
    public double getAspectRatioHeight() {
        return this.aspectHeight;
    }

    /**
     * @return the vertical alignment(top,middle,bottom).
     */
    public String getVerticalAlignment() {
        return this.verticalAlignment;
    }

    /**
     * @return the horizontal alignment(left,center,bottom)
     */
    public String getHorizontalAlignment() {
        return this.horizontalAlignment;
    }
    
    public boolean getVerticalFillProperty(){
        return this.fillVertical;
    }
    public boolean getHorizontalFillProperty(){
        return this.fillHorizontal;
    }
}
