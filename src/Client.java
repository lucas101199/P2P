import Peers.Peers;
import Torrent.Torrent;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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

    public Map<Integer, Integer> request(int peer_id, int port, int url, Torrent torrent) throws Exception {
        socket = new Socket("localhost", url);
        System.out.println("Connected");

        // send request
        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        pr.println(peer_id + ";" + port + ";" + Arrays.toString(torrent.gethash()));
        pr.flush();

        Thread.sleep(3000);
        // sends output to the socket
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println(str);
        Map<Integer, Integer> port_peer = new HashMap<>();
        String[] ss = str.split(":");
        port_peer.put(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]));
        System.out.println(port_peer);
        return port_peer;
    }

}
