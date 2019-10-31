package Seeder;

import Peers.Peers;

import java.io.*;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

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
    public void FillHashMapWithDataWhenInitialSeeder(File file, Map<byte[], byte[]> data) throws Exception {
        byte[] buffer = new byte[1024 * 256];

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        MessageDigest md = MessageDigest.getInstance("SHA-1");

        int bytesAmount;
        while ((bytesAmount = bis.read(buffer)) > 0) {
            byte[] buf = md.digest(Arrays.copyOfRange(buffer, 0, bytesAmount));

            for (Iterator<Map.Entry<byte[], byte[]>> iterator = data.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<byte[], byte[]> entry = iterator.next();
                byte[] hashPieces = entry.getKey();
                if (Arrays.equals(buf, hashPieces)) {
                    entry.setValue(buffer);
                    break;
                }
            }
        }
    }
}