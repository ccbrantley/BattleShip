package battleship.tools;

/** @author Area 51 Block Party:
 * Richard Abrams
 * The Serializer Class stores given data into a text file.
 * Last Updated: 11/27/2019
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    private File setting = new File(Serializer.SAVEFP);
    private String savedInfo;

    //Enumerator -> Save Path.
    public static final String SAVEFP = "settings.ser";

    public Serializer () {
        this.savedInfo = " ";
    }

    //The serialize method takes in any string data, concatenates it with the saved string and then adds it to the settings file.
    public void serialize (String _input) {
        boolean saveSuccesfull = true;
            try (FileOutputStream fileOut = new FileOutputStream(Serializer.SAVEFP);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);) {
                this.savedInfo = this.savedInfo.concat(_input + " | ");
                out.writeObject(this.savedInfo);
            }
            catch (Exception e) {
                saveSuccesfull = false;
                System.out.println(e.toString());
            }
        System.out.println("Saved: " + saveSuccesfull);
    }

    //The deserialize method takes the string from the settings file and returns it.
    public String deserialize () {
        if (this.setting.exists() == true) {
            try (FileInputStream fileIn = new FileInputStream(Serializer.SAVEFP);
                    ObjectInputStream in = new ObjectInputStream(fileIn);) {
                String tempSavedData = (String) in.readObject();
                return tempSavedData;
            }
            catch (Exception e) {
                System.out.println(e.toString());
                return " ";
            }
        }
        else {
            System.out.println("No save file found.");
            return " ";
        }
    }

//*****************     GETTERS     *******************

    public String getSavedInfo () {
        return this.savedInfo;
    }

//*****************     SETTERS     *******************

    public void setSavedInfo (String savedInfo) {
        this.savedInfo = savedInfo;
    }

}