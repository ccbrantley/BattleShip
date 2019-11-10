package battleship.tools;

/**
 * @author Christopher Brantley
 * @email c_brantl@uncg.edu
 * Last Updated : 10/20/2019
 */

import battleship.models.ChatBox;
import java.io.DataInputStream;

public class InputThread implements Runnable {

    // Input thread is required since input must be continuous stream
    public InputThread(DataInputStream _inputStream, ChatBox _chatBox, BattleshipClient _clientServer) {
        this.inputStream = _inputStream;
        this.chatBox = _chatBox;
        this.clientServer = _clientServer;
    }

    DataInputStream inputStream;
    ChatBox chatBox;
    String curMessage;
    BattleshipClient clientServer;

    @Override
    public void run() {
            try {
                String tempString;
                while((tempString = this.inputStream.readUTF()) != null) {
                    this.clientServer.setCurrentMessage(tempString);
                }
            } catch (Exception e) {
                System.err.println(e.toString());
                System.exit(1);
            }
    }
}