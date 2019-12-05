package battleship.tools;

/* @author Area 51 Block Party:
 * Richard Abrams, Christopher Brantley
 * This class can take a string and save it to a file or load what is stored in said file.
 * Last Updated: 12/2/2019
 */

import battleship.models.Animator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serializer {

    //Enumerators
    public static final char DEMARKER  = '|';
    public static final char SPACE = ' ';

    private File setting = new File(Serializer.SAVEFP);
    private String savedInfo;

    //Enumerator -> Save Path.
    public static final String SAVEFP = "settings.ser";

    //This method is a constructor.
    public Serializer () {
        this.savedInfo = Character.toString(Serializer.SPACE);
    }

    //Save a given string to the settings file by concatenating to the save string and sending it to the file.
    public void serialize (String _input) {
        boolean saveSuccesfull = true;
            try (FileOutputStream fileOut = new FileOutputStream(Serializer.SAVEFP);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);) {
                this.savedInfo = this.savedInfo.concat(_input + Serializer.SPACE + Serializer.DEMARKER + Serializer.SPACE);
                out.writeObject(this.savedInfo);
            }
            catch (Exception e) {
                saveSuccesfull = false;
                Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, e);
            }
    }

    // Loads a string from the settings file and returns it.
    public String deserialize () {
            try (FileInputStream fileIn = new FileInputStream(Serializer.SAVEFP);
                    ObjectInputStream in = new ObjectInputStream(fileIn);) {
                String tempSavedData = (String) in.readObject();
                return tempSavedData;
            }
            catch (Exception e) {
                Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, e);
                return Character.toString(Serializer.SPACE);
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