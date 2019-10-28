package Torrent;

import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



public class Torrent {

    URL trackerURL;
    long length;
    String nameFile;
    int piece_length;
    List<byte[]> pieces;

    public Torrent(URL trackerURL, File file, int piece_length) throws IOException, NoSuchAlgorithmException {
        this.trackerURL = trackerURL;
        this.length = file.length();
        this.nameFile = file.getName();
        this.piece_length = 1024 * piece_length;
        this.pieces = new LinkedList<>();
        setPieces(file);

    }

    public URL getTrackerURL() {
        return trackerURL;
    }

    public long getLength() {
        return length;
    }

    public String getNameFile() {
        return nameFile;
    }

    public int getPiece_length() {
        return piece_length;
    }

    public List<byte[]> getPieces() {
        return pieces;
    }

    public int numberPieces() {
        return pieces.size();
    }

    public void setPieces(File file) throws IOException, NoSuchAlgorithmException {
        byte[] buffer = new byte[piece_length];

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        MessageDigest md = MessageDigest.getInstance("SHA-1");

        int bytesAmount;
        while ((bytesAmount = bis.read(buffer)) > 0) {
            byte[] buf = md.digest(Arrays.copyOfRange(buffer, 0, bytesAmount));
            pieces.add(buf);
        }
    }
}
