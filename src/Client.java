import Torrent.Torrent;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Client {

    int peer_id;
    int port;
    Torrent torrent;
    int dowloaded;
    DataInputStream input;
    DataOutputStream out;
    Socket socket;

    public Client(int peer_id, int port)  {
        this.peer_id = peer_id;
        this.port = port;
    }

    public URL getUrl(Torrent torrent) {
        return torrent.getTrackerURL();
    }

    public void request(int peer_id, int port, int url, Torrent torrent) throws Exception {
        socket = new Socket("localhost", url);
        System.out.println("Connected");

        // send request
        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        pr.println(peer_id + ";" + port + ";" + Arrays.toString(torrent.gethash()));
        pr.flush();

        // sends output to the socket
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println(str);
    }
}
