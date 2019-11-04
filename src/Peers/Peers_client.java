package Peers;

import java.io.*;
import java.net.Socket;

public class Peers_client {

    Socket talkSocket;
    Peers peer;

    public Peers_client(Socket _talkSocket, Peers peers)
    {
        talkSocket = _talkSocket;
        peer = peers;
    }

    public void run()
    {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = talkSocket.getInputStream();
            String request = inputStreamToString(input);

            output = talkSocket.getOutputStream();

            input.close();
        } catch (IOException e) {
            throw new RuntimeException(
                    "Cannot talk.\nServer port: " + peer.getAddress().getPort() + "; Talker port: " + talkSocket.getPort(), e);
        }
    }

    public static String inputStreamToString (InputStream in) {

        // invalid input
        if (in == null) {
            return null;
        }

        // try to read line from input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println("Cannot read line from input stream.");
            return null;
        }

        return line;
    }
}
