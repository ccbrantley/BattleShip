package battleship.models;

/**MappingPane serves to make a Panes position easier to quantify.
 * It will allow access to various Pane properties.
 * MappingPane will take mapped panes and, based on their aspect ratio
 * and relative position, it will determine their new position and size.
 * @author Area 51 Block Party:
 * Andrew Braswell
 * Christopher Brantley
 * Jacob Schumacher
 * Richard Abrams
 * Last Updated: 09/13/2019
 */

import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class MappingPane extends Pane {

    private ArrayList<MapPane> mappedPanes = new ArrayList();

    /**Default Constructor
     */
    public MappingPane() {
    }

    /**
     * @param _passedPane pane to be added to the MappingPane.
     */
    public void mapToPane(MapPane _passedPane) {
        this.mappedPanes.add(_passedPane);
    }

    /**Logic for calculating the panes added to this MappingPane.
     * @param _stageHeight the stage height (i.e. screen height)
     * @param _stageWidth the stage width (i.e. screen width)
     */
    public void calculatePosition(double _stageHeight, double _stageWidth) {
        double newWidth;
        double newHeight;
        for (MapPane child : this.mappedPanes) {
            Pane curPane = child.getPane();
            double childAspectRatioWidth =  child.getAspectRatioWidth();
            double childAspectRatioHeight = child.getAspectRatioHeight();
            newWidth = (_stageHeight/3);
            newHeight = (_stageWidth/3);
            String childVerticalAlignment = child.getVerticalAlignment();
            String childHorizontalAlignment = child.getHorizontalAlignment();
            if((newWidth/newHeight) > (childAspectRatioWidth/childAspectRatioHeight)) {
                newWidth = newHeight*childAspectRatioHeight;
            }
            else if ((newWidth/newHeight) < (childAspectRatioWidth/childAspectRatioHeight)) {
                newHeight = newWidth*childAspectRatioWidth;
            }
            if(child.getVerticalFillProperty()) {
                newHeight = _stageHeight;
            }
            if(child.getHorizontalFillProperty()) {
                newWidth = _stageWidth;
            }
            if("top".equals(childVerticalAlignment)) {
                curPane.setLayoutY(0);
            }
            else if("middle".equals(childVerticalAlignment)) {
                curPane.setLayoutY(_stageHeight/2-(newHeight/2));
            }
            else if("bottom".equals(childVerticalAlignment)) {
                curPane.setLayoutY(_stageHeight-(newHeight));
            }
            if("left".equals(childHorizontalAlignment)) {
                curPane.setLayoutX(0);
            }
            else if("center".equals(childHorizontalAlignment)) {
                curPane.setLayoutX((_stageWidth/2)-(newWidth/2));
                }
            else if("right".equals(childHorizontalAlignment)) {
                curPane.setLayoutX(_stageWidth-(newWidth));
            }
            curPane.setPrefSize(newWidth, newHeight);
        }
    }

//*****************     GETTERS     *******************

    /**
     * @return the panes assigned to this MappingPane.
     */
    public ArrayList<MapPane> getPanes() {
        return this.mappedPanes;
    }
}
