package battleship.tools;

/* @author Area 51 Block Party:
 * Richard Abrams
 * Last Updated: 11/25/2019
 */

public class SerializerInterface {

    private Serializer serializer = new Serializer();

    /**
     * @param _searchDemarkerIndex
     * Takes in an integer and retrieves the data in a string from the settings file via the serializer.
     * After which it will loop through the string and identify the correct demarker by the number
     * then loop from that position back to get appropriate data
     * @return saveData
     */
    
    public String extractData(int _searchDemarkerIndex) {
        int x = 0;
        String saveData = this.serializer.deserialize();
        for (int i = 0; i < saveData.length()-1; i++) {
            if (saveData.charAt(i) == '|') {
                x++;
                if (x == _searchDemarkerIndex) {
                    for (int k = i - 2; k >= 0; k--) {
                        if (saveData.charAt(k) == ' ') {
                            saveData = saveData.substring(k, i);
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
    
    public void saveString(String _dataToBeSaved){
        this.serializer.serialize(_dataToBeSaved);
    }
    
    public void saveDouble(double _dataToBeSaved){
        this.serializer.serialize(String.valueOf(_dataToBeSaved));
    }
    
    public void saveInt(int _dataToBeSaved){
        this.serializer.serialize(String.valueOf(_dataToBeSaved));
    }
    
    public void saveFloat(float _dataToBeSaved){
        this.serializer.serialize(String.valueOf(_dataToBeSaved));
    }
    
    public void saveBoolean(boolean _dataToBeSaved){
        this.serializer.serialize(String.valueOf(_dataToBeSaved));
    }

//*****************     GETTERS     *******************
    
    public Serializer getSerializer() {
        return this.serializer;
    }

//*****************     SETTERS     *******************
    
    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }   
}