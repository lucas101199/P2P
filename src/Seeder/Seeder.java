package Seeder;

import Peers.Peers;

import java.io.*;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.*;

//seeder is build from a peer who has downloaded every pieces of a file

public class Seeder {

    public Peers peers;
    public File complete_file;

    public Seeder(Peers peer) {
        this.peers = peer;
    }

    //build a new seeder with file if it is the initial seeder
    public Seeder(Peers peers, File file) {
        this.peers = peers;
        this.complete_file = file;
    }

    public void buildFileWithPieces(Map<byte[], byte[]> data) throws IOException {
        long sizeFile = peers.getTorrent().getLength();

        byte[] allData = new byte[(int)sizeFile];

        ByteBuffer buff = ByteBuffer.wrap(allData);

        for (Map.Entry<byte[], byte[]> entry : data.entrySet()) {
            buff.put(entry.getValue());
        }

        byte[] combined = buff.array();

        try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/src/Seeder")) {
            fos.write(combined);
        }
    }

    //fill the hash map on class Peers with the file (to this only when it is the initial seeder cause after for the other peers the hash map will be fill with the data send by the other peers or seeder)
    public Map<byte[], byte[]> FillHashMapWithDataWhenInitialSeeder(File file) throws Exception {

        Map<byte[], byte[]> mapComplete = new HashMap<>();

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        int from = 0;

        byte[] dataInBuffer = bis.readAllBytes();

        int bytesAmount = 1024 * 256;
        int remainingBytes = (int) peers.torrent.getLength();

        for (int i = 0; i < peers.torrent.numberPieces(); i++) {
            if (bytesAmount > remainingBytes) {
                bytesAmount = remainingBytes;
            }
            byte[] buf = md.digest(Arrays.copyOfRange(dataInBuffer, from, bytesAmount + from));
            byte[] dd = Arrays.copyOfRange(dataInBuffer, from, bytesAmount + from);
            from += 256 * 1024;
            remainingBytes -= bytesAmount;
            mapComplete.put(buf, dd);
        }
        return mapComplete;
    }
}