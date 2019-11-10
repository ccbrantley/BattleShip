package battleship.tools;

/**
 * @author Christopher Brantley
 * @email c_brantl@uncg.edu
 * Last Updated : 10/20/2019
 */

import battleship.models.ChatBox;
import battleship.tools.InputThread;
import java.io.*;
import java.net.*;
import javafx.application.Platform;

public class BattleshipClient implements Runnable {
    public BattleshipClient(ChatBox _chatBox) {
        this.chatBox = _chatBox;
    }
    ChatBox chatBox;
    String[] server = {"169.254.31.203","23"};
    String hostName = server[0];
    Integer portNumber = Integer.parseInt(server[1]);
    String currentMessage = "";
    Socket clientSocket;
    DataOutputStream out;
    DataInputStream in;
    BufferedReader stdIn;
    @Override
    public void run() {
        try (
                Socket clientSocket = new Socket(this.hostName, this.portNumber);
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
                )
        {
            this.clientSocket = clientSocket;
            this.out = out;
            this.in = in;
            this.stdIn = stdIn;
            new Thread(new InputThread(in, this.chatBox, this)).start();
            //Platform.runLater(new InputThread(in, this.chatBox){});
            String tempString;
            do {
                if(((tempString) = stdIn.readLine()) != null) {
                    out.writeUTF(tempString);
                }
            } while (true);
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }

    public String getCurrentMessage() {
        return this.currentMessage;
    }

    public void sendMessage(String _message) throws IOException {
        this.out.writeUTF(_message);
    }

    public void setCurrentMessage(String _string) {
        this.currentMessage = _string;
    }
}
