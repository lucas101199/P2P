import Torrent.Torrent;
import java.io.File;
import java.net.URL;

public class Main_Client {

    public static void main(String args[]) throws Exception {
        File file = new File("/home/ens19/ens19lfz/edu/5DV205/assignments/project/src/Torrent/test_image.png");
        Torrent torrent = new Torrent(new URL("http://localhost:8080"), file, 256);
        byte[] by = torrent.gethash();
        //System.out.println(by);
        Client client = new Client(20, 9000);

        client.request(client.peer_id, client.port, client.getUrl(torrent).getPort(), torrent);

    }
}
