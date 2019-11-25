package battleship.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* @author Area 51 Block Party:
 * Richard Abrams
 * Last Updated: 11/25/2019
 */
public class Serializer {

    private File setting = new File("Settings.ser");
    private String savedInfo;

    public Serializer() {
        savedInfo = " ";
    }

    public void serialize(String _input) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Settings.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            savedInfo = savedInfo.concat(_input + " | ");
            out.writeObject(savedInfo);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in Settings.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    public String deserialize() {
        if (setting.exists() == true) {
            try {
                FileInputStream fileIn = new FileInputStream("Settings.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                String tempSavedData = (String) in.readObject();
                in.close();
                fileIn.close();
                return tempSavedData;
            } catch (IOException i) {
                i.printStackTrace();
                return " ";
            } catch (ClassNotFoundException c) {
                System.out.println("Student class not found");
                c.printStackTrace();
                return " ";
            }

        } else {
            System.out.println("No save file found.");
            return " ";
        }
    }

    public String getSavedInfo() {
        return savedInfo;
    }

    public void setSavedInfo(String savedInfo) {
        this.savedInfo = savedInfo;
    }
}
