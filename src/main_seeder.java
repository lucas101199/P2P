import Peers.Peers;
import Torrent.Torrent;

import java.io.File;
import java.net.URL;

public class main_seeder {
    public static void main(String args[]) throws Exception {
        File file = new File(System.getProperty("user.dir") + "/src/Torrent/test_image.png");
        Torrent torrent = new Torrent(new URL("http://localhost:8080"), file, 256);


    }
}
