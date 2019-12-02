package battleship.tools;

/* @author Area 51 Block Party:
 * Richard Abrams, Christopher Brantley
 * This class can take a string and save it to a file or load what is stored in said file.
 * Last Updated: 12/2/2019
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

    //Tis method is a constructor.
    public Serializer () {
        this.savedInfo = " ";
    }

    //Save a given string to the settings file by concatenating to the save string and sending it to the file.
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

    // Loads a string from the settings file and returns it.
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