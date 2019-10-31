import Peers.Peers;
import Torrent.Torrent;
import Torrent.Torrent_track;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Main {

    public static void main(String args[]) throws IOException, NoSuchAlgorithmException {
        //File use for define the new torrent
        File file = new File(System.getProperty("user.dir") + "/src/Torrent/test_image.png");

        //creation of new torrent and his torrent tracker associate
        Torrent torrent = new Torrent(new URL("http://localhost:8080"), file, 256);
        Torrent_track track = new Torrent_track(torrent);

        //Creating a new server which contains all torrents
        Tracker tr = new Tracker(8080);
        //System.out.println(Arrays.toString(by));
        tr.add_torrent(torrent, track);
        Peers initial_seeder = new Peers(1, 9007, torrent);
        track.FillWithSeeder(initial_seeder);

        //Map<byte[], Torrent> tor = tr.getTor();
        tr.run();
        //System.out.println(tor);

    }
}
