import Torrent.Torrent;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.*;

public class Tracker{

    public int port;
    Map<byte[], Torrent> tor;
    Socket socket;
    ServerSocket server;
    DataInputStream in;
    //public URL url;

    public Tracker(int port) throws IOException {
        this.tor = new HashMap<>();
        server = new ServerSocket(port);
    }

    public void add_torrent(Torrent torrent) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        tor.put(torrent.gethash(), torrent);
    }

    public Map<byte[], Torrent> getTor() {
        return tor;
    }

    public void run() throws IOException {
        System.out.println("Server started");

        System.out.println("Waiting for a client ...");

        socket = server.accept();
        System.out.println("Client accepted");

        // takes input from the client socket
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        String str;

        str = bf.readLine();
        System.out.println(str);
        String[] ss = str.split(";");
        System.out.println(Arrays.toString(ss));

        for(Map.Entry<byte[], Torrent> entry : tor.entrySet()) {
            byte[] cle = entry.getKey();
            Torrent value = entry.getValue();

            String te = Arrays.toString(cle);
            System.out.println("cle : " + te);
            if (te.equals(ss[2])) {
                pr.println("got it");
                //need to return list of peer to the client
                pr.println(value);
            }
            else {
                System.out.println("not got it");
            }
        }

        System.out.println(ss[2]);
        System.out.println(getTor().keySet());
        pr.flush();
    }
}
