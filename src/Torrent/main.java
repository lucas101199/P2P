package Torrent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

public class main {

    public static void main(String args[]) throws IOException, NoSuchAlgorithmException {
        File fi = new File(System.getProperty("user.dir") + "/src/Torrent/test_image.png");

        Torrent to = new Torrent(new URL("http://www.example.com/docs/resource1.html"), fi, 256);

        to.setPieces(fi);
    }
}
