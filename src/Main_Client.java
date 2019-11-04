import Peers.Peers;
import Torrent.Torrent;
import java.io.File;
import java.net.URL;
import java.util.Map;

public class Main_Client {

    public static void main(String args[]) throws Exception {
        File file = new File(System.getProperty("user.dir") + "/src/Torrent/test_image.png");
        Torrent torrent = new Torrent(new URL("http://localhost:8080"), file, 256);
        byte[] by = torrent.gethash();
        //System.out.println(by);
        //Client client = new Client(20, 9000);

        //Map<Integer, Integer> list_peers = client.request(client.peer_id, client.port, client.getUrl(torrent).getPort(), torrent);
        Peers peers = new Peers(20, 9000, torrent);
        //int port = list_peers.get(12);
        peers.connectToPeer(new Peers(12, 9009));
    }
}
