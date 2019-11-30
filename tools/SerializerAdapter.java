package battleship.tools;


/* @author Area 51 Block Party:
 * Richard Abrams
 * Last Updated: 11/29/2019
 */

import java.util.ArrayList;

public class SerializerAdapter {

    private Serializer serializer = new Serializer();

    /**
     * @param _searchDemarkerIndex
     * Takes in an integer and retrieves the data in a string from the settings file via the serializer.
     * After which it will loop through the string and identify the correct demarker by the number
     * then loop from that position back to get appropriate data.
     * @return saveData
     */
    public String extractData (int _searchDemarkerIndex) {
        int x = 0;
        String saveData = this.serializer.deserialize();
        for (int demarkerCounter = 0; demarkerCounter < saveData.length()-1; demarkerCounter++) {
            if (saveData.charAt(demarkerCounter) == '|') {
                x++;
                if (x == _searchDemarkerIndex) {
                    for (int spaceCounter = demarkerCounter - 2; spaceCounter >= 0; spaceCounter--) {
                        if (saveData.charAt(spaceCounter) == ' ') {
                            saveData = saveData.substring(spaceCounter, demarkerCounter);
                            saveData = saveData.trim();
                            return saveData;
                        }
                    }
                }
            }
        }
        System.out.println("File empty or not found.");
        return " ";
    }

//*****************     SAVING METHODS     *******************

    public void save (Object... _data) {
        for(Object child : _data) {
            this.serializer.serialize(String.valueOf(child));
        }
    }

    public void save (ArrayList<Object> _data) {
        _data.forEach((child) -> {
            this.serializer.serialize(String.valueOf(child));
        });
    }

//*****************     GETTERS     *******************

    public Serializer getSerializer () {
        return this.serializer;
    }

//*****************     SETTERS     *******************

    public void setSerializer (Serializer _serializer) {
        this.serializer = _serializer;
    }

}