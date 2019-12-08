package battleship.tools;

/* @author Area 51 Block Party:
 * Richard Abrams
 * Last Updated: 12/8/2019
 * A translator class that serves as a passthrough for saving thus creating
 * a reconnectable buffer that can be quickly editted if saving procedures were to change.
 */

import java.util.ArrayList;

public class SavingTranslator implements SavingInterface {

    private SerializerAdapter serializerAdapter = new SerializerAdapter();

    @Override
    public void saveList(ArrayList<Object> _data) {
        this.serializerAdapter.save(_data);
    }

    @Override
    public void saveObject(Object... _data) {
        this.serializerAdapter.save(_data);
    }

    @Override
    public String load(int _searchDemarkerIndex) {
        return this.serializerAdapter.extractData(_searchDemarkerIndex);
    }

}
