import Torrent.Torrent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(String args[]) throws IOException, NoSuchAlgorithmException {
        File file = new File("/home/ens19/ens19lfz/edu/5DV205/assignments/project/src/Torrent/test_image.png");
        Torrent torrent = new Torrent(new URL("http://localhost:8080"), file, 256);
        Tracker tr = new Tracker(8080);
        byte[] by = torrent.gethash();
        //System.out.println(Arrays.toString(by));
        tr.add_torrent(torrent);
        Map<byte[], Torrent> tor = tr.getTor();
        tr.run();
        //System.out.println(tor);

    }
}
