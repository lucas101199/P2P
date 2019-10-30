package Seeder;

import Peers.Peers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

//seeder is build from a peer who has downloaded every pieces of a file

public class Seeder {

    Peers peers;
    File complete_file;

    public Seeder(Peers peer) {
        this.peers = peer;
    }

    //build a new seeder with file if it is the initial seeder
    public Seeder(File file) {
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

        try (FileOutputStream fos = new FileOutputStream("/home/ens19/ens19lfz/edu/5DV205/assignments/project/src/Seeder")) {
            fos.write(combined);
        }
    }

    //fill the hash map on class Peers with the file (to this only when it is the initial seeder cause after for the other peers the hash map will be fill with the data send by the other peers or seeder
    public void FillHashMapWithDataWhenInitialSeeder(File file) {

    }
}
