package battleship.tools;

/* @author Area 51 Block Party:
 * Richard Abrams
 * Last Updated: 12/8/2019
 * An interface class to serve as a template for an saving translator class.
 */

import java.util.ArrayList;

public interface SavingInterface {

    //Methods to save and load data from a source.
    public void saveList (ArrayList<Object> _data);

    public void saveObject (Object... _data);

    public String load (int _searchDemarkerIndex);

}