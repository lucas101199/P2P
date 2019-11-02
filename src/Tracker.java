import Peers.Peers;
import Torrent.*;

import java.io.*;
import java.net.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Tracker{

    public int port;
    public Map<byte[], Torrent_track> tor; //byte[] = hash of the name of the file; Torrent_track = keep track of pieces and peers
    public Socket socket;
    public ServerSocket server;
    public DataInputStream in;
    //public URL url;

    public Tracker(int port) throws IOException {
        this.port = port;
        this.tor = new HashMap<>();
        server = new ServerSocket(port);
    }

    public void add_torrent(Torrent torrent, Torrent_track track) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        tor.put(torrent.gethash(), track);
    }

    public Map<byte[], Torrent_track> getTor() {
        return tor;
    }

    //communication with the client return list of peers via the PrintWriter if tracker got the torrent
    //TODO collect pieces send by the client that he already have and use this in method getListPeers
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

        for(Map.Entry<byte[], Torrent_track> entry : tor.entrySet()) {
            byte[] cle = entry.getKey();
            Torrent_track track = entry.getValue();

            String te = Arrays.toString(cle);
            System.out.println("cle : " + te);
            //List<byte[]> bytes = new LinkedList<>();
            if (te.equals(ss[2])) {

                //need to return list of peer to the client
                List<Peers> peers = getListPeers(track);
                pr.println(peers.get(0).getId() + ":" + peers.get(0).getPort());
                System.out.println(peers);
            }
            else {
                System.out.println("not got it");
            }
        }

        //System.out.println(ss[2]);
        //System.out.println(getTor().keySet());
        pr.flush();
    }

    public List<Peers> getListPeers(Torrent_track track) {
        List<Peers> peers = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            byte[] bytes = track.getTorrent().getPieces().get(RandomNumber(track));
            List<Peers> peer = track.getHashMap().get(bytes);
            for (Peers per : peer) {
                if (! peers.contains(per)) {
                    peers.add(per);
                }
            }
        }
        return peers;
    }

    public int RandomNumber(Torrent_track torrent) {
        return (int) (Math.random() * torrent.getHashMap().size());
    }
}
