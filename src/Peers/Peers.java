package Peers;

import Torrent.Torrent;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Peers {

    int id;
    int port;
    InetSocketAddress address;
    Peers_listener l ;
    Torrent torrent;
    Map<byte[], byte[]> data; //hashmap which contains the data(second byte[]) for each pieces(first byte[]) of the file

    public Peers(int id, int port, Torrent torrent) {
        this.id = id;
        this.port = port;
        this.torrent = torrent;
        this.address = new InetSocketAddress("localhost", this.port);
        this.data = new HashMap<>();

        //fill the hashmap for the first time when a new peer is created with all the hash of pieces contains in the torrent and data to null
        for (byte[] pieces : torrent.getPieces()) {
            byte[] data_downloaded = new byte[256];
            data.put(pieces, data_downloaded);
        }

        l = new Peers_listener(this);
        l.start();

    }

    public int getId() {
        return id;
    }

    public int getPort() {
        return port;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public Torrent getTorrent() {
        return torrent;
    }

    //become seeder when finish to download entire file

}
