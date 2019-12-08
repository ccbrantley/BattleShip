package battleship.tools;

/* @author Area 51 Block Party:
 * Richard Abrams
 * Last Updated: 12/8/2019
 * A translator class that serves as a passthrough for saving thus creating
 * a reconnectable buffer that can be quickly editted if saving procedures were to change.
 */

import java.util.ArrayList;

public class SavingTranslator implements SavingInterface {

    private SavingInterface serializerAdapter = new SerializerAdapter();

    @Override
    public void saveList (ArrayList<Object> _data) {
        this.serializerAdapter.saveList(_data);
    }

    @Override
    public void saveObject (Object... _data) {
        this.serializerAdapter.saveObject(_data);
    }

    @Override
    public String load(int _searchDemarkerIndex) {
        return this.serializerAdapter.load(_searchDemarkerIndex);
    }

}